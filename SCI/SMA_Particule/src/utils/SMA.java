package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import view.Panel;
import agent.Agent;
import agent.Attractor;

public class SMA extends Thread {
	private Environnement env;
	private List<Agent> agents;
	public int nbAttractor;
	public int[][] radar;
	public int nbFish;
	public int nbShark;
	public List<Agent> dead = new ArrayList<Agent>();
	private int nbTour;
	private int ralent;
	private Panel panel;
//	private boolean torique;
	public ArrayList<Attractor> attractor;

	public SMA(int nbFish, int nbShark) {
		this.nbFish = nbFish;
		this.nbShark = nbShark;
	}
	
	public SMA(int attr) {
		this.attractor = new ArrayList<Attractor>();
		this.nbAttractor = attr; 
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

	public void removeAttractor(Attractor a) {
		this.agents.remove(a);
		this.attractor.remove(a);
		this.dead.add(a);
	}
	
	public Environnement getEnv() {
		return this.env;
	}
	
	public List<Agent> getAgents() {
		return this.agents;
	}

	public void run() {
		while (this.nbTour >= 0) {
			
			Collections.shuffle(this.agents);
			
			for (Agent a : new ArrayList<Agent>(this.agents)) {
					a.decide();
			}
			try {
				Thread.sleep(this.ralent);
			} catch (Exception ignored) {
			}
			panel.repaint();
			this.nbTour--;
		}
	}
	
	public void runWator() {
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
	
	public void runCatcher() {
		this.initRadar();
		while (this.nbTour >= 0 && this.nbAttractor > 0) {
			
			Collections.shuffle(this.agents);
			
			for (Agent a : new ArrayList<Agent>(this.agents)) {
				if (! dead.contains(a))
					a.decide();
			}
			
			try {
				Thread.sleep(this.ralent);
			} catch (Exception ignored) {
			}
			//refresh radar
			panel.repaint();
			this.nbTour--;
		}
		System.out.println(this.agents.size());
	
		
	}

	private void initRadar() {
		HashMap<Integer, ArrayList<Integer>> hash = new HashMap<Integer, ArrayList<Integer>>();
		this.radar = new int[this.env.getEnv().length][this.env.getEnv()[0].length];
		for (Attractor at : attractor) {
			this.pushDistance(at.getX(), at.getY(), this.radar, hash);
		}
	}

	private void pushDistance(int x, int y, int[][] r, HashMap<Integer, ArrayList<Integer>> hash) {
		if (hash.containsKey(x)) {
			hash.get(x).add(y);
		} else {
			hash.put(x, new ArrayList<Integer>());
			hash.get(x).add(y);
		}
		putDistance(x+1, y-1, r, x, y, hash);
		putDistance(x+1, y, r, x, y, hash);
		putDistance(x+1, y+1, r, x, y, hash);
		putDistance(x, y+1, r, x, y, hash);
		putDistance(x, y-1, r, x, y, hash);
		putDistance(x-1, y-1, r, x, y, hash);
		putDistance(x-1, y, r, x, y, hash);
		putDistance(x-1, y+1, r, x, y, hash);
	}

	private void putDistance(int x, int y, int[][] r, int x_or, int y_or, HashMap<Integer, ArrayList<Integer>> hash) {
		if ((x < r.length) && (y < r[0].length) && (x >= 0) && (y >= 0) && ((hash.containsKey(x) && ! hash.get(x).contains(y))) || ! hash.containsKey(x)) {
			if (r[x][y] != 0) {
				r[x][y] = Math.min(r[x][y], r[x_or][y_or]+1);
				if(r[x][y] == r[x_or][y_or]+1)
					this.pushDistance(x, y, r, hash);
			} else {
				if (this.env.getAgent(x, y) == null) {
					r[x][y] = r[x_or][y_or]+1;
					this.pushDistance(x, y, r, hash);
				}
			}
		}
		
	}
}