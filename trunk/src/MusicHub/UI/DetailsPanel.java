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


public class DetailsPanel extends BasicPanel {
	
	private String title;
	private String content;
	private String imgURL;
	private Image image;
	//private BufferedImage bfImage;
	//private BufferedImage rescaledImg;
	
	public DetailsPanel(int x, int y, int w, int h){	
		super(x,y,w,h);
		this.setBounds(x, y, w, h);
	}
	
	public void showItem(String title, String content, String imgURL){
		
		try {
			image= Toolkit.getDefaultToolkit().getImage(new URL(imgURL));
			image= image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);			
			
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		this.content=content;
		this.title=title;
		
		
		HIcon ico=null;
		ico = new HIcon(image);
		
			
	
		ico.setBounds(10, 50, 120, 100);
		
		HText titleText= new HText(this.title);
		titleText.setFont(new Font("tiresias",Font.BOLD,13));
		titleText.setForeground(Color.WHITE);
		titleText.setBounds(10, 10, this.getWidth(), 20);
		titleText.setHorizontalAlignment(titleText.HALIGN_LEFT);		
		
		System.out.println("titu" + titleText);
		
		if(isTooLength(this.content)){
			this.content=wrapContent(this.content);
		}
		
		HText contentText= new HText(this.content);
		contentText.setFont(new Font("tiresias",Font.PLAIN,13));
		contentText.setForeground(Color.WHITE);
		contentText.setBounds(10, 180, this.getWidth(), this.getHeight()-10);
		contentText.setHorizontalAlignment(contentText.HALIGN_LEFT);
		contentText.setVerticalAlignment(contentText.VALIGN_TOP);
		
		
		this.add(contentText);
		this.add(titleText);
		
		this.add(ico);
		this.popToFront(ico);
		
	}
	
	private boolean isTooLength(String content){
		return content.length()>25;
	}
	
	private String wrapContent(String content){
		
		final int WRAP_LENGTH=62;
		int i=0,x=0;
		boolean f=false;
		String wContent=new String();
		
		
		while(i<content.length() && !f){			
			
			if(Character.isSpaceChar(content.charAt(i)) && x > WRAP_LENGTH){
				wContent+='\n';
				x=0;
			}
			else{
				wContent+=content.charAt(i);
				x++;
			}
			i++;
		}
		return wContent;		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

}
