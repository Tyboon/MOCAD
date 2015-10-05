import javax.swing.JFrame;

import utils.SMA;
import view.Panel;
import view.PanelBille;
import agent.Fish;
import agent.Shark;


public class SimulationWator {

	private static int nbRun;
	private static int sleepT;
	private static int L;
	private static int l;
	private static int px;
//	private static int tor;
	
	private static int nbFish = 20;
	private static int nbShark = 4;
	private static int birthFish = 2;
	private static int birthShark = 5;
	private static int mealShark = 3;

	public static void main(String[] args) throws InterruptedException {
		nbRun = Integer.parseInt(args[0]); //500
		sleepT = Integer.parseInt(args[1]); //80
		L = Integer.parseInt(args[2]); //20
		l = Integer.parseInt(args[3]); //20
		px = Integer.parseInt(args[4]);

		SMA sma = new SMA();
		JFrame jframe = new JFrame();
		Panel p = new PanelBille(sma, L, l, px);
		
		jframe.add(p);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		sma.init(L, l, nbRun, sleepT, p);
		
		for (int i = 0; i < nbFish; i++) {
			sma.add(new Fish(sma.getEnv(), sma, birthFish));
		}
		for (int i = 0; i < nbShark; i++) {
			sma.add(new Shark(sma.getEnv(), sma, birthShark, mealShark));
		}
		System.out.println("RUN");
		sma.run();
		System.out.println("FINISH ! ");
	}
	

}
