package MusicHub.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HScene;
import org.havi.ui.HStaticText;

import java.awt.Toolkit;

import MusicHub.Util.Conf;

public class BasicContainer extends HContainer {
	
	private HScene subScene;
	private HStaticText titleText;
	private HIcon icon;
	private Image img;
	
	public BasicContainer(HScene scene, String title){
		
		this.subScene=scene;
		this.setBounds(scene.getBounds());
		
		this.titleText= new HStaticText("MusicHub");
		this.titleText.setBounds(0, 0, this.getWidth(), 60);
		this.titleText.setFont(new Font(Conf.getFontName(), Font.PLAIN, Conf.getFontSize()));
		this.titleText.setHorizontalAlignment((int) HStaticText.LEFT_ALIGNMENT);		
		this.titleText.setForeground(Color.WHITE);	
		
		img=Toolkit.getDefaultToolkit().getImage("../assets/logo.gif");
		icon= new HIcon(img,10,10,250,250);
		icon.setSize(126, 56);
		
		this.add(icon);
		//this.add(titleText);
		

		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

}
