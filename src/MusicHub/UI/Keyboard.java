package MusicHub.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import org.havi.ui.HContainer;

import MusicHub.UI.Contracts.ISelectedOption;
import MusicHub.Util.Conf;

public class Keyboard extends HContainer implements KeyListener {	

	private static final long serialVersionUID = 1L;
	private ArrayList<Key> column1;
	private ArrayList<Key> column2;
	private ArrayList<Key> column3;
	private ArrayList<Key> column4;
	private Key space;
	private int x = 0; 
	private int y = 0; 	
	private String rssURL = "";
	private String rssURLFinal = "";
	
	public Keyboard(){
		System.out.println("Constructor keyboard");
		column1 = new ArrayList<Key>();
		column2 = new ArrayList<Key>();
		column3 = new ArrayList<Key>();
		column4 = new ArrayList<Key>();
		this.initializeKeybord();		
	}
	
	private void initializeKeybord(){
		System.out.println("Initialize keyboard");
	
	//Cargo el ArrayList de la columna 1
	Key key = new Key('1', 230, 278);
	column1.add(key);
	key = new Key('2', 259, 278);
	column1.add(key);
	key = new Key('3', 288, 278);
	column1.add(key);
	key = new Key('4', 317, 278);
	column1.add(key);
	key = new Key('5', 346, 278);
	column1.add(key);
	key = new Key('6', 375, 278);
	column1.add(key);
	key = new Key('7', 404, 278);
	column1.add(key);
	key = new Key('8', 433, 278);
	column1.add(key);
	key = new Key('9', 462, 278);
	column1.add(key);
	key = new Key('0', 491, 278);
	column1.add(key);

	//Cargo el ArrayList de la columna 2
	key = new Key('q', 230, 320);
	column2.add(key);
	key = new Key('w', 259, 320);
	column2.add(key);
	key = new Key('e', 288, 320);
	column2.add(key);
	key = new Key('r', 317, 320);
	column2.add(key);
	key = new Key('t', 346, 320);
	column2.add(key);
	key = new Key('y', 375, 320);
	column2.add(key);
	key = new Key('u', 404, 320);
	column2.add(key);
	key = new Key('i', 433, 320);
	column2.add(key);
	key = new Key('o', 462, 320);
	column2.add(key);
	key = new Key('p', 491, 320);
	column2.add(key);
	
	
	//Cargo el ArrayList de la columna 3
	key = new Key('a', 230, 362);
	column3.add(key);
	key = new Key('s', 259, 362);
	column3.add(key);
	key = new Key('d', 288, 362);
	column3.add(key);
	key = new Key('f', 317, 362);
	column3.add(key);
	key = new Key('g', 346, 362);
	column3.add(key);
	key = new Key('h', 375, 362);
	column3.add(key);
	key = new Key('j', 404, 362);
	column3.add(key);
	key = new Key('k', 433, 362);
	column3.add(key);
	key= new Key('l', 462, 362);
	column3.add(key);
	key= new Key('-', 491, 362); //backspace
	column3.add(key);

	//Cargo el ArrayList de la columna 4
	key = new Key('z', 230, 404);
	column4.add(key);
	key = new Key('x', 259, 404);
	column4.add(key);
	key = new Key('c', 288, 404);
	column4.add(key);
	key = new Key('v', 317, 404);
	column4.add(key);
	key = new Key('b', 346, 404);
	column4.add(key);
	key = new Key('n', 375, 404);
	column4.add(key);
	key = new Key('m', 404, 404);
	column4.add(key);
	key = new Key(':', 433, 404); 
	column4.add(key);
	key = new Key('/', 462, 404); 
	column4.add(key);
	key = new Key('_', 491, 404); 
	column4.add(key);

	//Tecla "espacio"
	space = new Key(' ', 346,446);	
	
	//Posicion inicial de la seleccion	
	this.x = 230;
	this.y = 278;
	}
		
	public Key getKey(int x, int y){
		System.out.println("GetKey");
		switch (y){
		case 1:
			return column1.get(x);
		case 2:
			return column2.get(x);
		case 3:
			return column3.get(x);
		case 4:
			return column4.get(x);
		default: // es espacio
			return space;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keypressed");
		switch(e.getKeyCode()){
		case 10:
			rssURL = rssURL + this.getKey(x,y);
			break;		
		case 38:
			//subo una fila
			if(y != 278){
				y = y - 42;
			}			
			break;
		case 40:
			//bajo una fila
			if(y == 404){
				x = 346;
				y = y + 42; 
			}else{
				if(y != 446){
					y = y + 42;
				}
			}
			break;
		case 37:
			//me  muevo a la izquierda
			if(y != 446){
				if(x != 230){
					x = x - 29;
				}				
			}
			break;
		case 39:
			//me muevo a la derecha
			if(y != 446){
				if(x != 491){
					x = x + 29;
				}				
			}
			break;
		case 404:
			//tecla verde acepto lo ingresado
			rssURLFinal = rssURL;
			System.out.println(rssURLFinal);
			//SALIR Y CERRAR EL TECLADO
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
