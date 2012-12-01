package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.havi.ui.HNavigable;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

import MusicHub.Util.Conf;

public class Key extends HTextButton implements KeyListener, HNavigable {

	private static final long serialVersionUID = 1L;
	private char character;
	private int x;
	private int y;

	public Key(int x, int y, char character, int width, int height) {
		super(String.valueOf(character));
		this.character = character;
		this.x = x;
		this.y = y;
		this.setBounds(this.x, this.y, width, height);
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		this.setFont(new Font(Conf.getFontName(), Font.BOLD, 20));	

	}

	public char getKey() {
		return this.character;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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
}
