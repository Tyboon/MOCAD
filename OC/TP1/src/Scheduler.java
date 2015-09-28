import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class Scheduler {
	
	public static ArrayList<int[]> RDM(int[][] jobs) {
		Random rand = new Random();
		ArrayList<int[]> list = new ArrayList<int[]>();
		boolean[] bool = new boolean[125];
		int[] tmp = null;
		while (list.size() <= jobs.length) {
			int r = rand.nextInt(jobs.length);
			if (!bool[r]) {
				tmp = jobs[r];
				list.add(tmp);
			}
		}
		return list;
	}
	
	public static ArrayList<int[]> EDD (int[][] jobs) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Arrays.sort(jobs, new Comparator<int[]>() {
		    public int compare(int[] s1, int[] s2) {
		        int num1 = s1[2];
		        int num2 = s2[2];
		        return Integer.compare(num1, num2);
		    }
		});
		list = new ArrayList<int[]>(Arrays.asList(jobs));
		return list;
	}
	
	
	public static ArrayList<int[]> MDD (int[][] jobs) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		int min = Integer.MAX_VALUE;
		int ind = -1;
		int compl = 0;
		int tmp = 0;
		
		while (list.size() < jobs.length) {
			min = Integer.MAX_VALUE;
			ind = -1;
			for (int i =0; i<jobs.length; i++) {
				if (!list.contains(jobs[i])) {
					tmp = Math.max(compl+jobs[i][0], jobs[i][2]);
					if (tmp < min) {
						min = tmp;
						ind = i;
					}
				}
			}
			list.add(jobs[ind]);
			compl += jobs[ind][0];
		}
		return list;
	}
	
	public static ArrayList<int[]> schedule(int[][] jobs, String scheduler) {
		switch (scheduler) {
		case "RDM" : 
			return RDM(jobs);
		case "EDD" :
			return EDD(jobs);
		case "MDD" :
			return MDD(jobs);
		default :
			System.out.println("Error : Unknow scheduler");
			System.exit(0);
		}
		return null;
	}
	
	
	public static int cost(ArrayList<int[]> list) {
		int compl = 0;
		int cost = 0;
		int[] tmp = null;
		int T = 0;
		for (int i = 0; i <list.size(); i++) {
			tmp = list.get(i);
			compl += tmp[0];
			T = Math.max((compl-tmp[2]), 0);
			cost += T * tmp[1];
		}
		return cost;
	}
}
