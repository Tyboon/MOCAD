import java.util.ArrayList;


public class Instance {

	private int[][] nodes;
	private int size;
	
	public Instance(ArrayList<Integer> city1, ArrayList<Integer> city2) {
		this.size = city1.size();
		this.nodes = new int[this.size][2];
		for (int i = 0; i < this.size; i++) {
			int[] tab= {(int) city1.get(i), (int) city2.get(i)}; 
			nodes[i] = tab;
		}
		return;
	}

	public int[][] getNodes() {
		return this.nodes;
	}
	
	/**
	 * calcule le cout d'un déplacement de a vers b selon l'objectif s
	 * @param a départ
	 * @param b arrivée
	 * @param s objectif
	 * @return cout
	 */
	public int cost(int a, int b, int s) {
		return this.nodes[a*this.size - a*(a+1)/2 + b][s]; 
	}
	
	
}
