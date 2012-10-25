package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import org.havi.ui.HNavigable;
import org.havi.ui.HText;
import org.havi.ui.HTextButton;

public class Key extends HTextButton implements KeyListener, HNavigable{	

	private static final long serialVersionUID = 1L;
	private char letter;
	
	
	public Key(int x, int y, char letter){
		super(String.valueOf(letter));
		this.setBounds(x, y, 20,20);
		this.setBackground(Color.BLACK);
		/*HText l = new HText(String.valueOf(letter));
		l.setFont(new Font("tiresias",Font.BOLD,12));*/
		//this.setTextContent(String.valueOf(letter), 12);
		this.setForeground(Color.WHITE);
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	private char key;
	private int x;
	private int y;
	
	public Key(char key, int x, int y){
		this.key = key;
		this.x = x;
		this.y = y;		
	}
	
	public char getKey(){
		return this.key;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setKey(char key){
		this.key = key;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	*/
	
}
