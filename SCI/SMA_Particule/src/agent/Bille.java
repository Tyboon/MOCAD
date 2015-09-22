package agent;

import java.awt.Color;
import java.util.Random;

import utils.Environnement;

public class Bille extends Agent {

	private Color color;
	
	public Bille(Environnement env) {
		super(env);
		int r = (int) (Math.random()*255);
		int g = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);
		this.color = new Color(r,g,b);
	}
	
	public Color getColor(){
		return this.color;
	}

	public void decide() { //TODO eclaircir
		Bille[][] tab = (Bille[][]) this.env.getEnv();
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		int cpt = 1;
		Random rand = new Random();
		
		// Si on est dans le cadre
		if ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut)) { 
//			System.out.println(x_fut + " - " + y_fut);
			// Si on rencontre un autre agent
			if (tab[x_fut][y_fut] != null) {
				// Partir dans le sens opposé si libre sinon rdm sur les cases libres
				this.pasX = this.pasX * (-1);
				this.pasY = this.pasY * (-1);
				x_fut = this.x + this.pasX;
				y_fut = this.y + this.pasY;
				
				while ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1)&& (0 <= x_fut) && (0 <= y_fut) && (env.getEnv()[x_fut][y_fut] != null)) {
					this.pasX = rand.nextInt(3)-1;
					this.pasY = rand.nextInt(3)-1;
					x_fut = this.x + this.pasX;
					y_fut = this.y + this.pasY;
					cpt++;
				}
				// Si on a trouvé une case libre
				if ((cpt < 8) && (x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut) && (env.getEnv()[x_fut][y_fut] != null)) { 
					this.env.deleteAgent(this.x, this.y);
					this.x = x_fut;
					this.y = y_fut;
					this.env.putAgent(this, this.x, this.y);
				}
			} else {
				
				// Sinon va à la place libre
				this.env.deleteAgent(this.x, this.y);
				this.x = x_fut;
				this.y = y_fut;
				this.env.putAgent(this, this.x, this.y);
			}
			
		} else {
			this.pasX = this.pasX * (-1);
			this.pasY = this.pasY * (-1);
			x_fut = this.x + this.pasX;
			y_fut = this.y + this.pasY;
			
			while ((x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut) && (env.getEnv()[x_fut][y_fut] != null)) {
				this.pasX = rand.nextInt(3)-1;
				this.pasY = rand.nextInt(3)-1;
				x_fut = this.x + this.pasX;
				y_fut = this.y + this.pasY;
				cpt++;
			}
			// Si on a trouvé une case libre
			if ((cpt < 8) && (x_fut <= tab.length-1) && (y_fut <= tab[0].length-1) && (0 <= x_fut) && (0 <= y_fut) && (env.getEnv()[x_fut][y_fut] != null)) { 
				this.env.deleteAgent(this.x, this.y);
				this.x = x_fut;
				this.y = y_fut;
				this.env.putAgent(this, this.x, this.y);
			}
		}
	}

}
