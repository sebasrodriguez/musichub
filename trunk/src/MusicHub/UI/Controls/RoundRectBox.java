package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Graphics;

import org.havi.ui.HContainer;

public class RoundRectBox extends HContainer{
	
	private static final long serialVersionUID = 1L;
	Color color;

	public RoundRectBox(int x, int y, int width, int height, Color color){
		this.setBounds(x, y, width, height);
		this.color = color;
	}
	
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);
		super.paint(g);
	}
	
}
