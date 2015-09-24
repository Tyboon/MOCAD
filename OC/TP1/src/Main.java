import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ;
		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
		ArrayList<int[]> tmp = new ArrayList<int[]>();
		ArrayList<Integer> cost = new ArrayList<Integer>();
		HillClimbing hillC = new HillClimbing("SWAP", false, "EDD");
		int i = 0; 
		
		
		for (Instance inst : instances) {
			tmp = hillC.launch(inst);
			list.add(tmp);
			cost.add(Scheduler.cost(tmp));
			System.out.println("i : " + i );
			i++;
		} 
		
//		ArrayList<int[]> tmp = hillC.launch(instances[1]);
//		System.out.println("Result : "+ Scheduler.cost(tmp));
	}
}

