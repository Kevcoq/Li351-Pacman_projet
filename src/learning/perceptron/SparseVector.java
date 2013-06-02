package learning.perceptron;

import java.util.HashMap;
import java.util.Iterator;

public class SparseVector implements Iterable<Integer> {
	protected HashMap<Integer, Double> values;
	protected int size;

	public SparseVector(int size) {
		this.size = size;
		values = new HashMap<Integer, Double>();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int f : this) {
			sb.append(f);
			sb.append(":");
			sb.append(getValue(f));
			sb.append(" ");
		}
		return (sb.toString());
	}

	/**
	 * Permet de changer la valeur de la clef i par v
	 * 
	 * @param i
	 * @param v
	 */
	public void setValue(int i, double v) {
		assert ((i > 0) && (i < size));
		if (v == 0) {
			if (values.containsKey(i))
				values.remove(i);
		} else {
			values.put(i, v);
		}
	}

	/**
	 * Renvoie la valeur associe a i
	 * 
	 * @param i
	 * @return
	 */
	public double getValue(int i) {
		if (values.containsKey(i))
			return (values.get(i));
		return (0.0);
	}

	/**
	 * Produit scalaire entre deux sparsevector
	 * 
	 * @param v
	 * @return
	 */
	public double computeDOT(SparseVector v) {
		double score = 0;
		for (Integer f : v) {
			score += v.getValue(f) * getValue(f);
		}
		return (score);
	}

	/**
	 * Permet d'iterer sur les index des valeurs non nulles du vecteur
	 */
	public Iterator<Integer> iterator() {
		return (values.keySet().iterator());
	}

	/**
	 * add
	 */
	public void addVector(SparseVector v2, double scalaire) {
		for (int f : v2) {
			double v = getValue(f);
			setValue(f, v + v2.getValue(f) * scalaire);
		}
	}

	/**
	 * La taille du sparsevector
	 * 
	 * @return
	 */
	public int size() {
		return (size);
	}

	/**
	 * Change la taille du sparsevector
	 * 
	 * @param size_vector
	 */
	public void setSize(int size_vector) {
		size = size_vector;
	}

}
