package pacman.eleves.tp3;

import pacman.eleves.GameState;

public interface Heuristic 
{
	public double getScore(GameState gs);
	
}
