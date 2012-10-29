package MusicHub.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import org.havi.ui.HContainer;

public class Keyboard extends HContainer implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private String letters = "1234567890QWERTYUIOPASDFGHJKL<ZXCVBNM:/_ ";
	private int x = 20;
	private int y = 40;
	private ArrayList <Key> kb;
	private int currentIndex = 0;
	private String urlActual = "";
	private Url url;
	
	
	
	
	/**
	 * Metodo contstructor
	 * Setea los limites del recuadro donde se va a dibujar el teclado
	 */
	public Keyboard(){
		this.setBounds(0, 0, 220, 140);	
		this.url = new Url(20,20,"");	
		this.fillArray();
		this.drawKeyboard();
			
	}

	
	/**
	 * Carga el Arraylist <Key>
	 */
	private void fillArray(){
		kb = new ArrayList<Key>();
		for(int i = 0; i < letters.length(); i++){
			Key n = new Key(x,y,letters.charAt(i));
			if(letters.charAt(i) == '0' || letters.charAt(i) == 'P' || letters.charAt(i) == '<' || letters.charAt(i) == '_'){
				if(letters.charAt(i) == '_'){
					x = 120;
					y +=20;
				}else{
					y +=20;
					x = 20;					
				}
			}else{
				x +=20;
			}
			this.kb.add(n);
		}
	}
	
	
	/**
	 * Dibuja el teclado en pantalla
	 */
	private void drawKeyboard(){		
		Iterator<Key> itKey = kb.iterator();		
		while(itKey.hasNext()){
			this.add(itKey.next());
			this.repaint();			
		}
		this.paintFocusIn(currentIndex);
		this.add(url);
	
		
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}

	/**
	 * Pinta el fondo de verde para simular foco
	 */
	private void paintFocusIn(int index){
		this.getComponent(index).setBackground(Color.GREEN);
		this.repaint();
	}
	
	/**
	 * Pinta el fondo de negro para sacar el foco
	 */
	private void paintFocusOut(int index){
		this.getComponent(index).setBackground(Color.BLACK);
		this.repaint();
	}
	
	/*
	 * boton azul key code = 406
	 * boton amarillo key code = 405
	 * boton verde key code = 404
	 * boton rojo key code = 403
	 * boton arriba key code = 38
	 * boton derecha key code = 39
	 * boton abajo key code = 40
	 * boton izquierda key code = 37
	 * boton ok key code = 10		
	 */	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(kb.get(currentIndex).getKey());
		switch (e.getKeyCode()){
		//derecha
		case 39:
			if(currentIndex < kb.size()){				
				currentIndex++;					
			}
			//si se pasa del largo, posicionarse en el ultimo indice
			if(currentIndex >= kb.size()){
				currentIndex = kb.size() - 1;
			}else{
				this.paintFocusIn(currentIndex);
				this.paintFocusOut(currentIndex - 1);
			}
			break;
		//izquierda
		case 37:
			if(currentIndex >= 0){			
				currentIndex--;
			}
			//si se pasa para los negativos, posicionarse en el primer elemento
			if(currentIndex < 0){
				currentIndex = 0;
			}else{
				this.paintFocusIn(currentIndex);
				this.paintFocusOut(currentIndex + 1);
			}			
			break;
		//arriba
		case 38:
			break;
		//abajo
		case 40:
			break;
		//ok
		case 10:
			//backspace
			if(kb.get(currentIndex).getKey() == '<'){
				String aux = "";
				for(int i = 0; i <urlActual.length()-1;i++){
					aux = aux + urlActual.charAt(i);					
				}
				urlActual = aux;
			}else{
				urlActual = urlActual+ kb.get(currentIndex).getKey();
			}
			this.url.setText(urlActual);
			this.repaint();			
			break;
		//boton verde
		case 404:
			System.out.println("URL: " + urlActual);
			break;
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
