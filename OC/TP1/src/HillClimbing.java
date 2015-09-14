import java.util.ArrayList;


public class HillClimbing {

	private String neigthboor = "INSERT"; // || SWAP || EXCHANGE
	private String select = "FIRST"; // || BEST
	private String init = "RDM"; // EDD || MDD
	private Scheduler sched = new Scheduler();
	
	public HillClimbing(String neigth, String sel, String ini) {
		this.neigthboor = neigth;
		this.select = sel;
		this.init = ini;
	}
	
	public ArrayList<int[]> launch (Instance inst) {
		// INIT
		ArrayList<int[]> list = new ArrayList<int[]>();
		switch (this.init) {
			case "RDM" : 
				list = sched.random_instance(inst.jobs);
				break;
			case "EDD" :
				list = sched.EDD(inst.jobs);
				break;
			default :
				list = sched.MDD(inst.jobs);
				break;
		}
		return list;
		// SELECT VOISIN
		
		
		// BOUCLE TANT QUE MEILLEUR
	}
	
}
