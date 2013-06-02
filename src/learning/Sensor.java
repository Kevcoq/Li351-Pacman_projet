package learning;

import learning.perceptron.SparseVector;
import pacman.eleves.AgentAction;
import pacman.eleves.GameState;

public class Sensor {
	protected StateSensor ss;

	public Sensor(StateSensor s) {
		this.ss = s;
	}

	/**
	 * Taille du StateSensor
	 * 
	 * @return
	 */
	public int size() {
		return (ss.size() * 4 + 1);
	}

	/**
	 * Retourne le vecteur correspond a une action par rapport à une situation
	 * 
	 * @param s
	 * @param action
	 * @return
	 */
	public SparseVector getVector(GameState s, AgentAction action) {
		SparseVector r = new SparseVector(size());
		SparseVector v = ss.getVector(s);
		r.setValue(0, 1.0);
		int pos = action.getDirection() * ss.size() + 1;
		for (int f : v) {
			r.setValue(pos + f, v.getValue(f));
		}
		return (r);
	}
}
