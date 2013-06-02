package pacman.sma;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import pacman.eleves.Agent;
import pacman.eleves.GameState;
import pacman.eleves.MazeException;
import pacman.prof.Game;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.prof.graphics.GamePanel;

public class Test_Collaboratif_MinCommonCase 
{
		protected MazeWritable maze;
		protected int timestep;
		protected int nb_tries;
		protected ArrayList<ArrayList<Path> > all_paths_pacmans;
		
		public Test_Collaboratif_MinCommonCase(MazeWritable maze,int timestep,int nb_tries)
		{
			this.maze=maze;
			this.timestep=timestep;
			this.nb_tries=nb_tries;
		}
		
		public void computeAllPaths()
		{
			all_paths_pacmans=new ArrayList<ArrayList<Path>>();
			for(int i=0;i<maze.getNumberOfPacmans();i++)
			{
				int sx=maze.getPacmanStartX(i);
				int sy=maze.getPacmanStartY(i);
				int dx=1;
				int dy=1;
				ArrayList<Path> all_paths=SetPaths.calculTousLesCheminsLargeur(sx,sy,dx,dy, maze,90);
				all_paths_pacmans.add(all_paths);
			}
		}
		
		public ArrayList<Path> choosePaths()
		{
			System.out.println("Choosing paths...");
			
			ArrayList<ArrayList<Path> > ppacmans_paths=new ArrayList<ArrayList<Path>>();
			ArrayList<Integer> nnb_commons=new ArrayList<Integer>();
			for(int k=0;k<nb_tries;k++)
			{
				ArrayList<Path> pacmans_paths=new ArrayList<Path>();
				for(ArrayList<Path> a:all_paths_pacmans)
				{
					int index=(int)(Math.random()*a.size());
					pacmans_paths.add(a.get(index));
				}
				
				int nb_common=0;
				for(int i=0;i<pacmans_paths.size();i++)
					for(int j=i+1;j<pacmans_paths.size();j++)
					{
							nb_common+=pacmans_paths.get(i).nbCommonCasesButNotLast(pacmans_paths.get(j));
					}
				
				ppacmans_paths.add(pacmans_paths);
				nnb_commons.add(nb_common);				
			}
			
			
			//Recherche du minimum
			int min_nb=nnb_commons.get(0);
			ArrayList<Path> min_paths=ppacmans_paths.get(0);
			for(int i=1;i<nnb_commons.size();i++)
			{
				if (nnb_commons.get(i)<min_nb)
				{
					min_nb=nnb_commons.get(i);
					min_paths=ppacmans_paths.get(i);
					System.out.println(" Min = "+min_nb);
				}
			}
			return(min_paths);
		}
		
		public int launchGames(int nb_games,boolean visualisation)
		{
			int nb=0;
			for(int i=0;i<nb_games;i++)
				if (launchOneGame(visualisation)) nb++;
			return(nb);
		}
		
		public boolean launchOneGame(boolean visualisation)
		{
			GameStateWritable state=new GameStateWritable(maze);
			Game game=new Game(state);
			
			ArrayList<Path> pacmans_paths=choosePaths();
			
			for(Path p:pacmans_paths)
			{
				System.out.println(p);
				game.addPacmanAgent(new Agent_FollowPath(p));
			}
			
			
			for(int i=0;i<state.getNumberOfGhosts();i++)
				game.addGhostAgent(new IntelligentGhost_Agent1());	
			
			if (visualisation)
			{
				GamePanel panel=new GamePanel(state);
				game.addObserver(panel);			
				JFrame frame = new JFrame("FrameDemo");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setPreferredSize(new Dimension(640,480));
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
				GameState fstate=game.runUntilEnd(100);
				
				return (!fstate.isLose());
			}
			else
			{
				GameState fstate=game.runUntilEnd(0);
				return (!fstate.isLose());
			}
		}
	
		public static void main(String args[]) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, MazeException
		{
			
			int timestep=100;
			int nb_games=100;
			int nb_tries=3;
			String layout_file="layouts/trickyClassic.lay";
			
			Test_Collaboratif_MinCommonCase t=new Test_Collaboratif_MinCommonCase(new MazeWritable(layout_file), timestep,nb_tries);
			t.computeAllPaths();
			int nb_won=t.launchGames(nb_games,true);
			System.out.println(nb_won +" over "+nb_games);
		}
	}

