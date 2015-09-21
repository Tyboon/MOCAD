import java.util.ArrayList;


public class Neigthboor {

	public int[][] insert (int[][] jobs, int j, int k) {
		int[] tmp = jobs[j];
		for (int i =k; i<j; i++) 
			jobs[i+1] = jobs[i];
		jobs[k] = tmp;
		return jobs;
	}
	
	public int[][] swap(int[][] jobs, int j, int k) {
		int[] tmp = jobs[j];
		jobs[j] = jobs[k];
		jobs[k] = tmp;
		return jobs;
	}
	
	public int[][] exchange(int[][] jobs, int j, int k) {
		int[] tmp = null;
		for (int i = 0; i<(k-j)/2; i++) {
			tmp = jobs[j+i];
			jobs[j+i] = jobs[k-i];
			jobs[j+i] = tmp;
		}
		return jobs;
	}

	public static ArrayList<int[]> select(ArrayList<int[]> jobs, int i, int j,
			String neightboor) {
		// TODO Auto-generated method stub
		return null;
	}
}
