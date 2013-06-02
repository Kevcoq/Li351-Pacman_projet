package learning;

import pacman.eleves.GameState;

public interface Reward {
	/**
	 * Rend le score associe a ce chemin
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public double getReward(GameState from, GameState to);
}
