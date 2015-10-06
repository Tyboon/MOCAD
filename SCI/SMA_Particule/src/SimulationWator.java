import javax.swing.JFrame;

import utils.SMA;
import view.Panel;
import view.PanelBille;
import view.PanelWator;
import agent.Fish;
import agent.Shark;


public class SimulationWator {

	private static int nbRun;
	private static int sleepT;
	private static int L;
	private static int l;
	private static int px;
//	private static int tor;
	
	private static int nbFish = 100;
	private static int nbShark = 20;
	private static int birthFish = 3;
	private static int birthShark = 5;
	private static int mealShark = 10;

	public static void main(String[] args) throws InterruptedException {
		nbRun = Integer.parseInt(args[0]); //500
		sleepT = Integer.parseInt(args[1]); //80
		L = Integer.parseInt(args[2]); //20
		l = Integer.parseInt(args[3]); //20
		px = Integer.parseInt(args[4]);
		
		nbShark = Integer.parseInt(args[5]);
		birthShark = Integer.parseInt(args[6]);
		mealShark = Integer.parseInt(args[7]);
		nbFish = Integer.parseInt(args[8]);
		birthFish = Integer.parseInt(args[9]);
		
		
		SMA sma = new SMA(nbFish, nbShark);
		JFrame jframe = new JFrame();
		Panel p = new PanelWator(sma, L, l, px);
		
		jframe.add(p);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		sma.init(L, l, nbRun, sleepT, p);
		
		for (int i = 0; i < nbShark; i++) {
			sma.add(new Shark(sma.getEnv(), sma, birthShark, mealShark));
		}
		
		for (int i = 0; i < nbFish; i++) {
			sma.add(new Fish(sma.getEnv(), sma, birthFish));
		}
		
		System.out.println("RUN");
		sma.run();
		System.out.println("FINISH ! ");
	}
	

}
