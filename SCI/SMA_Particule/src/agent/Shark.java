package agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import utils.Environnement;
import utils.SMA;

public class Shark extends Agent {

	private int birth;
	private int cpt_birth = 0;
	private int meal;
	private int cpt_meal = 0;
	private SMA sma;
	
	public Shark(Environnement env, SMA sma, int birthShark, int mealShark) {
		super(env);
		this.birth = birthShark;
		this.meal = mealShark;
		this.color = Color.RED;
		this.sma = sma;
	}

	public void decide() {
		ArrayList<Agent> neighbor = (ArrayList<Agent>) getNeighbor();
		Agent[][] tab = this.env.getEnv();
		Random rand = new Random();
		Fish fish = null;
		
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		
		// Si on est dans le cadre
				if ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut)) { 
					
					// S'il n'y a pas de poisson
					if ((fish = containsFish(neighbor)) != null) {
						// S'il y a un poisson à l'emplacement souhaité
						if (tab[x_fut][y_fut] != null) {
							x_fut = this.x;
							y_fut = this.y;
							this.cpt_meal--;
						}
					} else {
					//S'il y a un poisson
						this.eat(fish);
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
				
				if (this.cpt_meal == 0) {
					this.sma.remove(this);
				} else {
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
	}

	private void eat(Fish fish) {
		this.env.deleteAgent(fish.getX(), fish.getY());
		this.cpt_meal = this.meal;
	}

	private Fish containsFish(ArrayList<Agent> neighbor) {
		for (Agent agent : neighbor) {
			if (agent instanceof Fish) {
				return (Fish) agent;
			}
		}
		return null;
	}

}
