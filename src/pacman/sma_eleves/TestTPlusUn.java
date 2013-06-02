package pacman.sma_eleves;

import java.util.ArrayList;

import learning.AgentFitted;
import learning.Fitted;
import learning.Reward;
import learning.RewardTools;
import learning.Sensor;
import learning.SimpleReward;
import learning.SimpleStateSensor;
import learning.perceptron.LabeledSet;
import learning.perceptron.Perceptron;
import learning.perceptron.PerceptronAgent;
import pacman.eleves.Agent;
import pacman.eleves.MazeException;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.sma.IntelligentGhost_Agent2;

public class TestTPlusUn {
	public static void main(String[] args) throws MazeException {
		int vision = 4;

		String layout_file = "layouts/mediumClassic.lay";
		MazeWritable maze = new MazeWritable(layout_file);
		GameStateWritable state = new GameStateWritable(maze);

		SimpleStateSensor ss = new SimpleStateSensor(vision);
		Sensor s = new Sensor(ss);

		Reward r = new SimpleReward();

		ArrayList<Agent> ghost = new ArrayList<>();
		for (int i = 0; i < state.getNumberOfGhosts(); i++)
			ghost.add(new IntelligentGhost_Agent2(0.1));

		LabeledSet train = new LabeledSet(s.size());
		for (int i = 0; i < 500; i++) {
			AgentFitted af = new AgentFitted();
			RewardTools.getReward(state, af, ghost, r, 200);
			for (Fitted f : af.getList()) {
				train.addExample(f.getAtteint(), f.getReward_atteint());
			}
		}
		System.out.println("Nombre d'echantillon : " + train.size());
		Perceptron p = new Perceptron(vision);
		p.train(train, 200);

		LabeledSet train2 = new LabeledSet(s.size());
		for (int i = 0; i < 500; i++) {
			AgentFitted af = new AgentFitted();
			RewardTools.getReward(state, af, ghost, r, 200);
			for (Fitted f : af.getList()) {
				train2.addExample(f.getAtteint(), f.getReward_atteint());
			}
		}
		Perceptron pPlus = p.t_Plus_UN(train2);

		RewardTools.vizualize(state, new PerceptronAgent(pPlus, s), ghost, r,
				100, 30);
	}
}
