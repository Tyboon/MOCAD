package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import utils.SMA;

@SuppressWarnings("serial")
public abstract class Panel extends JPanel {
	protected SMA sma;
	protected int pxSize = 1;

	public Panel(SMA sma, int width, int height, int pxSize) {
		this.sma = sma;
		this.pxSize = pxSize;
		this.setPreferredSize(new Dimension(width * pxSize, height * pxSize));
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE); //TODO 
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}