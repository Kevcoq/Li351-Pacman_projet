package pacman.sma_eleves;

import java.util.ArrayList;

import learning.RechercheAlea;
import learning.Reward;
import learning.RewardTools;
import learning.Sensor;
import learning.SimpleReward;
import learning.SimpleStateSensor;
import learning.perceptron.Perceptron;
import learning.perceptron.PerceptronAgent;
import pacman.eleves.Agent;
import pacman.eleves.MazeException;
import pacman.prof.Game;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.sma.IntelligentGhost_Agent2;

public class TestAlgoAleaPerceptron {
	public static void main(String args[]) throws MazeException {
		String layout_file = "layouts/mediumClassic.lay";
		MazeWritable maze = new MazeWritable(layout_file);
		GameStateWritable state = new GameStateWritable(maze);

		Game game = new Game(state);

		Agent agent_ghost = new IntelligentGhost_Agent2(0.1);
		ArrayList<Agent> ghost_list = new ArrayList<>();
		for (int i = 0; i < state.getNumberOfGhosts(); i++) {
			game.addGhostAgent(agent_ghost);
			ghost_list.add(agent_ghost);
		}
		int vision = 5;
		Sensor sens = new Sensor(new SimpleStateSensor(vision));
		Reward reward = new SimpleReward();

		RechercheAlea ra = new RechercheAlea(vision, 100, 50, 50, state, sens,
				reward, ghost_list);
		Perceptron percept = ra.getMeilleur();
		Agent agent_pacman = new PerceptronAgent(percept, sens);

		RewardTools.vizualize(state, agent_pacman, ghost_list, reward, 100, 30);
	}
}
