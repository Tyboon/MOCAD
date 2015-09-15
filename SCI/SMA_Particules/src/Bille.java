import java.util.Random;


public class Bille {

	//private Sens sens;
	private int x;
	private int y;
	private int pasX;
	private int pasY;
	private Environnement env;
	private int[] sens = new int[2];
	
	public Bille() {
		
		this.sens[0] = -1;
		this.sens[1] = 1;
		
		Random rand = new Random();
		Bille[][] tab = env.getEnv();
		//int d = new Random().nextInt(Sens.values().length);
		//this.sens = Sens.values()[d];
		
		// VERIFIER SI NON COLLISION
		int tmp1 = rand.nextInt(env.getEnv().length);
		int tmp2 = rand.nextInt(env.getEnv()[0].length);
		
		while (env.getEnv()[tmp1][tmp2] != null) {
			tmp1 = rand.nextInt(env.getEnv().length);
			tmp2 = rand.nextInt(env.getEnv()[0].length);
		}
		
		this.x = tmp1;
		this.y = tmp2;
		
		this.pasX = this.sens[rand.nextInt(1)];
		this.pasY = this.sens[rand.nextInt(1)];
		
	}
	
	public void decide() {
		Bille[][] tab = env.getEnv();
		int x_fut = this.x + this.pasX;
		int y_fut = this.y + this.pasY;
		int cpt = 1;
		Random rand = new Random();
		
		// Si on est dans le cadre
		if ((x_fut < tab.length) && (y_fut < tab[0].length)) { 
			
			// Si on rencontre un autre agent
			if (tab[x_fut][y_fut] != null) {
				// Partir dans le sens opposé si libre sinon rdm sur les cases libres
			}else {
				
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
			
			while ((x_fut < tab.length) && (y_fut < tab[0].length) && (env.getEnv()[x_fut][y_fut] != null)) {
				this.pasX = this.sens[rand.nextInt(1)];
				this.pasY = this.sens[rand.nextInt(1)];
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
}
