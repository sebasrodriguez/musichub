package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HText;

public class ItemContainer extends HContainer {
	
	
	private HText label;
	private HIcon icon;
	
	public ItemContainer(String intro){
		
		this.setBounds(50, 50, 250, 150);
		//this.setBackground(Color.gray);
		this.setSize(250, 150);		
		label= new HText(intro,0, 0, this.getWidth()-20, this.getHeight()-20);
		label.setFont(new Font("Tiresias",Font.PLAIN,13));
		label.setForeground(Color.cyan);
		//label.setBackground(Color.white);-
		
		icon = new HIcon(Toolkit.getDefaultToolkit().getImage("http://www.valsf.com.uy/mvdexchange/mvd/Bra.png"));		
		icon.setSize(250, 250);
		
		this.add(icon);
		this.add(label);
		
	}

	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		super.print(g);
	}
	
	

}
