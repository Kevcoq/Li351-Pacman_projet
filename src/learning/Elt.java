package learning;

import learning.perceptron.Perceptron;

/**
 * Un element contient un perceptron et son score associe
 * 
 * @author
 * 
 */
public class Elt implements Comparable<Elt> {
	public Perceptron perceptron;
	public double score;

	@Override
	public int compareTo(Elt arg0) {
		Double i1 = new Double(score);
		Double i2 = new Double(arg0.score);
		return (i2.compareTo(i1));
	}

}