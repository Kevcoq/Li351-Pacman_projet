package pacman.eleves.tp2;

import java.util.ArrayList;

import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.eleves.Maze;

public abstract class SearchAgent implements Agent 
{
	protected ArrayList<AgentAction> actions;
	int index=0;
	
	public SearchAgent()
	{
	}
	
	public AgentAction getAction(AgentState as,GameState state) 
	{		
		if (actions==null) return(new AgentAction(Maze.STOP));
		if (index>=actions.size()) return(new AgentAction(Maze.STOP));
		AgentAction a=actions.get(index++);
		return(a);
	}
	
	public void search(SearchProblem problem)
	{
		actions=solveProblem(problem);
		index=0;
	}
	
	
	protected abstract ArrayList<AgentAction> solveProblem(SearchProblem problem);
}
