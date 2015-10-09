package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import view.Panel;
import agent.Agent;

public class SMA extends Thread {
	private Environnement env;
	private List<Agent> agents;
	public int nbFish;
	public int nbShark;
	public List<Agent> dead = new ArrayList<Agent>();
	private int nbTour;
	private int ralent;
	private Panel panel;
//	private boolean torique;

	public SMA(int nbFish, int nbShark) {
		this.nbFish = nbFish;
		this.nbShark = nbShark;
	}

	public SMA() {;}

	public void init(int L, int l, int nb, int r, Panel p) {
		this.env = new Environnement(l, L);
		this.agents = new ArrayList<Agent>();
		this.nbTour = nb;
		this.ralent = r;
		this.panel = p;
//		this.torique = tor;
	}

	public void add(Agent a) {
		this.agents.add(a);
	}
	
	public void remove(Agent a) {
		this.agents.remove(a);
		this.dead.add(a);
	}

	public Environnement getEnv() {
		return this.env;
	}
	
	public List<Agent> getAgents() {
		return this.agents;
	}

	public void run() {
		ArrayList<Integer> fishes = new ArrayList<Integer>();
		ArrayList<Integer> sharks = new ArrayList<Integer>();
		while (this.nbTour >= 0 && this.nbFish != 0 && this.nbShark != 0) {
			
			fishes.add(this.nbFish);
			sharks.add(this.nbShark);
			Collections.shuffle(this.agents);
			
			for (Agent a : new ArrayList<Agent>(this.agents)) {
				if (! dead.contains(a))
					a.decide(); // decide(torique)
			}
			
			try {
				Thread.sleep(this.ralent);
			} catch (Exception ignored) {
			}
			
			panel.repaint();
			this.nbTour--;
		}
		System.out.println(this.nbFish);
		System.out.println(this.nbShark);
		System.out.println(this.agents.size());
		Writer.write("data/fishes.txt",fishes);
		Writer.write("data/sharks.txt",sharks);
		
	}
}