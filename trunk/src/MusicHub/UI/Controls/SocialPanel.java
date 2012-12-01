package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.havi.ui.HGraphicButton;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Views.ContentView;

public class SocialPanel extends BasicPanel implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private IMenuContainer parent;
	private HGraphicButton btnComment;
	private HGraphicButton btnVote;
	private HGraphicButton btnTweet;
	private HGraphicButton btnFacebook;
	private HGraphicButton[] accButtons;
	private int selected = 0;
	private RssItem selectedItem;

	public SocialPanel(IMenuContainer parent, RssItem itemSelected, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setBounds(x, y, w, h);
		this.setSize(w, h);
		this.parent = parent;
		this.selectedItem = itemSelected;
		this.hasFocus = false;

		accButtons = new HGraphicButton[4];

		Image comentarImg = Toolkit.getDefaultToolkit().getImage("../assets/comment_32.png");
		btnComment = new HGraphicButton(comentarImg);
		btnComment.setBounds(20, 10, this.getWidth() - 30, 40);
		btnComment.setName("comentar");
		btnComment.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case ControlKeyConstants.OK:
					SocialPanel.this.commentRssItem();
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

		accButtons[0] = btnComment;
		this.add(btnComment);

		Image votarImg = Toolkit.getDefaultToolkit().getImage("../assets/votar_32.png");
		btnVote = new HGraphicButton(votarImg);
		btnVote.setBounds(20, 65, this.getWidth() - 30, 40);
		btnVote.setName("votar");
		btnVote.addKeyListener(new KeyListener() {

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
					SocialPanel.this.voteRssItem();
					break;
				default:
					SocialPanel.this.keyPressed(arg0);
					break;
				}

			}
		});

		accButtons[1] = btnVote;

		this.add(btnVote);

		Image tweetImg = Toolkit.getDefaultToolkit().getImage("../assets/tweet_32.png");
		btnTweet = new HGraphicButton(tweetImg);
		btnTweet.setBounds(20, 120, this.getWidth() - 30, 40);
		btnTweet.setName("twitter");
		btnTweet.addKeyListener(new KeyListener() {

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

		accButtons[2] = btnTweet;
		this.add(btnTweet);

		Image fcbkImg = Toolkit.getDefaultToolkit().getImage("../assets/me_gusta_32.png");
		btnFacebook = new HGraphicButton(fcbkImg);
		btnFacebook.setBounds(20, 180, this.getWidth() - 30, 40);
		btnFacebook.setName("facebook");
		btnFacebook.addKeyListener(new KeyListener() {
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

		accButtons[3] = btnFacebook;
		this.add(btnFacebook);

		addKeyListener(this);
	}

	@Override
	public void focusGained() {
		super.focusGained();
		selected = 0;
		btnComment.requestFocus();
	}

	public RssItem getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);

		if (super.hasFocus) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, this.getWidth(), 5);
		}

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

	public void commentRssItem() {
		((ContentView) this.parent).commentRssItem();
	}

	public void voteRssItem() {
		((ContentView) this.parent).voteRssItem();
	}

	public void facebook() {
		((ContentView) this.parent).facebook();
	}

	public void sendTweet() {
		((ContentView) this.parent).sendTweet();
	}
}