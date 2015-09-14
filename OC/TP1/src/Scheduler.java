import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class Scheduler {
	
	public ArrayList<int[]> random_instance(int[][] jobs) {
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
	
	public ArrayList<int[]> EDD (int[][] jobs) {
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
	
	int comp = 0;
	
	public ArrayList<int[]> MDD (int[][] jobs) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Arrays.sort(jobs, new Comparator<int[]>() {
		    public int compare(int[] s1, int[] s2) {
		        int num1 = Math.max(comp + s1[0], s1[3]);
		        int num2 = Math.max(comp + s2[0], s2[3]);
		        return Integer.compare(num1, num2);
		    }
		});
		list = new ArrayList<int[]>(Arrays.asList(jobs));
		return list;
	}
	
	public int cost(ArrayList<int[]> list) {
		int compl = 0;
		int cost = 0;
		int[] tmp = null;
		int T = 0;
		
		for (int i = 0; i <list.size(); i++) {
			tmp = list.get(i);
			compl += tmp[0];
			T = Math.max(compl-tmp[2], 0);
			cost += T * tmp[1];
		}
		return cost;
	}
}
