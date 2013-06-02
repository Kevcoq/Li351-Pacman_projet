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

public class Test_SuivreUnChemin 
{
		public static void main(String args[]) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, MazeException
		{
			
			int timestep=300;
			String layout_file="layouts/originalClassic.lay";
			
			String pacman="pacman.prof.tp1.RandomAgent";
			String ghost="pacman.prof.tp1.RandomAgent";
			
			
			
					
			Class class_agent_pacman = Class.forName(pacman);
			Agent agent_pacman = (Agent) class_agent_pacman.newInstance();
			Class class_agent_ghost = Class.forName(ghost);
			Agent agent_ghost = (Agent) class_agent_ghost.newInstance();
			
			MazeWritable maze=new MazeWritable(layout_file);
			GameStateWritable state=new GameStateWritable(maze);
			
			
			int sx=maze.getPacmanStartX(0);
			int sy=maze.getPacmanStartY(0);
			int dx=1;
			int dy=1;
			
			
			if((maze.isWall(sx, sy) || (maze.isWall(dx, dy))))
			{
				System.out.println("Probleme mur...");
				System.exit(1);
			}
		
			
			ArrayList<Path> all_paths=SetPaths.calculTousLesCheminsLargeur(sx,sy,dx,dy, maze,50);
			Path p=all_paths.get((int)(Math.random()*all_paths.size()));
			System.out.println("Following : "+p);
			
			
			Game game=new Game(state);
			GamePanel panel=new GamePanel(state);
			game.addObserver(panel);
			
			game.addPacmanAgent(new Agent_FollowPath(p));
			for(int i=0;i<state.getNumberOfGhosts();i++)
				game.addGhostAgent(agent_ghost);
			
			JFrame frame = new JFrame("FrameDemo");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(640,480));
			frame.add(panel);
			frame.pack();
			frame.setVisible(true);
			GameState fstate=game.runUntilEnd(timestep);
			
			if (fstate.isLose())			
				System.out.println("Lose !!");
			else 
				System.out.println("Win !!");
			System.out.println("Nb Food = "+fstate.getFoodEaten());
			System.out.println("Nb Capsule = "+fstate.getCapsulesEaten());
			System.out.println("Nb Ghost = "+fstate.getGhostsEaten());
			System.out.println("Duration = "+fstate.getTime());
		}
	}

