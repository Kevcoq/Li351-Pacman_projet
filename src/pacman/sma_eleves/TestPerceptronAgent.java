package pacman.sma_eleves;

import java.awt.Dimension;

import javax.swing.JFrame;

import learning.Sensor;
import learning.SimpleStateSensor;
import learning.perceptron.Perceptron;
import learning.perceptron.PerceptronAgent;
import pacman.eleves.Agent;
import pacman.eleves.MazeException;
import pacman.prof.Game;
import pacman.prof.GameStateWritable;
import pacman.prof.MazeWritable;
import pacman.prof.graphics.GamePanel;
import pacman.sma.IntelligentGhost_Agent2;

public class TestPerceptronAgent {
	public static void main(String args[]) throws MazeException {
		MazeWritable maze = new MazeWritable("layouts/originalClassic.lay");
		GameStateWritable state = new GameStateWritable(maze);

		Game game = new Game(state);
		GamePanel panel = new GamePanel(state);
		game.addObserver(panel);

		int vision = 4;
		Sensor sens = new Sensor(new SimpleStateSensor(vision));
		Perceptron percept = new Perceptron(vision);
		PerceptronAgent pa = new PerceptronAgent(percept, sens);
		Agent agent_pacman = pa;
		Agent agent_ghost = new IntelligentGhost_Agent2(0.1);

		game.addPacmanAgent(agent_pacman);
		for (int i = 0; i < state.getNumberOfGhosts(); i++)
			game.addGhostAgent(agent_ghost);

		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640, 480));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		game.runUntilEnd(50);
	}
}
