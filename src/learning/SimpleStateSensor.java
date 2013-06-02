package learning;

import learning.perceptron.SparseVector;
import pacman.eleves.GameState;
import pacman.eleves.Maze;

public class SimpleStateSensor implements StateSensor {
	private SparseVector vector;
	private int n;

	public SimpleStateSensor(int n) {
		super();
		this.vector = new SparseVector((int) Math.pow(n, 2) * 3);
		this.n = n;
	}

	@Override
	public int size() {
		return vector.size();
	}

	@Override
	public SparseVector getVector(GameState s) {
		calc(s);
		return vector;
	}

	/**
	 * Calcule le sparse vector
	 * 
	 * @param s
	 */
	private void calc(GameState s) {
		Maze m = s.getMaze();
		int x = s.getPacmanState(0).getX();
		int y = s.getPacmanState(0).getY();

		int cpt = 0;
		for (int i = -n / 2; i < n / 2; i++)
			for (int j = -n / 2; j < n / 2; j++)
				if (j > 0 && i > 0 && j < m.getSizeY() - y
						&& i < m.getSizeX() - x) {
					// wall
					if (m.isWall(x + i, y + j))
						vector.setValue(cpt++, 1);
					else
						vector.setValue(cpt++, 0);

					// food
					if (m.isFood(x + i, y + j))
						vector.setValue(cpt++, 1);
					else
						vector.setValue(cpt++, 0);

					// ghost
					boolean ghost = false;
					for (int k = 0; k < s.getNumberOfGhosts() && !ghost; k++)
						if (s.getGhostState(k).getX() == x
								&& s.getGhostState(k).getY() == y)
							ghost = true;
					if (ghost)
						vector.setValue(cpt++, 1);
					else
						vector.setValue(cpt++, 0);

				}
	}
}
