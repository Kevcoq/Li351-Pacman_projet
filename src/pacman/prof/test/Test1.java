package pacman.prof.test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import pacman.eleves.MazeException;
import pacman.prof.Game;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.prof.graphics.GamePanel;
import pacman.prof.tp1.RandomAgent;

public class Test1 {

	public static void main(String args[]) throws MazeException
	{
		MazeWritable maze=new MazeWritable("layouts/openMaze.lay");
		GameStateWritable state=new GameStateWritable(maze);
		
		Game game=new Game(state);
		GamePanel panel=new GamePanel(state);
		game.addObserver(panel);
		
		for(int i=0;i<state.getNumberOfPacmans();i++)
			game.addPacmanAgent(new RandomAgent());
		for(int i=0;i<state.getNumberOfGhosts();i++)
			game.addGhostAgent(new RandomAgent());
		
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640,480));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		game.runUntilEnd(300);
		
	}
}
