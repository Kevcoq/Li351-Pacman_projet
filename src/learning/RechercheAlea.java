package learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import learning.perceptron.Perceptron;
import learning.perceptron.PerceptronAgent;
import pacman.eleves.Agent;
import pacman.eleves.GameState;

public class RechercheAlea {
	private int vision, n, m;
	private double variance;
	private List<Elt> list;
	private GameState state;
	private Sensor sens;
	private Reward rw;
	private ArrayList<Agent> ghost;

	/**
	 * Construit une recherche aleatoire
	 * 
	 * @param vision
	 *            nombre de case vu par le pacman
	 * @param n
	 *            nombre max de perceptron
	 * @param m
	 *            nombre de perceptron garde a l'intermediaire
	 * @param variance
	 *            la variance
	 * @param state
	 *            l'etat du jeu
	 * @param sens
	 *            le sensor
	 * @param rw
	 *            le reward
	 * @param ghost
	 *            les fantomes
	 */
	public RechercheAlea(int vision, int n, int m, double variance,
			GameState state, Sensor sens, Reward rw, ArrayList<Agent> ghost) {
		super();
		this.vision = vision;
		this.n = n;
		this.m = m;
		this.variance = variance;
		this.list = new ArrayList<>();
		this.state = state;
		this.sens = sens;
		this.rw = rw;
		this.ghost = ghost;
	}

	private void algo() {
		for (int i = 0; i < n; i++) {
			Elt e = new Elt();
			e.perceptron = new Perceptron(vision);
			e.score = RewardTools.getAverageReward(state, new PerceptronAgent(
					e.perceptron, sens), ghost, rw, 50, 100);
			list.add(e);
		}

		Collections.sort(list);

		for (int i = m; i < n; i++) {
			Elt e = new Elt();
			e.perceptron = list.get(i % m).perceptron.bruite(variance);
			e.score = RewardTools.getAverageReward(state, new PerceptronAgent(
					e.perceptron, sens), ghost, rw, 100, 100);
			list.set(i, e);
			System.out.println("score : " + e.score);
		}
		Collections.sort(list);

	}

	/**
	 * Rend le meilleurs perceptron de la recherche aleatoires
	 * 
	 * @return
	 */
	public Perceptron getMeilleur() {
		algo();
		Elt e = list.get(0);
		System.out.println("SCORE FINAL : " + e.score);
		return e.perceptron;
	}
}
