import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ;
//		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
//		ArrayList<int[]> tmp = new ArrayList<int[]>();
//		ArrayList<Integer> cost = new ArrayList<Integer>();
		HillClimbing hillC = new HillClimbing("SWAP", false, "EDD");
		
		String[] neighbor = {"EXCHANGE","SWAP","INSERT"};
		VND vnd = new VND(neighbor, false, "EDD");
		
		PipedVND pvnd = new PipedVND(neighbor, false, "EDD");
		
		long t,t1,t2, t3;
		/**
		int i = 0; 
		
		
		
		for (Instance inst : instances) {
			t = System.currentTimeMillis();
			tmp = vnd.launch(inst);
			//list.add(tmp);
			//cost.add(Scheduler.cost(tmp));
			System.out.println("i : " + i + " " + (System.currentTimeMillis()-t) );
			i++;
		} 
		**/
		t = System.currentTimeMillis();
		ArrayList<int[]> tmp1 = hillC.launch(instances[3]);
		t1 = System.currentTimeMillis() - t;
		
		System.currentTimeMillis();
		ArrayList<int[]> tmp2 = vnd.launch(instances[3]);
		t2 = System.currentTimeMillis() - t;
		
		System.currentTimeMillis();
		ArrayList<int[]> tmp3 = pvnd.launch(instances[3]);
		t3 = System.currentTimeMillis() - t;
		
		System.out.println("Result HillClimbing: "+ Scheduler.cost(tmp1) + " in " + t1 + " msec");
		System.out.println("Result VND: "+ Scheduler.cost(tmp2) + " in " + t2 + " msec");
		System.out.println("Result PIPED VND: "+ Scheduler.cost(tmp3) + " in " + t3 + " msec");

	}
}

