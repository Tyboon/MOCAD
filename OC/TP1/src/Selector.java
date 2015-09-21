import java.util.ArrayList;


public class Selector {

	public static ArrayList<int[]> select_first(ArrayList<int[]> jobs, String neightboor) {
		ArrayList<int[]> tmp = jobs;
		int cost = Scheduler.cost(jobs);
		
		for (int i =1; i<jobs.size(); i++) {
			for (int j=0; j<jobs.size(); j++) {
				tmp = Neigthboor.select(jobs,i,j,neightboor);
				if (Scheduler.cost(tmp) < cost){
					return tmp;
				}
			}
		}
		return tmp;
	}
	
	public static ArrayList<int[]> select_best(ArrayList<int[]> jobs, String neightboor) {
		ArrayList<int[]> tmp = jobs;
		int cost = Scheduler.cost(jobs);
		ArrayList<int[]> var = null;
		int cost_var = (Integer) null;
		for (int i =1; i<jobs.size(); i++) {
			for (int j=0; j<jobs.size(); j++) {
				var = Neigthboor.select(jobs,i,j,neightboor);
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
	
}
