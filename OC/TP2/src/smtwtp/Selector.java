package smtwtp;
import java.util.ArrayList;


public class Selector {

	public static ArrayList<int[]> select_first(ArrayList<int[]> jobs, String neighbor) {
		ArrayList<int[]> tmp = new ArrayList<int[]>(jobs);
		int cost = Scheduler.cost(jobs);
		int jobs_size = jobs.size();
		
		for (int i =0; i<jobs_size; i++) {
			for (int j=i+1; j<jobs_size; j++) {
				tmp = Neighbor.select(jobs,i,j,neighbor);
				if (Scheduler.cost(tmp) < cost){
					return tmp;
				}
//				System.out.println("Test 1 "+ i + "  " + j);
			}
		}
		return tmp;
	}
	
	public static ArrayList<int[]> select_best(ArrayList<int[]> jobs, String neighbor) {
		ArrayList<int[]> tmp = new ArrayList<int[]>(jobs);
		int cost = Scheduler.cost(jobs);
		ArrayList<int[]> var = new ArrayList<int[]>();
		int cost_var = Integer.MAX_VALUE;
		for (int i =0; i<jobs.size(); i++) {
			for (int j=0; j<jobs.size(); j++) {
//				System.out.println(cost +"  " + cost_var);	//PRINT
				var = Neighbor.select(jobs,i,j,neighbor);
				cost_var = Scheduler.cost(var);
				if (cost_var < cost){
					tmp = var;
					cost = cost_var;
				}
			}
		}
		return tmp;
	}

	public static ArrayList<int[]> select(ArrayList<int[]> jobs, String neighbor, boolean best) {
		if (best) 
			return select_best(jobs,neighbor);
		return select_first(jobs, neighbor);
	}

	public static ArrayList<int[]> accept(ArrayList<int[]> tmp) {
		// TODO
		return null;
	}
	
}
