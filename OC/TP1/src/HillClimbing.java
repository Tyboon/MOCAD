import java.util.ArrayList;


public class HillClimbing {

	private String neighbor = "INSERT"; // || SWAP || EXCHANGE
	private boolean best = false; // || BEST
	private String init = "RDM"; // EDD || MDD
	
	public HillClimbing(String neigth, boolean best, String ini) {
		this.neighbor = neigth;
		this.best = best;
		this.init = ini;
	}
	
	public ArrayList<int[]> launch (Instance inst) {
		// INIT
//		ArrayList<int[]> jobs = Scheduler.schedule(inst.jobs, this.init);
		ArrayList<int[]> jobs = Scheduler.EDD(inst.jobs);
		
		boolean progress = true;
		int cost = Scheduler.cost(jobs);
		int cost_t = 0;
		ArrayList<int[]> tmp = new ArrayList<int[]>();
		
		while (progress) {
		// BOUCLE TANT QUE MEILLEUR
			tmp = Selector.select(jobs, neighbor, this.best);
			cost_t = Scheduler.cost(tmp);
			if (cost_t < cost) {
				cost = cost_t;
				jobs = new ArrayList<int[]>(tmp);
			} else {
				progress = false;
			}
		}
		return jobs;
	}
	
}
