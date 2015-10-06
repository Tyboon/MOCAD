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
	private int cpt_meal;
	private SMA sma;
	
	public Shark(Environnement env, SMA sma, int birthShark, int mealShark) {
		super(env);
		this.birth = birthShark;
		this.meal = mealShark;
		this.cpt_meal = this.meal;
		this.color = Color.RED;
		this.sma = sma;
	}
	
	public Shark(Environnement env, SMA sma, int birthShark, int mealShark, int x, int y) {
		super(env, x, y);
		this.birth = birthShark;
		this.meal = mealShark;
		this.cpt_meal = this.meal;
		this.color = Color.RED;
		this.sma = sma;
	}

	public void decide() {
		ArrayList<Agent> neighbor = (ArrayList<Agent>) getNeighbor();
		Agent[][] tab = this.env.getEnv();
		Random rand = new Random();
		Fish fish = null;
		int x_born, y_born;
		
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		
		if (this.cpt_meal == 0) { 
			// Mort de faim
			this.sma.remove(this);
			this.env.deleteAgent(this.getX(), this.getY());
			this.sma.nbShark--;
			return ;
		}
		
		
		if ((fish = containsFish(neighbor)) != null) {
			// s'il y a un poisson
			x_fut = fish.getX();
			y_fut = fish.getY();
			this.eat(fish);
		} else { // sinon se déplace
			
			this.cpt_meal--;
			
			if ((this.x < tab.length-1) && (this.y < tab[0].length-1) && (0 < this.x) && (0 < this.y)) { 
				// si on est dans le cadre
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
			} else { // si on dépasse le cadre
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
		}
				
		if (this.cpt_birth >= this.birth && (this.x != x_fut && this.y != y_fut)) {
			// Déplace le requin
			x_born = this.x;
			y_born = this.y;
			
			this.x = x_fut;
			this.y = y_fut;
			this.env.putAgent(this, this.x, this.y); 
//			System.out.println("SHARK "+ this.x + " " + this.y);
			this.env.deleteAgent(x_born, y_born);
			
			// Donne naissance
			Shark s = new Shark(env, this.sma, this.birth, this.meal, x_born, y_born);
			this.sma.add(s);
			this.env.putAgent(s, x_born, y_born);
			this.sma.nbShark++;
			this.cpt_birth = 0;
		}
		
		this.cpt_birth++;
	}
	

	private void eat(Fish fish) {
		this.env.deleteAgent(fish.getX(), fish.getY());
		this.sma.remove(fish);
		this.sma.nbFish--;
		this.cpt_meal = this.meal;
//		System.out.println("EAT");
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
