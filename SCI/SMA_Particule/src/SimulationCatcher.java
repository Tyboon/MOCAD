import javax.swing.JFrame;

import utils.SMA;
import view.PanelCatcher;
import agent.Attractor;
import agent.Catcher;
import agent.Wall;


public class SimulationCatcher {
	private static int nbRun;
	private static int nbAttractor;
	private static int nbCatcher;
	private static int sleepT;
	private static int L;
	private static int l;
	private static int px;
	
	public static void main(String[] args) throws InterruptedException {
		nbRun = Integer.parseInt(args[0]); //500
		nbAttractor = Integer.parseInt(args[1]); //100
		nbCatcher = Integer.parseInt(args[2]);
		sleepT = Integer.parseInt(args[3]); //80
		L = Integer.parseInt(args[4]); //20
		l = Integer.parseInt(args[5]); //20
		px = Integer.parseInt(args[6]);
	
		SMA sma = new SMA(nbAttractor);
		JFrame jframe = new JFrame();
		PanelCatcher p = new PanelCatcher(sma, L, l, px);
		
		jframe.add(p);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		sma.init(L, l, nbRun, sleepT, p);
		for (int i = 0; i < nbAttractor; i++) {
			Attractor att = new Attractor(sma.getEnv()); 
			sma.add(att);
			sma.attractor.add(att);
		}
		
		
		for (int i = 0; i < nbCatcher; i++) {
			sma.add(new Catcher(sma.getEnv(), sma));
		}
		
		for (int i = 0; i < 50; i++) {
			sma.add(new Wall(sma.getEnv()));
		}
		
		System.out.println("RUN");
		sma.runCatcher();
		System.out.println("FINISH ! ");
	}
}
