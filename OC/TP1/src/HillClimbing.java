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
		System.out.println("INIT");
		ArrayList<int[]> jobs = Scheduler.schedule(inst.jobs, this.init);
		boolean progress = true;
		int cost = Scheduler.cost(jobs);
		int cost_t = Integer.MAX_VALUE;
		ArrayList<int[]> tmp = null;
		
		
		while (progress) {
		// BOUCLE TANT QUE MEILLEUR
			tmp = Selector.select(jobs, neighbor, this.best);
			System.out.println("WHILE");
			cost_t = Scheduler.cost(tmp);
			if (cost_t < cost) {
				cost = cost_t;
				jobs = tmp;
			} else
				progress = false;
		}
		return jobs;
	}
	
}
