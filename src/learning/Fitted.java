package learning;

import learning.perceptron.SparseVector;
import pacman.eleves.AgentAction;

public class Fitted {
	private SparseVector init, atteint;
	private AgentAction action;
	private double reward_atteint;

	public Fitted(SparseVector init, AgentAction action, SparseVector atteint,
			double reward_obtenu) {
		super();
		this.init = init;
		this.action = action;
		this.atteint = atteint;
		this.reward_atteint = reward_obtenu;
	}

	public Fitted getFitted() {
		return this;
	}

	public SparseVector getInit() {
		return init;
	}

	public SparseVector getAtteint() {
		return atteint;
	}

	public AgentAction getAction() {
		return action;
	}

	public double getReward_atteint() {
		return reward_atteint;
	}

	@Override
	public String toString() {
		return "Fitted [action=" + action + ", reward_atteint="
				+ reward_atteint + "]";
	}

}
