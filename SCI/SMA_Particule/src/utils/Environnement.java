package utils;

import agent.Agent;


public class Environnement {
	private Agent[][] env;
	
	public Environnement(int L, int l) {
		this.env = new Agent[l][L];
	}

	public Agent[][] getEnv() {
		return env;
	}
	public Agent getAgent(int i, int j) {
		return env[i][j];
	}

	public void setEnv(Agent[][] env) {
		this.env = env;
	}

	public void putAgent(Agent a, int i, int j) {
		this.env[i][j] = a;
	}
	
	public void deleteAgent(int i, int j) {
		this.env[i][j] = null;
	}
}
