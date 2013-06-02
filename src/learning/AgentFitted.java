package learning;

import java.util.ArrayList;
import java.util.List;

import learning.perceptron.SparseVector;

import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.prof.tp1.RandomAgent;

public class AgentFitted implements Agent {
	private Agent a = new RandomAgent();
	private ArrayList<Fitted> list;

	public AgentFitted() {
		super();
		this.list = new ArrayList<>();
	}

	@Override
	public AgentAction getAction(AgentState as, GameState state) {
		Sensor s = new Sensor(new SimpleStateSensor(4));
		AgentAction aa = a.getAction(as, state);
		SparseVector init = s.getVector(state, aa), atteint = s.getVector(
				state.nextStatePacman(aa), aa);
		Reward rw = new SimpleReward();
		double score = rw.getReward(state, state.nextStatePacman(aa));
		list.add(new Fitted(init, aa, atteint, score));
		return aa;
	}

	public List<Fitted> getList() {
		return list;
	}
}
