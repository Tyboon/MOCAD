package view;

import java.awt.Graphics;

import agent.Agent;
import utils.SMA;

@SuppressWarnings("serial")
public class PanelCatcher extends Panel {

	public PanelCatcher(SMA sma, int width, int height, int pxSize) {
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
