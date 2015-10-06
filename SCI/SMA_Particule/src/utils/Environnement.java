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
		try {
			return env[i][j];
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void setEnv(Agent[][] env) {
		this.env = env;
	}

	public boolean putAgent(Agent a, int i, int j) {
		try {
			this.env[i][j] = a;
			return true;
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void deleteAgent(int i, int j) {
		this.env[i][j] = null;
	}
}
