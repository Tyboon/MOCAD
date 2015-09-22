
public class Simulation {

	private static int nbRun;
	private static int nbAgent;
	private static int sleepT;
	private static int L;
	private static int l;
	
	public static void main (String[] args) throws InterruptedException {
		nbRun = 5;
		nbAgent = 4;
		sleepT = 10;
		L = 20;
		l = 10;
		
		SMA sma = new SMA();
		Vue vue = new Vue(sma, L, l);
		sma.init(L, l, nbRun, sleepT, vue);
		
		for (int i =0; i < nbAgent; i++) {
			sma.add(new Bille(sma.getEnv()));
			System.out.println("Add bowls");
		}
		System.out.println("RUN");
		sma.run();
		System.out.println("FINISH ! ");
	}
}
