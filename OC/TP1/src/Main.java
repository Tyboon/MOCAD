import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ;
		/**
		for (Instance inst : instances) {
			for (int[] job : inst.jobs) {
				System.out.println("Process time : "+job[0]+ " ; weight : "+job[1]+" ; due time : "+job[2]);
			}
			System.out.println();
		} **/
		Scheduler sche = new Scheduler();
		ArrayList<int[]> list = null;
		int[] job = null;
		
		for (Instance inst : instances) {
			list = (ArrayList<int[]>) sche.MDD(inst.jobs);
			/**for (int i =0; i<list.size(); i++) {
				job = (int[]) list.get(i);
				System.out.println("Process time : "+job[0]+ " ; weight : "+ job[1] + " ; due time : " + job[2]);	
			}
			System.out.println();**/
			System.out.println("Cost random : " + sche.cost(list));
		}
		
	}
}
