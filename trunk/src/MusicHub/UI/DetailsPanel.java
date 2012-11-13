package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HText;

import com.mortennobel.imagescaling.MultiStepRescaleOp;
import com.mortennobel.imagescaling.ResampleOp;

public class DetailsPanel extends BasicPanel {
	
	private String content;
	private String imgURL;
	private Image image;
	//private BufferedImage bfImage;
	//private BufferedImage rescaledImg;
	
	public DetailsPanel(int x, int y, int w, int h){	
		super(x,y,w,h);
		this.setBounds(x, y, w, h);
		
		
	}
	
	public void showItem(String content, String imgURL){
		
		try {
			image= Toolkit.getDefaultToolkit().getImage(new URL(imgURL));
			image= image.getScaledInstance(60, 60, Image.SCALE_DEFAULT);			
			
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
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

		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.paint(g);
	}

}
