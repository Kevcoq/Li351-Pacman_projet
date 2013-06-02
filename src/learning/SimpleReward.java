package learning;

import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.eleves.Maze;

public class SimpleReward implements Reward {

	@Override
	public double getReward(GameState from, GameState to) {
		Maze m = to.getMaze();
		AgentState p = to.getPacmanState(0);
		if (to.isLose())
			return -1000;
		else if (to.isWin())
			return 100;
		else if (m.isFood(p.getX() - 1, p.getY() - 1)) {
			return 10;
		} else if (!m.isCapsule(p.getX() - 1, p.getY() - 1)) {
			return -1;
		}
		// Si le pacman mange une capsule
		return 25;
	}
}
