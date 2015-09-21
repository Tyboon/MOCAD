import java.util.ArrayList;


public class Neigthboor {

	public static ArrayList<int[]> insert (ArrayList<int[]> jobs, int j, int k) {
		int[] tmp = jobs.get(j);
		for (int i =k; i<j; i++) 
			jobs.add(i+1,jobs.get(i));
		jobs.add(k,tmp);
		return jobs;
	}
	
	public static ArrayList<int[]> swap(ArrayList<int[]> jobs, int j, int k) {
		int[] tmp = jobs.get(j);
		jobs.add(j,jobs.get(k));
		jobs.add(k,tmp);
		return jobs;
	}
	
	public static ArrayList<int[]> exchange(ArrayList<int[]> jobs, int j, int k) {
		int[] tmp = null;
		for (int i = 0; i<(k-j)/2; i++) {
			tmp = jobs.get(j+i);
			jobs.add(j+i,jobs.get(k-i));
			jobs.add(k-i,tmp);
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
