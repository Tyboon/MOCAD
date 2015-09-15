import java.util.ArrayList;
import java.util.Collections;


public class SMA {
	private Environnement env;
	private ArrayList<Bille> agents;
	private int nbTour;
	private int ralent;
	private Vue vue;
	
	public void init(int L, int l, int nb, int r) {
		this.env = new Environnement(L, l);
		this.agents = new ArrayList<Bille>();
		this.nbTour = nb;
		this.ralent = r;
	}
	
	public void add(Bille b) {
		this.agents.add(b);
	}
	
	public void Run() throws InterruptedException {
		while (this.nbTour >= 0) {
			Collections.shuffle(this.agents);
			for (Bille b : this.agents) {
				Thread.sleep(this.ralent);
				b.decide();
			}
			this.setChanged();
		}
	}

	private void setChanged() {
		this.vue.setChanged(this.agents);
		
	}
}
