package view;

import java.awt.Graphics;
import java.util.ArrayList;

import utils.SMA;
import agent.Agent;
import agent.Bille;

@SuppressWarnings("serial")
public class PanelBille extends Panel {

	public PanelBille(SMA sma, int width, int height, int pxSize) {
		super(sma, width, height, pxSize);
	}

	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Bille bille = null;
		for (Agent agent : sma.getAgents()) {
			bille = (Bille) agent;
			g.setColor(bille.getColor());
			g.fillRect(bille.getX() * pxSize, bille.getY() * pxSize, pxSize, pxSize);
		}
	}

}
