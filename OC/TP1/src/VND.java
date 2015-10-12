import java.util.ArrayList;


public class VND {
	private String[] neighbor = {"EXCHANGE","SWAP","INSERT"}; // || SWAP || EXCHANGE
	private boolean best = false; // || BEST
	private String init = "RDM"; // EDD || MDD
	private int k = neighbor.length;
	
	public VND(String[] neigth, boolean best, String ini) {
		this.neighbor = neigth;
		this.best = best;
		this.init = ini;
		this.k = neighbor.length;
	}
	
	public ArrayList<int[]> launch (ArrayList<int[]> inst) {
		// INIT
		ArrayList<int[]> jobs = Scheduler.schedule(inst, this.init);
		int i = 0;
		int cost = Scheduler.cost(jobs);
		int cost_t = 0;
		ArrayList<int[]> tmp = null;
		
		while (i < k) {
			tmp = Selector.select(jobs, neighbor[i], this.best);
			cost_t = Scheduler.cost(tmp);
			
			if (cost_t < cost) {
				cost = cost_t;
				jobs = new ArrayList<int[]>(tmp);
				i = 0;
			} else {
				i++;
			}
		}
		return jobs;
	}
}
