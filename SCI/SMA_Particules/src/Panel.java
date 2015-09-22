import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	private SMA sma;
	private int pxSize = 1;

	public Panel(SMA sma, int width, int height, int pxSize) {
		this.sma = sma;
		this.pxSize = pxSize;
		this.setPreferredSize(new Dimension(width * pxSize, height * pxSize));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		List<Bille> agents = sma.getAgents();
		for (Bille bille : agents) {
			g.setColor(bille.getColor());
			g.fillRect(bille.getX() * pxSize, bille.getY() * pxSize, pxSize, pxSize);
		}
	}

}
