package pacman.eleves.tp2;

import pacman.eleves.AgentAction;
import pacman.eleves.GameState;

public interface SearchProblem
{
	public GameState getInitialState();
	public  boolean isFinalState(GameState s);
}
