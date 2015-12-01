

public class Instance {

	private int[][] nodes;
	private int size;
	
	public Instance(int[] tab1, int[] tab2, int size) {
		this.size = size;
		this.nodes = new int[this.size][2];
		for (int i = 0; i < this.size; i++) {
			int[] tab= {tab1[i], tab2[i]}; 
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
		//return this.nodes[a*this.size - a*(a+1)/2 + b][s];
		return this.nodes[a][s] - this.nodes[b][s];
	}
	
	
}
