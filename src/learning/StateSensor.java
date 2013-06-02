package learning;

import learning.perceptron.SparseVector;
import pacman.eleves.GameState;

public interface StateSensor {
	/**
	 * Taille du sparse vector
	 * 
	 * @return
	 */
	public int size();

	/**
	 * Retourne le sparse vector
	 * 
	 * @param s
	 * @return
	 */
	public SparseVector getVector(GameState s);
}
