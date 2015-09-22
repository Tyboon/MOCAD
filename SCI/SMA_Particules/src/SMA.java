import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SMA extends Thread {
	private Environnement env;
	private ArrayList<Bille> agents;
	private int nbTour;
	private int ralent;
	private Panel panel;

	public void init(int L, int l, int nb, int r, Panel p) {
		this.env = new Environnement(l, L);
		this.agents = new ArrayList<Bille>();
		this.nbTour = nb;
		this.ralent = r;
		this.panel = p;
	}

	public void add(Bille b) {
		this.agents.add(b);
	}

	public Environnement getEnv() {
		return this.env;
	}

	public void run() {
		while (this.nbTour >= 0) {
			Collections.shuffle(this.agents);
			for (Bille b : this.agents) {
				b.decide();
			}
			try {
				Thread.sleep(this.ralent);
			} catch (Exception ignored) {
			}
			panel.repaint();
			this.nbTour--;
		}
	}

	public List<Bille> getAgents() {
		return this.agents;
	}

}
