package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import org.havi.ui.HContainer;

import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.Contracts.IKeyboardReceiver;

public class Keyboard extends HContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	private String letters = "1234567890QWERTYUIOPASDFGHJKL.ZXCVBNM:/_ ";
	private int xPosition;
	private int keyWidth = 50;
	private int keyHeight = 50;
	private int yPosition;
	private int xStartPosition;
	private ArrayList<Key> kb;
	private int currentIndex = 0;
	private int oldIndex = 0;
	private String actualString = "";
	private IKeyboardReceiver keyboardReceiver;

	public Keyboard(IKeyboardReceiver keyboardReceiver, int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
		this.xStartPosition = x;
		this.setBounds(xPosition, yPosition, 700, 700);
		this.fillArray();
		this.drawKeyboard();
		this.keyboardReceiver = keyboardReceiver;
	}

	/**
	 * Carga el Arraylist <Key>
	 */
	private void fillArray() {
		kb = new ArrayList<Key>();
		for (int i = 0; i < letters.length(); i++) {
			Key n = new Key(xPosition, yPosition, letters.charAt(i),
					this.keyWidth, this.keyHeight);
			if (letters.charAt(i) == '0' || letters.charAt(i) == 'P'
					|| letters.charAt(i) == '.' || letters.charAt(i) == '_') {
				if (letters.charAt(i) == '_') {
					xPosition = xStartPosition + (keyWidth * 4);
					yPosition += keyHeight;
				} else {
					yPosition += keyHeight;
					xPosition = xStartPosition;
				}
			} else {
				xPosition += keyWidth;
			}
			this.kb.add(n);
		}
	}

	/**
	 * Dibuja el teclado en pantalla
	 */
	private void drawKeyboard() {
		Iterator<Key> itKey = kb.iterator();
		while (itKey.hasNext()) {
			this.add(itKey.next());
		}
		this.paintFocusIn(currentIndex);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}

	/**
	 * Pinta el fondo de verde para simular foco
	 */
	private void paintFocusIn(int index) {
		this.getComponent(index).setBackground(Color.GREEN);
		this.repaint();
	}

	/**
	 * Pinta el fondo de negro para sacar el foco
	 */
	private void paintFocusOut(int index) {
		this.getComponent(index).setBackground(Color.DARK_GRAY);
		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case ControlKeyConstants.RIGHT:
			if (currentIndex < kb.size()) {
				currentIndex++;
			}
			// si se pasa del largo, posicionarse en el ultimo indice
			if (currentIndex >= kb.size()) {
				currentIndex = kb.size() - 1;
			} else {
				this.paintFocusIn(currentIndex);
				this.paintFocusOut(currentIndex - 1);
			}
			break;
		case ControlKeyConstants.LEFT:
			if (currentIndex >= 0) {
				currentIndex--;
			}
			// si se pasa para los negativos, posicionarse en el primer elemento
			if (currentIndex < 0) {
				currentIndex = 0;
			} else {
				this.paintFocusIn(currentIndex);
				this.paintFocusOut(currentIndex + 1);
			}
			break;
		case ControlKeyConstants.UP:
			oldIndex = currentIndex;
			currentIndex = currentIndex - 10;
			
			if(currentIndex < 0){
				currentIndex = 0;
			}
			
			this.paintFocusOut(oldIndex);
			this.paintFocusIn(currentIndex);
			
			
			break;
		case ControlKeyConstants.DOWN:
			oldIndex = currentIndex;
			currentIndex = currentIndex + 10;
			
			if(currentIndex > kb.size() - 1){
				currentIndex = kb.size() - 1;
			}
			
			this.paintFocusOut(oldIndex);
			this.paintFocusIn(currentIndex);			
			
			break;
		case ControlKeyConstants.OK:
			/*
			 * // backspace if (kb.get(currentIndex).getKey() == '<') { String
			 * aux = ""; for (int i = 0; i < actualString.length() - 1; i++) {
			 * aux = aux + actualString.charAt(i); } actualString = aux; } else
			 * {
			 */
			actualString = actualString + kb.get(currentIndex).getKey();
			this.keyboardReceiver.keyboardText(actualString);
			break;
		case ControlKeyConstants.YELLOW:
			if (!actualString.isEmpty()) {
				actualString = actualString.substring(0,actualString.length() - 1);
			}
			this.keyboardReceiver.keyboardText(actualString);
			break;
		default:
			this.keyboardReceiver.unamangedKeyboardKey(e.getKeyCode());
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
