package MusicHub.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.havi.ui.HContainer;

public abstract class BasicPanel extends HContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	protected boolean hasFocus;

	public BasicPanel(int x, int y, int w, int h) {
		super();
		hasFocus = false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void focusGained() {
		hasFocus = true;
	}

	public void focusLost() {
		hasFocus = false;
	}
}
