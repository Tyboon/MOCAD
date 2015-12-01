

public class Main {
	public static void main(String[] args) {
		int[] tab1 = Parser.parse("data/randomA100.tsp", 100);
		int[] tab2 =  Parser.parse("data/randomB100.tsp", 100);
		
		Instance inst  = new Instance(tab1, tab2, 100);
		for (int[] i: inst.getNodes()) {
			System.out.println(i[0] + " - " + i[1]);
		}
	}
}
