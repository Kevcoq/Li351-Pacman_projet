package learning.perceptron;

import learning.Sensor;
import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;

public class PerceptronAgent implements Agent {
	private Perceptron percept;
	private Sensor sens;

	/**
	 * 
	 * @param percept
	 *            un perceptron
	 * @param sens
	 *            un sensor
	 */
	public PerceptronAgent(Perceptron percept, Sensor sens) {
		super();
		this.percept = percept;
		this.sens = sens;
	}

	@Override
	public AgentAction getAction(AgentState as, GameState state) {
		// TODO Auto-generated method stub
		int max = 4;
		double score_max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i <= 4; i++)
			if (state.isLegalMove(new AgentAction(i), as)) {
				double tmp = percept.getScore(sens.getVector(state,
						new AgentAction(i)));
				if (tmp > score_max) {
					score_max = tmp;
					max = i;
				}
				if (tmp == score_max)
					if (Math.random() > 0.5) {
						score_max = tmp;
						max = i;
					}
			}
		// System.out.println("Direction -> " + max + "\tscore : " + score_max);
		return new AgentAction(max);
	}

}
