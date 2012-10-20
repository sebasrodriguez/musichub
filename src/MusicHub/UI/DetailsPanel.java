package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HText;

public class DetailsPanel extends BasicPanel {
	
	private String content;
	private String imgURL;
	
	public DetailsPanel(int x, int y, int w, int h){	
		super(x,y,w,h);
		this.setBounds(x, y, w, h);
	}
	
	public void showItem(String content, String imgURL){
		HIcon ico= new HIcon(Toolkit.getDefaultToolkit().getImage(imgURL)) ;
		ico.setBounds(20, 20, 60, 60);
		
		HText contentText= new HText(content);
		contentText.setFont(new Font("tiresias",Font.BOLD,13));
		contentText.setForeground(Color.WHITE);
		contentText.setBounds(0, 10, 250, 50);
		
		this.add(contentText);
		this.add(ico);
		this.popToFront(ico);
		this.repaint();
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.paint(g);
	}

}
