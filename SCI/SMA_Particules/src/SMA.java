import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;


public class SMA extends Observable {
	private Environnement env;
	private ArrayList<Bille> agents;
	private int nbTour;
	private int ralent;
	private Vue vue;
	
	public void init(int L, int l, int nb, int r, Vue v) {
		this.env = new Environnement(l, L);
		this.agents = new ArrayList<Bille>();
		this.nbTour = nb;
		this.ralent = r;
		this.vue = v;
	}
	
	public void add(Bille b) {
		this.agents.add(b);
	}
	
	public Environnement getEnv() {
		return this.env;
	}
	
	public void run() throws InterruptedException {
		while (this.nbTour >= 0) {
			//Collections.shuffle(this.agents);
			for (Bille b : this.agents) {
				Thread.sleep(this.ralent);
				b.decide();
			}
			this.setChanged();
			this.nbTour--;
		}
	}

}
