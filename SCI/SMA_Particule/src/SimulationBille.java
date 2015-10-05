import javax.swing.JFrame;

import agent.Bille;
import utils.SMA;
import view.Panel;
import view.PanelBille;


public class SimulationBille {
	
	private static int nbRun;
	private static int nbAgent;
	private static int sleepT;
	private static int L;
	private static int l;
	private static int px;
//	private static int tor;

	public static void main(String[] args) throws InterruptedException {
		nbRun = Integer.parseInt(args[0]); //500
		nbAgent = Integer.parseInt(args[1]); //100
		sleepT = Integer.parseInt(args[2]); //80
		L = Integer.parseInt(args[3]); //20
		l = Integer.parseInt(args[4]); //20
		px = Integer.parseInt(args[5]);
//		tor = Integer.parseInt(args[6]);

		SMA sma = new SMA();
		JFrame jframe = new JFrame();
		Panel p = new PanelBille(sma, L, l, px);
		
		jframe.add(p);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
//		if (tor == 1)
//			sma.init(L, l, nbRun, sleepT, p, true);
//		else
//			sma.init(L, l, nbRun, sleepT, p, false);
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
