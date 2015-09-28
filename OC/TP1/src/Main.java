import java.util.ArrayList;



public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ; // WARNING : décalage de 1 au parsing! 
//		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
//		ArrayList<int[]> tmp = new ArrayList<int[]>();
//		ArrayList<Integer> cost = new ArrayList<Integer>();
		HillClimbing hillC = new HillClimbing("SWAP", true, "EDD");
		
		String[] neighbor = {"EXCHANGE","SWAP","INSERT"};
		VND vnd = new VND(neighbor, true, "EDD");
		
		PipedVND pvnd = new PipedVND(neighbor, true , "EDD");
		
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
		
		
		for (int i = 0; i < 5; i++) {
			t = System.currentTimeMillis();
			ArrayList<int[]> tmp1 = hillC.launch(instances[i]);
			t1 = System.currentTimeMillis() - t;
			System.out.println(tmp1.size());
		
			System.currentTimeMillis();
			ArrayList<int[]> tmp2 = vnd.launch(instances[i]);
			t2 = System.currentTimeMillis() - t;
		
			System.currentTimeMillis();
			ArrayList<int[]> tmp3 = pvnd.launch(instances[i]);
			t3 = System.currentTimeMillis() - t;
		
			System.out.println("INSTANCE N° " + (i-1));
			System.out.println("Result HillClimbing: "+ Scheduler.cost(tmp1) + " in " + t1 + " msec");
			System.out.println("Result VND: "+ Scheduler.cost(tmp2) + " in " + t2 + " msec");
			System.out.println("Result PIPED VND: "+ Scheduler.cost(tmp3) + " in " + t3 + " msec");
		}
		**/
		ArrayList<int[]> tmp1 = hillC.launch(instances[3]);
		System.out.println(Scheduler.cost(tmp1));
		ArrayList<int[]> tmp2 = Scheduler.EDD(instances[3].jobs);
		for (int[] i : tmp1)
			System.out.println(i[0] + " "+i[1]+" "+i[2]);
		System.out.println(Scheduler.cost(tmp2));
	}
}

