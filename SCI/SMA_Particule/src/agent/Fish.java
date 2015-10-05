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

	@SuppressWarnings("null")
	public void decide() {
		ArrayList<Agent> neighbor = (ArrayList<Agent>) getNeighbor();
		Agent[][] tab = this.env.getEnv();
		Random rand = new Random();
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		Shark shark = null;
		
		// Si on est dans le cadre
		if ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut)) { 
			
			// S'il n'y a pas de requin
			if ((shark = containsShark(neighbor)) != null) {
				// S'il y a un poisson à l'emplacement souhaité
				if (tab[x_fut][y_fut] != null) {
					x_fut = this.x;
					y_fut = this.y;
				}
			} else {
			//S'il y a un requin
				x_fut = this.x + (shark.getPasX()*(-1));
				y_fut = this.y + (shark.getPasY()*(-1));
			}
		} else {
		// Si on dépasse le cadre
			if (this.x == 0 || this.x == tab.length-1)
				this.pasX = this.pasX * (-1);
			else
				this.pasY = this.pasY * (-1);
			x_fut = this.x + this.pasX;
			y_fut = this.y + this.pasY;
			
			while ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut) && (env.getEnv()[x_fut][y_fut] != null)) {
				this.pasX = rand.nextInt(3)-1;
				this.pasY = rand.nextInt(3)-1;
				x_fut = this.x + this.pasX;
				y_fut = this.y + this.pasY;
			}
		}
		
		this.env.deleteAgent(this.x, this.y);
		this.x = x_fut;
		this.y = y_fut ;
		this.cpt_birth++;
		this.env.putAgent(this, this.x, this.y);
		if (this.cpt_birth == this.birth) {
			this.sma.add(new Fish(env, this.sma, this.birth));
			this.cpt_birth = 0;
		}
	}

	private Shark containsShark(ArrayList<Agent> neighbor) {
		for (Agent agent : neighbor) {
			if (agent instanceof Shark) {
				return (Shark) agent;
			}
		}
		return null;
	}

}
