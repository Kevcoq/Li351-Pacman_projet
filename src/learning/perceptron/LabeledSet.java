package learning.perceptron;

import java.util.ArrayList;

/**
 * Ensemble d'exemples ï¿½tiquetï¿½s
 * 
 * @author denoyer
 * 
 */
public class LabeledSet {
	protected ArrayList<SparseVector> vectors;
	protected ArrayList<Double> labels;
	protected int size_vectors;

	public LabeledSet(int size_vectors) {
		vectors = new ArrayList<SparseVector>();
		labels = new ArrayList<Double>();
		this.size_vectors = size_vectors;
	}

	/**
	 * Renvoie la taille des vecteurs
	 */
	public int getSizeVectors() {
		return (size_vectors);
	}

	/**
	 * Ajoute un exemple
	 * 
	 * @param v
	 * @param l
	 */
	public void addExample(SparseVector v, double l) {
		vectors.add(v);
		labels.add(l);
	}

	/**
	 * Renvoie la taille du labeled
	 * 
	 * @return
	 */
	public int size() {
		return (vectors.size());
	}

	/**
	 * Renvoie le sparsevector d'index i
	 * 
	 * @param i
	 * @return
	 */
	public SparseVector getVector(int i) {
		return (vectors.get(i));
	}

	/**
	 * Renvoie le label à l'index i
	 * 
	 * @param i
	 * @return
	 */
	public double getLabel(int i) {
		return (labels.get(i));
	}

}
