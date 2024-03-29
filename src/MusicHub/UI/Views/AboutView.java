package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;

public class AboutView extends BasicContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	private HText about;
	private HIcon logo;

	public AboutView() {
		super("../assets/acerca-de-view.png");

		String aboutText = ServiceLocator.getStorageManager().getAbout();
		Image img = Toolkit.getDefaultToolkit().getImage("../assets/logoUDE_2.png");
		logo = new HIcon(img);
		logo.setBounds(430, 105, 190, 100);
		logo.setVisible(true);

		about = new HText(aboutText, new Font("Tiresias", Font.BOLD, 22), Color.WHITE, Color.WHITE,
				new HDefaultTextLayoutManager());
		about.setBounds(290, 105, 545, 400);
		about.setVisible(true);
		about.setVerticalAlignment(HVisible.VALIGN_TOP);
		about.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		about.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		this.add(logo);
		this.add(about);

		this.popToFront(about);
		this.popToFront(logo);

		this.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("MainView", null);
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.requestFocus();
	}
}
