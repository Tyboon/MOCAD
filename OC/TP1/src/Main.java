import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ;
		//ArrayList<ArrayList<int[]>> list = null;
		ArrayList<int[]> tmp = null;
		//ArrayList<Integer> cost = new ArrayList<Integer>();
		HillClimbing hillC = new HillClimbing("INSERT", false, "RDM");
		//int i = 0; 
		
		/*
		for (Instance inst : instances) {
			tmp = hillC.launch(inst);
			list.add(tmp);
			cost.add(Scheduler.cost(tmp));
			System.out.println("i : " + Scheduler.cost(tmp));
			i++;
		} */
		tmp = hillC.launch(instances[0]);
		System.out.println("COST ");
		System.out.println("Result : "+ Scheduler.cost(tmp));
	}
}

