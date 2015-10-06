package agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Environnement;

public abstract class Agent {
	
	protected int x;
	protected int y;
	protected int pasX;
	protected int pasY;
	protected Environnement env;
	protected Color color;
	
	
	public Agent(Environnement env) {
		
		this.env = env;
		Random rand = new Random();

		//TODO VERIFIER SI NON COLLISION
		
		int tmp1 = rand.nextInt(env.getEnv().length-1);
		int tmp2 = rand.nextInt(env.getEnv()[0].length-1);
		
		this.x = tmp1;
		this.y = tmp2;
		
		this.pasX = rand.nextInt(3)-1; // -1, 0 ou 1
		this.pasY = rand.nextInt(3)-1;	
	}
	
public Agent(Environnement env, int x, int y) {
		
		this.env = env;
		Random rand = new Random();
		this.x = x;
		this.y = y;
		this.pasX = rand.nextInt(3)-1; // -1, 0 ou 1
		this.pasY = rand.nextInt(3)-1;	
	}
	
	public int getX() {
		return x;
	}

	
	public int getPasX() {
		return pasX;
	}


	public int getPasY() {
		return pasY;
	}


	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public abstract void decide() ;

		
	public List<Agent> getNeighbor() {
		ArrayList<Agent> neighbor = new ArrayList<Agent>();
		Agent n = null;
		if ((n = env.getAgent(this.x-1, this.y-1)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x, this.y-1)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x-1, this.y)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x-1, this.y+1)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x, this.y+1)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x+1, this.y-1)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x+1, this.y)) != null)
			neighbor.add(n);
		if ((n = env.getAgent(this.x+1, this.y+1)) != null)
			neighbor.add(n);
		return neighbor;
	}
	

}
