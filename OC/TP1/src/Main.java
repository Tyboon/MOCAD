
public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Instance[] instances = parser.parse("wt100.txt", 100) ;
		for (Instance inst : instances) {
			for (int[] job : inst.jobs) {
				System.out.println("Process time : "+job[0]+ " ; weight : "+job[1]+" ; due time : "+job[2]);
			}
			System.out.println();
		}
	}
	
}
