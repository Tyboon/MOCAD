package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import view.*;

import agent.Agent;

public class SMA extends Thread {
	private Environnement env;
	private ArrayList<Agent> agents;
	private int nbTour;
	private int ralent;
	private Panel panel;

	public void init(int L, int l, int nb, int r, Panel p) {
		this.env = new Environnement(l, L);
		this.agents = new ArrayList<Agent>();
		this.nbTour = nb;
		this.ralent = r;
		this.panel = p;
	}

	public void add(Agent a) {
		this.agents.add(a);
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
			for (Agent a: this.agents) {
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
}