import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Scheduler {
	
	public static ArrayList<int[]> RDM(ArrayList<int[]> inst) {
		Random rand = new Random();
		ArrayList<int[]> list = new ArrayList<int[]>();
		boolean[] bool = new boolean[125];
		int[] tmp = null;
		while (list.size() <= inst.size()) {
			int r = rand.nextInt(inst.size());
			if (!bool[r]) {
				tmp = inst.get(r);
				list.add(tmp);
			}
		}
		return list;
	}
	
	public static ArrayList<int[]> EDD (ArrayList<int[]> inst) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Collections.sort(inst, new Comparator<int[]>() {
		    public int compare(int[] s1, int[] s2) {
		        int num1 = s1[2];
		        int num2 = s2[2];
		        return Integer.compare(num1, num2);
		    }
		});
		list = new ArrayList<int[]>(inst);
		return list;
	}
	
	
	public static ArrayList<int[]> MDD (ArrayList<int[]> inst) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		int min = Integer.MAX_VALUE;
		int ind = -1;
		int compl = 0;
		int tmp = 0;
		
		while (list.size() < inst.size()) {
			min = Integer.MAX_VALUE;
			ind = -1;
			for (int i =0; i<inst.size(); i++) {
				if (!list.contains(inst.get(i))) {
					tmp = Math.max(compl+inst.get(i)[0], inst.get(i)[2]);
					if (tmp < min) {
						min = tmp;
						ind = i;
					}
				}
			}
			list.add(inst.get(ind));
			compl += inst.get(ind)[0];
		}
		return list;
	}
	
	public static ArrayList<int[]> schedule(ArrayList<int[]> inst, String scheduler) {
		switch (scheduler) {
		case "RDM" : 
			return RDM(inst);
		case "EDD" :
			return EDD(inst);
		case "MDD" :
			return MDD(inst);
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
