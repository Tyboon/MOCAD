package agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import utils.Environnement;
import utils.SMA;

public class Fish extends Agent{

	private int birth;
	private int cpt_birth = 0;
	private SMA sma;
	
	public Fish(Environnement env, SMA sma, int birthFish) {
		super(env);
		this.birth = birthFish;
		this.sma = sma;
		this.color = Color.BLUE;
	}

	public Fish(Environnement env, SMA sma, int birthFish, int x, int y) {
		super(env, x, y);
		this.birth = birthFish;
		this.sma = sma;
		this.color = Color.BLUE;
	}
	
	public void decide() {
		ArrayList<Agent> neighbor = (ArrayList<Agent>) getNeighbor();
		Agent[][] tab = this.env.getEnv();
		Random rand = new Random();
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		int x_born, y_born;
		
		
		// Si on est dans le cadre
		if ((this.x < tab.length-1) && (this.y < tab[0].length-1) && (0 < this.x) && (0 < this.y)) { 
			if (neighbor.size() < 8) {
				x_fut = this.x + this.getPasX()*(-1);
				y_fut = this.y + this.getPasY()*(-1);
				while ((x_fut > tab.length-1) || (y_fut > tab[0].length-1) || (0 > x_fut) || (0 > y_fut) || (env.getEnv()[x_fut][y_fut] != null)) {
					this.pasX = rand.nextInt(3)-1;
					this.pasY = rand.nextInt(3)-1;
					x_fut = this.x + this.pasX;
					y_fut = this.y + this.pasY;
				}
			} else {
					x_fut = this.x;
					y_fut = this.y;
			}
		} else {
		// Si on dépasse le cadre
			if (this.x == 0 || this.x == tab.length-1)
				this.pasX = this.pasX * (-1);
			else
				this.pasY = this.pasY * (-1);
			x_fut = this.x + this.pasX;
			y_fut = this.y + this.pasY;
			int cpt = 0;
			while (((x_fut > tab.length-1) || (y_fut > tab[0].length-1) || (0 > x_fut) || (0 > y_fut) || (env.getEnv()[x_fut][y_fut] != null)) && (cpt < 5)) {
				this.pasX = rand.nextInt(3)-1;
				this.pasY = rand.nextInt(3)-1;
				x_fut = this.x + this.pasX;
				y_fut = this.y + this.pasY;
				cpt++;
			}
			if ((x_fut > tab.length-1) || (y_fut > tab[0].length-1) || (0 > x_fut) || (0 > y_fut) || (env.getEnv()[x_fut][y_fut] != null)) {
				x_fut = this.x;
				y_fut = this.y;
			}
		}
		
		if (this.cpt_birth >= this.birth && (this.x != x_fut && this.y != y_fut)) {
			x_born = this.x;
			y_born = this.y;
			
			this.x = x_fut;
			this.y = y_fut;
			this.env.putAgent(this, this.x, this.y);
			this.env.deleteAgent(x_born, y_born);
			
			// Donne naissance
			Fish f = new Fish(env, this.sma, this.birth, x_born, y_born);
			this.sma.add(f);
			this.env.putAgent(f, x_born, y_born);
			this.sma.nbFish++;
			this.cpt_birth = 0;
		}
		this.cpt_birth++;
	}


//	private Shark containsShark(ArrayList<Agent> neighbor) {
//		for (Agent agent : neighbor) {
//			if (agent instanceof Shark) {
//				return (Shark) agent;
//			}
//		}
//		return null;
//	}

}
