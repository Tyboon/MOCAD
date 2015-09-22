import java.awt.Color;
import java.util.Random;


public class Bille {

	//private Sens sens;
	private int x;
	private int y;
	private int pasX;
	private int pasY;
	private Color color = Color.BLUE; 
	private Environnement env;
	
	public Bille(Environnement env) {
		
		this.env = env;
		Random rand = new Random();
		
		// VERIFIER SI NON COLLISION
		int tmp1 = rand.nextInt(env.getEnv().length);
		int tmp2 = rand.nextInt(env.getEnv()[0].length);
		
		while (env.getEnv()[tmp1][tmp2] != null) {
			tmp1 = rand.nextInt(env.getEnv().length);
			tmp2 = rand.nextInt(env.getEnv()[0].length);
		}
		
		this.x = tmp1;
		this.y = tmp2;
		
		this.pasX = rand.nextInt(3)-1; // -1, 0 ou 1
		this.pasY = rand.nextInt(3)-1;
		
	}
	
	public void decide() {
		Bille[][] tab = this.env.getEnv();
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
				if (cpt != 8) { 
					this.env.deleteBille(this.x, this.y);
					this.x = x_fut;
					this.y = y_fut;
					this.env.putBille(this, this.x, this.y);
				}
			} else {
				
				// Sinon va à la place libre
				this.env.deleteBille(this.x, this.y);
				this.x = x_fut;
				this.y = y_fut;
				this.env.putBille(this, this.x, this.y);
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
			if (cpt != 8) { 
				this.env.deleteBille(this.x, this.y);
				this.x = x_fut;
				this.y = y_fut;
				this.env.putBille(this, this.x, this.y);
			}
		}
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}
}
