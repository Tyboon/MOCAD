package view;


import java.awt.Graphics;
import utils.SMA;
import agent.Agent;

@SuppressWarnings("serial")
public class PanelWator extends Panel {

	public PanelWator(SMA sma, int width, int height, int pxSize) {
		super(sma, width, height, pxSize);
	}

		
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Agent agent : sma.getAgents()) {
			g.setColor(agent.getColor());
			g.fillRect(agent.getX() * pxSize, agent.getY() * pxSize, pxSize, pxSize);
		}
	}
}



