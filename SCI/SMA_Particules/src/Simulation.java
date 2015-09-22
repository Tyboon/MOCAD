import javax.swing.JFrame;

public class Simulation {

	private static int nbRun;
	private static int nbAgent;
	private static int sleepT;
	private static int L;
	private static int l;

	public static void main(String[] args) throws InterruptedException {
		nbRun = 500;
		nbAgent = 10;
		sleepT = 80;
		L = 20;
		l = 20;

		SMA sma = new SMA();
		JFrame jframe = new JFrame();
		Panel p = new Panel(sma, L, l, 10);
		
		jframe.add(p);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		sma.init(L, l, nbRun, sleepT, p);

		for (int i = 0; i < nbAgent; i++) {
			sma.add(new Bille(sma.getEnv()));
//			System.out.println("Add bowls");
		}
		System.out.println("RUN");
		sma.run();
		System.out.println("FINISH ! ");
	}
}
