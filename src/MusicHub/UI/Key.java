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
	private int x;
	private int y;
	
	
	public Key(int x, int y, char letter){
		super(String.valueOf(letter));
		this.letter = letter;
		this.x = x;
		this.y = y;
		this.setBounds(this.x, this.y, 20,20);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		
	}
	
	public char getKey(){
		return this.letter;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	/*
	 * boton azul key code = 406
	 * boton amarillo key code = 405
	 * 	boton verde key code = 404
	 * boton rojo key code = 403
	 * boton arriba key code = 38
	 * boton derecha key code = 39
	 * boton abajo key code = 40
	 * boton izquierda key code = 37
	 * boton ok key code = 10		
	 */	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 10){
			System.out.println(this.letter);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
