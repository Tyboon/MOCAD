package smtwtp;
import java.util.ArrayList;
import java.util.Arrays;


public class Neighbor {
	
	public static ArrayList<int[]> kopt(ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = new ArrayList<int[]>(job);
		int[] tmp = new int[3];
		if (j < k) {
			int t = j;
			j = k;
			k = t;
		}
		for (int i = 0; i < (j-k); i++) {
			tmp = Arrays.copyOf( jobs.get(k+i), 3 );
			jobs.set( (k+i), Arrays.copyOf(jobs.get(j-i), 3) );
			jobs.set( (j-i), tmp );
		}
		return jobs;
	}

	public static ArrayList<int[]> insert (ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = new ArrayList<int[]>(job);
		ArrayList<int[]> fin = new ArrayList<int[]>();
		int i = 0;
		int[] tmp = Arrays.copyOf(jobs.get(j), 3);
		if (k < j) {
			for (; i < k; i++) fin.add(jobs.get(i));
			fin.add(tmp);
			for (i = k; i < j; i++) fin.add(jobs.get(i));
			for (i = j+1; i < jobs.size(); i++) fin.add(jobs.get(i));
		} else if (k != j){
			for (; i < j; i++) fin.add(jobs.get(i));
			for (i = j+1; i < k; i++) fin.add(jobs.get(i));
			fin.add(tmp);
			for (i = k; i < jobs.size(); i++) fin.add(jobs.get(i));
		} else {
			return jobs;
		}
		return fin;
	}
	
	public static ArrayList<int[]> swap(ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = new ArrayList<int[]>(job);
		int[] tmp = jobs.get(j);
		jobs.set(j,jobs.get(k));
		jobs.set(k,tmp);
		return jobs;
	}
	
	public static ArrayList<int[]> exchange(ArrayList<int[]> job, int j, int k) {
		ArrayList<int[]> jobs = new ArrayList<int[]>(job);
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
