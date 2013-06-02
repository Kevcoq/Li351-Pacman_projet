package pacman.sma_eleves;

import java.util.ArrayList;
import java.util.HashSet;

import pacman.eleves.Maze;

public class Path 
{
	protected int start_x;
	protected int start_y;
	protected int end_x;
	protected int end_y;
	
	protected Maze maze;
	protected HashSet<Integer> in_path;
	protected ArrayList<Integer> cases;
	
	protected int getIndexCase(int x,int y)
	{
		return(x*maze.getSizeY()+y);
	}
	
	protected int getXFromIndex(int index)
	{
		return(index/maze.getSizeY());
	}
	
	protected int getYFromIndex(int index)
	{
		return(index%maze.getSizeY());
	}
	
	public boolean isInPath(int x,int y)
	{
		return(in_path.contains(getIndexCase(x,y)));
	}
	
	public Path(int x,int y,Maze maze)
	{
		cases=new ArrayList<Integer>();
		in_path=new HashSet<Integer>();
		start_x=x;
		start_y=y;
		end_x=x;
		end_y=y;
		this.maze=maze;
		
		cases.add(getIndexCase(x, y));
		in_path.add(getIndexCase(x, y));
	}
	
	public void addCase(int x,int y)
	{
		cases.add(getIndexCase(x, y));
		in_path.add(getIndexCase(x, y));
		end_x=x;
		end_y=y;
	}

	public int size()
	{
		return(cases.size());
	}
	
	public int getX(int i)
	{
		return(getXFromIndex(cases.get(i)));
	}
	
	public int getY(int i)
	{
		return(getYFromIndex(cases.get(i)));
	}
	
	public Path copyPath()
	{
		Path p=new Path(start_x,start_y,maze);
		for(int i=1;i<size();i++)
		{
			p.addCase(getX(i),getY(i));
		}
		return(p);
	}

	public int getEndY() {
		return(end_y);
	}
	
	public int getEndX() {
		return(end_x);
	}
	
	public String toString()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("Path:");
		for(int i=0;i<cases.size();i++)
		{
			int in=cases.get(i);
			int x=getXFromIndex(in);
			int y=getYFromIndex(in);
			sb.append(" ");
			sb.append(x);
			sb.append(";");
			sb.append(y);
		}
		
		return(sb.toString());
	}
	
	public int nbCommonCases(Path p)
	{
		int nb=0;
			for(int j=0;j<p.size()-1;j++)
			{
				if (isInPath(p.getX(j),p.getY(j)))
				{
					nb++;
				}
			}
			return(nb);
	}
}
