
import java.util.ArrayList;


public class PipedVND {
	private String[] neighbor = {"EXCHANGE","SWAP","INSERT"}; // || SWAP || EXCHANGE
	private boolean best = false; // || BEST
	private String init = "RDM"; // EDD || MDD
	private int k = neighbor.length;
	
	public PipedVND(String[] neigth, boolean best, String ini) {
		this.neighbor = neigth;
		this.best = best;
		this.init = ini;
	}
	
	public ArrayList<int[]> launch (ArrayList<int[]> inst) {
		// INIT
		ArrayList<int[]> jobs = Scheduler.schedule(inst, this.init);
		int i = 0, j = 0;
		int cost = Scheduler.cost(jobs);
		int cost_t = 0;
		ArrayList<int[]> tmp = null;
		
		while (j < k) {
			// faire un rdm sur i et ajouter un compteur
			tmp = Selector.select(jobs, neighbor[i], this.best);
			cost_t = Scheduler.cost(tmp);
			System.out.println(cost_t + " v = "+ i);
			
			if (cost_t < cost) {
				cost = cost_t;
				jobs = new ArrayList<int[]>(tmp);
				j = 0;
//				i = (i++)%3; //?
			} else {
				System.out.println("else");
				i = (++i)%3; // ou rdm ? 
				j++;
			}
		}
		return jobs;
	}
}
