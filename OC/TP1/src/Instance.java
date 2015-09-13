
public class Instance {
	
	public int number;
	public int[][] jobs;
	
	public Instance(int nb) {
		this.number = nb;
		this.jobs = new int[nb][3];
	}
	
	public void add_process(int j, int p) {
			jobs[j][0] = p;
	}
	
	public void add_weigth(int j, int w) {
		jobs[j][1] = w;
	}
	
	public void add_due(int j, int d) {
		jobs[j][2] = d;
	}
	
	
}
