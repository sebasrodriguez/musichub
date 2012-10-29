package MusicHub.UI;

import java.awt.Color;

import org.havi.ui.HTextButton;

public class Url extends HTextButton {

	private static final long serialVersionUID = 1L;
	private String text;
	private int x;
	private int y;
	
	
	
	public Url(int x, int y, String text){
		super(text);
		this.text = text;
		this.x = x;
		this.y = y;
		this.setBounds(this.x, this.y, 220,20);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);		
	}	
	
	public void setText(String text){
		this.text = text;
		this.setTextContent(this.text, 128);
	}
}
