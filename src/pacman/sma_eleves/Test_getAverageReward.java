package pacman.sma_eleves;

import java.util.ArrayList;

import learning.Reward;
import learning.RewardTools;
import learning.SimpleReward;
import pacman.eleves.Agent;
import pacman.eleves.GameState;
import pacman.eleves.MazeException;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.prof.tp1.RandomAgent;
import pacman.sma.IntelligentGhost_Agent2;

public class Test_getAverageReward {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MazeWritable maze = new MazeWritable("layouts/originalClassic.lay");
			GameState state = new GameStateWritable(maze);

			RandomAgent pacman_agent = new RandomAgent();
			ArrayList<Agent> ghosts_agents = new ArrayList<>();
			for (int i = 0; i < 3; i++)
				ghosts_agents.add(new IntelligentGhost_Agent2(0.1));

			Reward reward = new SimpleReward();

			System.out.println(RewardTools.getAverageReward(state,
					pacman_agent, ghosts_agents, reward, 100, 100));
		} catch (MazeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
