import java.util.ArrayList;
import java.util.Collections;
import smtwtp.*;


public class Scheduler {

	private Instance inst;
	
	
	public Scheduler(Instance inst) {
		this.inst = inst;
	}
	
	/**
	 * Génère une solution d'ordonnancement aléatoire
	 * @param size le nombre de villes
	 * @return la liste donnant l'ordonnancement des villes
	 */
	public ArrayList<Integer> generateAlea(int size) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i=0; i < size; i++) {
			l.add(i);
		}
		Collections.shuffle(l);
		return l;
	}
	
	/**
	 * Evalue le cout d'un ordonnancement l selon l'objectif s
	 * @param l liste d'ordonnancement
	 * @param s l'objectif
	 * @return le score
	 */
	public int evaluate(ArrayList<Integer> l, int s) {
		int sum = 0;
		for (int i = 0; i < l.size()-1; i++) {
			sum += this.inst.cost(l.get(i), l.get(i+1), s);
		}
		return sum;
	}
	
	public ArrayList<Integer> mTSPscalaire(int nbObj, int gap, ArrayList<Integer[]> order) {
		ArrayList<Integer> score = new ArrayList<>();
		int[] score_mono = new int[nbObj];
		for (int obj = 0; obj < nbObj; obj++) {
			score_mono[obj] = ILS.launch(order, obj);
		}
		
		return score;
	}
}
