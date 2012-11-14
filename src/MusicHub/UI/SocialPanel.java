package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HText;

import MusicHub.UI.Contracts.IMenuContainer;

public class SocialPanel extends BasicPanel implements IMenuContainer {

	public SocialPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		
		this.setBounds(x, y, w, h);		
		this.setSize(w, h);
		
		System.out.println(this.getWidth());
		
		
		HText simpletext= new HText("Social");
		simpletext.setFont(new Font("tiresias",Font.BOLD,13));
		simpletext.setForeground(Color.WHITE);
		simpletext.setBounds(0, 0, this.getWidth(), 50);
		simpletext.setHorizontalAlignment(HText.HALIGN_CENTER);
		
		this.add(simpletext);
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLUE);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		super.paint(g);
	}


	@Override
	public void selectedOption(UOptionItem selectedOption) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stepedOnOption(UOptionItem option) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
