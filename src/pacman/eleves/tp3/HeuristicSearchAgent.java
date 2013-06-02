package pacman.eleves.tp3;

import java.util.ArrayList;

import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.eleves.Maze;
import pacman.eleves.tp2.SearchProblem;

public abstract class HeuristicSearchAgent implements Agent 
{
	protected ArrayList<AgentAction> actions;
	protected Heuristic heuristic;
	int index=0;
	
	public HeuristicSearchAgent()
	{		
	}
	
	public void setHeuristic(Heuristic h)
	{
		heuristic=h;
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
