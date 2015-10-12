import java.util.ArrayList;
import java.util.Random;


public class ILS {

	private  String[] neighbors = {"EXCHANGE","SWAP","INSERT"};
	private VND vnd = new VND(neighbors, true, "EDD");
	private String init = "EDD";
	
	public ArrayList<int[]> launch (ArrayList<int[]> inst) {
		ArrayList<int[]> jobs = Scheduler.schedule(inst, this.init);
		jobs = vnd.launch(jobs);
		
		ArrayList<int[]> max = new ArrayList<int[]>(jobs);
		int cost = Scheduler.cost(max);
		int cost_t;
		
		int j, k;
		Random rand = new Random();
		ArrayList<int[]> tmp = null;
		int nbIter = 100;
		
		//boolean condition = true; //voir selon les critères : tps ou nbIter 
		
		while (nbIter > 0) {
			// Perturbation
			k = rand.nextInt(jobs.size());
			j = rand.nextInt(jobs.size());
			jobs = Neighbor.kopt(jobs, j, k);
			
			// Local Search
			tmp = vnd.launch(jobs);
			jobs = tmp; // voir acceptation
			cost_t = Scheduler.cost(jobs);
			
			if (cost < cost_t) {
				max = jobs;
				cost = cost_t;
			}
			nbIter--;
		}
		return max;
	}
	
}
