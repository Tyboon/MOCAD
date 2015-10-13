package agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import utils.Environnement;
import utils.SMA;

public class Catcher extends Agent {

	private SMA sma;
	
	public Catcher(Environnement env, SMA sma) {
		super(env);
		this.color = Color.RED;
		this.sma = sma;
	}

	
	public void decide() {
		ArrayList<Agent> neighbor = (ArrayList<Agent>) getNeighbor();
		Attractor attr = null;
		
		// Si trouve un attractor l'attrape
		if ((attr = containsAttr(neighbor)) != null) {
			this.env.deleteAgent(this.x, this.y);
			this.x = attr.getX();
			this.y= attr.getY();
			this.catched(attr);
			this.env.putAgent(this, this.x, this.y);
		} else {
			this.move();
		}
		return;
	}
	
	public void move() {
		int x = this.x, y = this.y, min = Integer.MAX_VALUE;
		if (this.isAvailable(this.x-1, this.y-1, min)) { x = this.x-1; y = this.y-1; min = this.sma.radar[this.x-1][this.y-1];}
		if (this.isAvailable(this.x-1, this.y, min)) { x = this.x-1; y = this.y; min = this.sma.radar[this.x-1][this.y];}
		if (this.isAvailable(this.x-1, this.y+1, min)) { x = this.x-1; y = this.y+1; min = this.sma.radar[this.x-1][this.y+1];}
		if (this.isAvailable(this.x, this.y-1, min)) { x = this.x; y = this.y-1; min = this.sma.radar[this.x][this.y-1];}
		if (this.isAvailable(this.x, this.y+1, min)) { x = this.x; y = this.y+1; min = this.sma.radar[this.x][this.y+1];}
		if (this.isAvailable(this.x+1, this.y-1, min)) { x = this.x+1; y = this.y-1; min = this.sma.radar[this.x+1][this.y-1];}
		if (this.isAvailable(this.x+1, this.y, min)) { x = this.x+1; y = this.y; min = this.sma.radar[this.x+1][this.y];}
		if (this.isAvailable(this.x+1, this.y+1, min)) { x = this.x+1; y = this.y+1; min = this.sma.radar[this.x+1][this.y+1];}

	}
	
	//Non pris, non en dehors des bornes, plus petit que min 
	public boolean isAvailable(int x, int y, int min) {
		return ( (this.env.getAgent(x, y) != null) && x >= 0 && y >= 0 && x < this.env.getEnv().length && y < this.env.getEnv()[0].length && min > this.sma.radar[x][y]);
	}
	
	public Attractor containsAttr(ArrayList<Agent> neighbor) {
		for (Agent agent : neighbor) {
			if (agent instanceof Attractor) {
				return (Attractor) agent;
			}
		}
		return null;
	}
	
	public void catched(Attractor attr) {
		this.env.deleteAgent(attr.getX(), attr.getY());
		this.sma.remove(attr);
		this.sma.nbAttractor--;
	}
}
