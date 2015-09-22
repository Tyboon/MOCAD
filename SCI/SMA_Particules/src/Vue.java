import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Vue extends JFrame implements Observer {
	private SMA sma;
	
	public Vue(SMA sma, int L, int l) {
		this.sma = sma;
		JPanel panel = new JPanel();
		this.setSize(l, L);
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
