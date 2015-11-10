import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> list1 = (ArrayList<Integer>) Parser.parse("data/randomA100.tsp");
		ArrayList<Integer> list2 = (ArrayList<Integer>) Parser.parse("data/randomB100.tsp");
		
		Instance inst  = new Instance(list1, list2);
		for (int[] i: inst.getNodes()) {
			System.out.println(i[0] + " - " + i[1]);
		}
	}
}
