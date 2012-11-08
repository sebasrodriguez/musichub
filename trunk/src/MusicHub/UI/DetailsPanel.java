package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HText;

public class DetailsPanel extends BasicPanel {
	
	private String content;
	private String imgURL;
	private Image image;
	
	public DetailsPanel(int x, int y, int w, int h){	
		super(x,y,w,h);
		this.setBounds(x, y, w, h);
		
		
	}
	
	public void showItem(String content, String imgURL){
		
		try {
			image= Toolkit.getDefaultToolkit().getImage(new URL(imgURL));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		image.getScaledInstance(60, 60, image.SCALE_SMOOTH);
		BufferedImage buffImg= new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.createGraphics();
		g.drawImage(image, 0, 0, new Color(0,0,0), null);
		g.dispose();
		
		
		HIcon ico=null;
		ico = new HIcon(image);
			
	
		ico.setBounds(this.getX()+10, this.getY()+10, 60, 60);
		
		HText contentText= new HText(content);
		contentText.setFont(new Font("tiresias",Font.PLAIN,13));
		contentText.setForeground(Color.WHITE);
		contentText.setBounds(ico.getX()+70, this.getY()+10, this.getWidth(), 50);
		contentText.setHorizontalAlignment(contentText.HALIGN_LEFT);
		
		
		
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
