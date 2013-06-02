package learning.perceptron;

public class Perceptron {
	private SparseVector parameters;

	/**
	 * Constructeur qui prend un entier et un nombre d'iteration
	 * 
	 * @param n
	 *            la taille des parametres
	 * @param nb_iterations
	 */
	public Perceptron(int n) {
		parameters = new SparseVector((int) Math.pow(n, 2) * 3);
		for (int i = 0; i < parameters.size; i++)
			parameters.setValue(i, Math.random() * 20 - 10);
	}

	/**
	 * Constructeur qui prend un sparse vector, un nombre d'iteration et la
	 * variance
	 * 
	 * @param p
	 * @param nb_iterations
	 * @param variance
	 */
	public Perceptron(SparseVector p, double variance) {
		parameters = new SparseVector(p.size);
		for (int i = 0; i < parameters.size; i++)
			if (Math.random() > 0.5)
				parameters.setValue(i,
						p.getValue(i) + p.getValue(i) * Math.random()
								* variance);
			else
				parameters.setValue(i,
						p.getValue(i) - p.getValue(i) * Math.random()
								* variance);
	}

	/**
	 * Le score du perceptron face à un autre sparse vector
	 * 
	 * @param v
	 * @return
	 */
	public double getScore(SparseVector v) {
		return (parameters.computeDOT(v));
	}

	/**
	 * Permet d'entraine le perceptron
	 * 
	 * @param training_set
	 */
	public void train(LabeledSet training_set, int nb_iterations) {
		for (int nbit = 0; nbit < nb_iterations; nbit++) {
			System.out.println("======= Iteration  " + nbit + " =======");

			for (int i = 0; i < training_set.size(); i++) {
				SparseVector v = training_set.getVector(i);
				double reel = training_set.getLabel(i);
				double predicted = getScore(v);
				double error = reel - predicted;
				// System.out.println("EPSILON => " + error);
				parameters.addVector(v, reel - (Math.random() * error));

			}
		}
	}

	/**
	 * Renvoie un perceptron t plus, cad un perceptron qui prend le meilleurs
	 * parametre entre celui de base et ce que donne son fichier de test
	 * 
	 * @param training_set
	 * @return
	 */
	public Perceptron t_Plus_UN(LabeledSet training_set) {
		Perceptron t_Plus = new Perceptron(training_set.size_vectors);
		for (int i = 0; i < this.parameters.size; i++) {
			double max = Double.NEGATIVE_INFINITY;
			for (int j = 0; j < training_set.size(); j++)
				if (training_set.getVector(j).getValue(i) > max)
					max = training_set.getVector(j).getValue(i);
			t_Plus.parameters.setValue(i,
					Math.max(max, this.getParameter().getValue(i)));
		}
		return t_Plus;
	}

	/**
	 * Permet de rendre un perceptron avec des parametre bruité par rapport au
	 * précedant
	 * 
	 * @param variance
	 * @return
	 */
	public Perceptron bruite(double variance) {
		Perceptron p = new Perceptron(this.parameters, variance);
		return p;
	}

	/**
	 * Renvoie le sparsevector
	 * 
	 * @return
	 */
	public SparseVector getParameter() {
		return parameters;
	}
}
