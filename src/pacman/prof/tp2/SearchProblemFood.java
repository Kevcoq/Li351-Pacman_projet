package pacman.prof.tp2;

import pacman.eleves.AgentAction;
import pacman.eleves.GameState;
import pacman.eleves.Maze;
import pacman.eleves.tp2.SearchProblem;
import pacman.prof.GameStateWritable;

public class SearchProblemFood implements SearchProblem
{
	protected GameState initialState;
	
	public SearchProblemFood(GameStateWritable is)
	{
		initialState=is;
	}
	
	public GameState getInitialState() {return(initialState);}
	
	public boolean isFinalState(GameState s)
	{
		Maze m=s.getMaze();
		for(int x=0;x<m.getSizeX();x++)
			for(int y=0;y<m.getSizeY();y++)
			{
				if (m.isFood(x,y)) return(false);				
			}
		return(true);
	}
}
