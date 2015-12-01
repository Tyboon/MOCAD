package smtwtp;
import java.util.ArrayList;


public class Instance {
	
	public ArrayList<int[]> jobs;
	
	public Instance() {
		this.jobs = new ArrayList<int[]>();
	}
	
	public void add_process(int j, int p) {
		jobs.add(new int[3]);
		jobs.get(j)[0] = p;
	}
	
	public void add_weigth(int j, int w) {
		jobs.get(j)[1] = w;
	}
	
	public void add_due(int j, int d) {
		jobs.get(j)[2] = d;
	}
	
	
}
