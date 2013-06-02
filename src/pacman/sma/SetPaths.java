package pacman.sma;

import java.util.ArrayList;

import pacman.eleves.Maze;

/**
 * Ensemble de chemins entre deux points du labyrinthe tels que l'on ne passe pas deux fois par la même case
 * @author ludov_000
 *
 */

public class SetPaths 
{
	public static Path expandPath(int vx,int vy,Path p,Maze maze)
	{
		int lx=p.getEndX();
		int ly=p.getEndY();
		lx=lx+vx;
		ly=ly+vy;
		
		if (lx<0) return(null);
		if (lx>=maze.getSizeX()) return(null);
		if (ly<0) return(null);
		if (ly>=maze.getSizeY()) return(null);
		
		if (maze.isWall(lx, ly)) return(null);
		if (p.isInPath(lx,ly)) return(null);
		Path p2=p.copyPath();
		p2.addCase(lx,ly);
		return(p2);
	}
	
	public static ArrayList<Path> calculTousLesCheminsLargeur(int x,int y,int xd,int yd, Maze maze,int size_max)
	{
		ArrayList<Path> all_paths=new ArrayList<Path>();
		ArrayList<Path> to_explore=new ArrayList<Path>();
		to_explore.add(new Path(x,y,maze));
		System.out.println("Recherche de chemins de "+x+";"+y+" à "+xd+";"+yd);
		boolean flag=true;
		while((to_explore.size()>0) && (to_explore.get(0).size()<size_max))
		{	
			System.out.println(all_paths.size()+" Trouvés, " +to_explore.size()+" à explorer de taille "+to_explore.get(0).size());
			
			ArrayList<Path> new_to_explore=new ArrayList<Path>();
			for(Path p:to_explore)
			{
				//System.out.println(p);
				//if (p.size()>4) System.exit(0);
				{
					Path p1=SetPaths.expandPath(1, 0,p, maze);
					if (p1!=null)
					{
						if ((p1.getEndX()==xd) && (p1.getEndY()==yd)) all_paths.add(p1);
						else new_to_explore.add(p1);
					}
				}
				{
					Path p1=SetPaths.expandPath(-1, 0,p, maze);
					if (p1!=null)
					{
						if ((p1.getEndX()==xd) && (p1.getEndY()==yd)) all_paths.add(p1);
						else new_to_explore.add(p1);
					}				
				}
				{
					Path p1=SetPaths.expandPath(0, 1,p, maze);
					if (p1!=null)
					{
						if ((p1.getEndX()==xd) && (p1.getEndY()==yd)) all_paths.add(p1);
						else new_to_explore.add(p1);
					}	
				}
				{
					Path p1=SetPaths.expandPath(0, -1, p, maze);
					if (p1!=null)
					{
						if ((p1.getEndX()==xd) && (p1.getEndY()==yd)) all_paths.add(p1);
						else new_to_explore.add(p1);
					}		
				}
			}
			to_explore=new_to_explore;
		}
		System.out.println("=== Found : "+all_paths.size());
		
		return(all_paths);
	}	
}
