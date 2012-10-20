package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HTextButton;

import MusicHub.Util.Conf;

public class ChannelsView extends BasicContainer{

	private static final long serialVersionUID = 1L;
	private HTextButton button;
	
	public ChannelsView(){
		this.setBounds(10, 0, 500, 500);
		button = new HTextButton("test");
		button.setBounds(0, 0, this.getWidth(), Conf.getItemHeight());
		button.setForeground(Color.white);
		button.setFont(new Font(Conf.getFontName(), Font.BOLD, Conf
				.getFontSize()));
		
		this.add(button);		
		this.popToFront(button);
	}
	
	@Override
	public void paint(Graphics g) {		
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

}
