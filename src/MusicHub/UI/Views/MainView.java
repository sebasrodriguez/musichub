package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;

public class MainView extends BasicContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	private HTextButton htbChannel;
	private HTextButton htbTwitter;
	private HTextButton htbAbout;
	private HTextButton htbExit;
	private int xPosition = 460;
	private int yPosition = 200;
	private int width = 100;
	private int height = 30;
	private String selectedOption;

	public MainView() {
		super("../assets/init_bg.png");

		htbChannel = new HTextButton("Canales");
		htbChannel.setFont(new Font("tiresias", Font.BOLD, 22));
		htbChannel.setForeground(Color.WHITE);
		htbChannel.setBounds(xPosition, yPosition, width, height);
		htbChannel.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		htbTwitter = new HTextButton("Twitter");
		htbTwitter.setFont(new Font("tiresias", Font.BOLD, 22));
		htbTwitter.setForeground(Color.WHITE);
		htbTwitter.setBounds(xPosition, yPosition + 40, width, height);
		htbTwitter.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		htbAbout = new HTextButton("Acerca de");
		htbAbout.setFont(new Font("tiresias", Font.BOLD, 22));
		htbAbout.setForeground(Color.WHITE);
		htbAbout.setBounds(xPosition, yPosition + 80, width, height);
		htbAbout.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		htbExit = new HTextButton("Salir");
		htbExit.setFont(new Font("tiresias", Font.BOLD, 22));
		htbExit.setForeground(Color.WHITE);
		htbExit.setBounds(xPosition, yPosition + 120, width, height);
		htbExit.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		this.add(htbChannel);
		this.add(htbTwitter);
		this.add(htbAbout);
		this.add(htbExit);

		this.popToFront(htbChannel);
		this.popToFront(htbTwitter);
		this.popToFront(htbAbout);
		this.popToFront(htbExit);

		this.selectedOption = "Channel";
		this.addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		if (this.selectedOption.equals("Channel")) {			
			htbChannel.setForeground(Color.DARK_GRAY);
			htbTwitter.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
		}
		else if (this.selectedOption.equals("Twitter")) {
			htbTwitter.setForeground(Color.DARK_GRAY);
			htbChannel.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
		}
		else if (this.selectedOption.equals("About")) {
			htbAbout.setForeground(Color.DARK_GRAY);
			htbTwitter.setForeground(Color.WHITE);
			htbChannel.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
		}
		else if (this.selectedOption.equals("Exit")) {
			htbExit.setForeground(Color.DARK_GRAY);
			htbTwitter.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbChannel.setForeground(Color.WHITE);
		}

		this.requestFocus();
		super.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case ControlKeyConstants.UP:
			if (this.selectedOption.equals("Channel")) {
				this.selectedOption = "Channel";
			}
			else if (this.selectedOption.equals("Twitter")) {
				this.selectedOption = "Channel";
			}
			else if (this.selectedOption.equals("About")) {
				this.selectedOption = "Twitter";
			}
			else if (this.selectedOption.equals("Exit")) {
				this.selectedOption = "About";
			}
			break;
		case ControlKeyConstants.DOWN:
			if (this.selectedOption.equals("Channel")) {
				this.selectedOption = "Twitter";
			}
			else if (this.selectedOption.equals("Twitter")) {
				this.selectedOption = "About";
			}
			else if (this.selectedOption.equals("About")) {
				this.selectedOption = "Exit";
			}
			else if (this.selectedOption.equals("Exit")) {
				this.selectedOption = "Exit";
			}
			break;
		case ControlKeyConstants.OK:
			if (this.selectedOption.equals("Channel")) {
				ViewManager.getInstance().changeView("ChannelsView", null);
			}
			else if (this.selectedOption.equals("Twitter")) {
				ViewManager.getInstance().changeView("TwitterView", null);
			}
			else if (this.selectedOption.equals("About")) {
				ViewManager.getInstance().changeView("AboutView", null);
			}
			else if (this.selectedOption.equals("Exit")) {
				ViewManager.getInstance().exitApplication();
			}
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();
			break;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
