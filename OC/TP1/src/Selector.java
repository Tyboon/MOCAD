import java.util.ArrayList;


public class Selector {

	public int[] select_first(ArrayList<int[]> jobs, String neightboor) {
		ArrayList<int[]> tmp = jobs;
		int[] tab = null;
		int cost = Scheduler.cost(jobs);
		for (int i =1; i<jobs.size(); i++) {
			for (int j=0; j<jobs.size(); j++) {
				tmp = Neigthboor.select(jobs,i,j,neightboor);
				if (Scheduler.cost(tmp) < cost){
					return tmp, Scheduler.cost(tmp);
				}
			}
		}
	}
}
