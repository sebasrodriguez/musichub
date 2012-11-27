package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.havi.ui.HGraphicButton;
import org.havi.ui.HText;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Views.ContentView;

public class SocialPanel extends BasicPanel implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private IMenuContainer parent;
	private HGraphicButton comentarBtn;
	private HGraphicButton votarBtn;
	private HGraphicButton tweetBtn;
	private HGraphicButton fcbkBtn;
	private HGraphicButton[] accButtons;
	private int selected = 0;
	private RssItem itemSelected;

	public SocialPanel(IMenuContainer parent, RssItem itemSelected, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setBounds(x, y, w, h);
		this.setSize(w, h);
		this.parent = parent;
		this.itemSelected = itemSelected;

		accButtons = new HGraphicButton[4];

		HText simpletext = new HText("Social");
		simpletext.setFont(new Font("tiresias", Font.BOLD, 13));
		simpletext.setForeground(Color.WHITE);
		simpletext.setBounds(0, 0, this.getWidth(), 20);
		simpletext.setHorizontalAlignment(HText.HALIGN_CENTER);

		this.add(simpletext);

		Image comentarImg = Toolkit.getDefaultToolkit().getImage("../assets/comment_32.png");
		comentarBtn = new HGraphicButton(comentarImg);
		comentarBtn.setBounds(10, 55, 83, 55);
		comentarBtn.setName("comentar");
		comentarBtn.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case ControlKeyConstants.OK:
					SocialPanel.this.comentar();
					break;
				default:
					SocialPanel.this.keyPressed(arg0);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});

		accButtons[0] = comentarBtn;
		this.add(comentarBtn);

		Image votarImg = Toolkit.getDefaultToolkit().getImage("../assets/votar_32.png");
		votarBtn = new HGraphicButton(votarImg);
		votarBtn.setBounds(10, 105, 83, 55);
		votarBtn.setName("votar");
		votarBtn.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case ControlKeyConstants.OK:
					SocialPanel.this.votar();
					break;
				default:
					SocialPanel.this.keyPressed(arg0);
					break;
				}

			}
		});

		accButtons[1] = votarBtn;

		this.add(votarBtn);

		Image tweetImg = Toolkit.getDefaultToolkit().getImage("../assets/tweet_32.png");
		tweetBtn = new HGraphicButton(tweetImg);
		tweetBtn.setBounds(10, 160, 83, 55);
		tweetBtn.setName("twitter");
		tweetBtn.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case ControlKeyConstants.OK:
					SocialPanel.this.sendTweet();
					break;
				default:
					SocialPanel.this.keyPressed(arg0);
					break;
				}
			}
		});

		accButtons[2] = tweetBtn;
		this.add(tweetBtn);

		Image fcbkImg = Toolkit.getDefaultToolkit().getImage("../assets/me_gusta_32.png");
		fcbkBtn = new HGraphicButton(fcbkImg);
		fcbkBtn.setBounds(10, 220, 83, 55);
		fcbkBtn.setName("facebook");
		fcbkBtn.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case ControlKeyConstants.OK:
					SocialPanel.this.facebook();
					break;
				default:
					SocialPanel.this.keyPressed(arg0);
					break;
				}

			}
		});

		accButtons[3] = fcbkBtn;
		this.add(fcbkBtn);

		addKeyListener(this);
	}

	public RssItem getItemSelected() {
		return itemSelected;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(30, 0, 10, 2);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case ControlKeyConstants.OK:
			// Seteo el foco en el 1ero
			accButtons[selected].requestFocus();
			break;
		case ControlKeyConstants.DOWN:
			// Aumentar el selected
			selected++;

			if (selected == accButtons.length)
				selected = 0;
			accButtons[selected].requestFocus();

			break;
		case ControlKeyConstants.UP:
			System.out.println("arriba");
			selected--;

			if (selected < 0)
				selected = accButtons.length - 1;
			accButtons[selected].requestFocus();
			break;
		default:
			this.parent.unmanagedMenuKey(e.getKeyCode());
			break;
		}

	}

	public void comentar() {
		((ContentView) this.parent).comentar();
	}

	public void votar() {
		((ContentView) this.parent).votar();
	}

	public void facebook() {
		((ContentView) this.parent).facebook();
	}

	public void sendTweet() {
		((ContentView) this.parent).sendTweet();
	}
}
