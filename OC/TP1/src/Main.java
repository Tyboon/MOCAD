import java.util.ArrayList;
import java.util.Arrays;



public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ; // WARNING : décalage de 1 au parsing! 
//		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
//		ArrayList<int[]> tmp = new ArrayList<int[]>();
//		ArrayList<Integer> cost = new ArrayList<Integer>();
		
	
		
		HillClimbing hillC = new HillClimbing("INSERT", true, "MDD");
//		ArrayList<int[]> tmp1 = hillC.launch(instances[1]);
//		System.out.println(Scheduler.cost(tmp1));
//		
		ArrayList<int[]> tmp1 = hillC.launch(instances[1]);
		for (int[] i : tmp1)
			System.out.println(i[0] + " "+i[1]+" "+i[2]);
		
		System.out.println("cost : " +Scheduler.cost(tmp1));
		
		
		String[] neighbor = {"EXCHANGE","INSERT","SWAP"};
		VND vnd = new VND(neighbor, true, "EDD");
		PipedVND pvnd = new PipedVND(neighbor, true , "EDD");
//
//		ArrayList<int[]> tmp2 = vnd.launch(instances[1]);
//		System.out.println(Scheduler.cost(tmp2));

//		long t,t1,t2, t3;
//		int i = 0;
//		
//		
//		for (Instance inst : instances) {
//			i++;
//			t = System.currentTimeMillis();
//			ArrayList<int[]> tmp1 = hillC.launch(inst);
//			t1 = System.currentTimeMillis() - t;
//			System.out.println(tmp1.size());
//		
//			System.currentTimeMillis();
//			ArrayList<int[]> tmp2 = vnd.launch(inst);
//			t2 = System.currentTimeMillis() - t;
//		
//			System.currentTimeMillis();
//			ArrayList<int[]> tmp3 = pvnd.launch(inst);
//			t3 = System.currentTimeMillis() - t;
//		
//			System.out.println("INSTANCE N° " + (i-1));
//			System.out.println("Result HillClimbing: "+ Scheduler.cost(tmp1) + " in " + t1 + " msec");
//			System.out.println("Result VND: "+ Scheduler.cost(tmp2) + " in " + t2 + " msec");
//			System.out.println("Result PIPED VND: "+ Scheduler.cost(tmp3) + " in " + t3 + " msec");
//		}
		
//		for (int[] i : instances[4].jobs)
//			System.out.println(i[0] + " "+i[1]+" "+i[2]);
		
//		ArrayList<int[]> tmp1 = hillC.launch(instances[1]);
//		System.out.println(Scheduler.cost(tmp1));
//		ArrayList<int[]> tmp2 = Scheduler.EDD(instances[1].jobs);
//		System.out.println(Scheduler.cost(tmp2));
//		ArrayList<int[]> tmp3 = Scheduler.MDD(instances[1].jobs);
//		System.out.println(Scheduler.cost(tmp3));
//		ArrayList<int[]> tmp4 = Scheduler.RDM(instances[1].jobs);
//		System.out.println(Scheduler.cost(tmp4));
	}
}

