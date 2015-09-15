
public class Environnement {

	private int l;
	private int L;
	private Bille[][] env;
	
	public Environnement(int L, int l) {
		this.l = l;
		this.l = L;
		this.env = new Bille[l][L];
	}

	public Bille[][] getEnv() {
		return env;
	}

	public void setEnv(Bille[][] env) {
		this.env = env;
	}

	public void putBille(Bille b, int i, int j) {
		this.env[i][j] = b;
	}
	
	public void deleteBille(int i, int j) {
		this.env[i][j] = null;
	}
}
