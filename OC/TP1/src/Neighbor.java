import java.util.ArrayList;


public class Neighbor {

	@SuppressWarnings("unchecked")
	public static ArrayList<int[]> insert (ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = (ArrayList<int[]>) job.clone();
		int[] tmp = jobs.get(j);
		for (int i =k; i<j; i++) {
			jobs.set(i+1,jobs.get(i));
			System.out.println("Sel" + i + j);
		}
		jobs.set(k,tmp);
		return jobs;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<int[]> swap(ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = (ArrayList<int[]>) job.clone();
		int[] tmp = jobs.get(j);
		jobs.set(j,jobs.get(k));
		jobs.set(k,tmp);
		return jobs;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<int[]> exchange(ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = (ArrayList<int[]>) job.clone();
		int[] tmp = null;
		for (int i = 0; i<(k-j)/2; i++) {
			tmp = jobs.get(j+i);
			jobs.set(j+i,jobs.get(k-i));
			jobs.set(k-i,tmp);
		}
		return jobs;
	}

	
	public static ArrayList<int[]> select(ArrayList<int[]> jobs, int i, int j, String neighbor) {
		switch (neighbor) {
			case "INSERT" : 
				return insert(jobs, i, j);
			case "SWAP" :
				return swap(jobs,i,j);
			case "EXCHANGE" :
				return exchange(jobs,i,j);
		}
		System.out.println("Error : Unknow neighbor");
		System.exit(0);
		return null;
	}
}
