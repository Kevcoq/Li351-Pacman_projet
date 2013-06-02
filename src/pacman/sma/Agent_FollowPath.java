package pacman.sma;

import pacman.eleves.Agent;
import pacman.eleves.AgentAction;
import pacman.eleves.AgentState;
import pacman.eleves.GameState;
import pacman.eleves.Maze;

public class Agent_FollowPath  implements Agent
{
	protected Path p;
	protected int index;
	
	public Agent_FollowPath(Path p)
	{
		index=0;
		this.p=p;
	}

	@Override
	public AgentAction getAction(AgentState as,GameState state) 
	{
		if (index>=p.size()-1) return(new AgentAction(Maze.STOP));
		
		if ((as.getX()!=p.getX(index)) || (as.getY()!=p.getY(index))) 
		{
			if (!as.isDead())
			{
				System.out.println("Probleme de positionnement de l'agent par rapport au chemin : ");
				System.out.println("index = "+index+" "+as.getX()+";"+as.getY()+" et "+p.getX(index)+";"+p.getY(index));
				return(new AgentAction(Maze.STOP));
			}
		}
		
		int xd=p.getX(index+1);
		int yd=p.getY(index+1);
		index++;
		if (xd==as.getX()-1) return(new AgentAction(Maze.WEST));
		else if (xd==as.getX()+1) return(new AgentAction(Maze.EAST));
		else if (yd==as.getY()-1) return(new AgentAction(Maze.NORTH));
		else if (yd==as.getY()+1) return(new AgentAction(Maze.SOUTH));
		else if (!as.isDead())
		{
			System.out.println("Chemin non contigu...");
			return(new AgentAction(Maze.STOP));
		}
		return(new AgentAction(Maze.STOP));
	}

}