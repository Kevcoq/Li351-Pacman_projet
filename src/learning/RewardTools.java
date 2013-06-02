package learning;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.prof.Game;
import pacman.prof.graphics.GamePanel;

public class RewardTools {
	/**
	 * Rend le score du pacman sur le jeu
	 * 
	 * @param initial_state
	 * @param pacman_agent
	 * @param ghosts_agents
	 * @param reward
	 * @param size_max_trajectory
	 * @return
	 */
	public static double getReward(GameState initial_state, Agent pacman_agent,
			ArrayList<Agent> ghosts_agents, Reward reward,
			int size_max_trajectory) {
		GameState s = initial_state.copy();
		double rs = 0;
		int time = 0;
		boolean flag = true;
		while (flag) {
			GameState iv = s.copy();
			AgentAction action = pacman_agent.getAction(s.getPacmanState(0), s);
			s = s.nextStatePacman(action);

			if ((!s.isLose()) && (!s.isWin())) {
				ArrayList<AgentAction> gactions = new ArrayList<AgentAction>();
				for (int i = 0; i < ghosts_agents.size(); i++) {
					Agent a = ghosts_agents.get(i);
					AgentState ss = s.getGhostState(i);
					AgentAction aa = a.getAction(ss, s);
					gactions.add(a.getAction(ss, s));
				}
				s = s.nextStateGhosts(gactions);
				double r = reward.getReward(iv, s);
				rs += r;
			} else {
				double r = reward.getReward(iv, s);
				rs += r;
				flag = false;
			}

			if (s.isLose() || s.isWin())
				flag = false;

			if (time++ >= (size_max_trajectory - 1))
				flag = false;
		}
		return (rs);
	}

	/**
	 * Rend le score moyen du pacman sur le jeu
	 * 
	 * @param initial_state
	 * @param pacman_agent
	 * @param ghosts_agents
	 * @param reward
	 * @param size_max_trajectory
	 * @param nb_trajectories
	 * @return
	 */
	public static double getAverageReward(GameState initial_state,
			Agent pacman_agent, ArrayList<Agent> ghosts_agents, Reward reward,
			int size_max_trajectory, int nb_trajectories) {
		// TODO
		int somme = 0;
		for (int n = 0; n < nb_trajectories; n++)
			somme += getReward(initial_state, pacman_agent, ghosts_agents,
					reward, size_max_trajectory);
		return ((somme + 0.0) / nb_trajectories);
	}

	/**
	 * Permet de visualiser un agent
	 * 
	 * @param initial_state
	 * @param pacman_agent
	 * @param ghosts_agents
	 * @param reward
	 * @param timestep
	 */
	public static void vizualize(GameState initial_state, Agent pacman_agent,
			ArrayList<Agent> ghosts_agents, Reward reward,
			int size_max_trajectory, int timestep) {
		Game game = new Game(initial_state);
		game.addPacmanAgent(pacman_agent);

		for (Agent a : ghosts_agents)
			game.addGhostAgent(a);

		GamePanel panel = new GamePanel(initial_state);
		game.addObserver(panel);
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640, 480));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		GameState fstate = game.runUntilEnd(timestep, size_max_trajectory);
	}

}
