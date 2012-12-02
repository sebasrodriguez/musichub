package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import org.havi.ui.HIcon;
import org.havi.ui.HState;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.UI.Contracts.IMenuContainer;

public class DetailsPanel extends BasicPanel implements IMenuContainer, KeyListener {

	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Image image;
	private HText titleText;
	private HText contentText;
	private HIcon ico;
	private IMenuContainer parent;

	public DetailsPanel(IMenuContainer parent, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setBounds(x, y, w, h);
		this.parent = parent;
	}

	public void showItem(String title, String content, String imgURL) {
		try {
			image = null;
			if (imgURL != null && !imgURL.equals("") && imgURL.startsWith("http")) {
				image = Toolkit.getDefaultToolkit().getImage(new URL(imgURL));
				image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			}
		}
		catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		this.content = content;
		this.title = title;

		ico = null;

		if (getImage() != null) {
			ico = new HIcon(image);
			ico.setBounds(10, 50, 120, 100);
			this.add(ico);
			this.popToFront(ico);
		}

		titleText = new HText(this.wrapContent(this.title, 60, 3));
		titleText.setFont(new Font("tiresias", Font.BOLD, 15));
		titleText.setForeground(Color.WHITE);
		titleText.setBounds(10, 10, this.getWidth() - 15, 30);
		titleText.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		titleText.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		if (isTooLength(this.content)) {
			this.content = wrapContent(this.content, 65, 21);
		}

		contentText = new HText(this.content);
		contentText.setFont(new Font("tiresias", Font.PLAIN, 15));
		contentText.setForeground(Color.WHITE);
		contentText.setBounds(10, 180, this.getWidth() - 15, 350);
		contentText.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		contentText.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		contentText.setVerticalAlignment(HVisible.VALIGN_TOP);

		this.add(contentText);
		this.add(titleText);

		addKeyListener(this);
	}

	private boolean isTooLength(String content) {
		return content.length() > 25;
	}

	private String wrapContent(String content, int wrapLength, int maxLines) {
		StringBuilder wrappedContent = new StringBuilder();
		char[] chars = content.toCharArray();
		int j = 0;
		int lines = 1;

		for (int i = 0; i < chars.length; i++) {
			if (j == wrapLength) {
				wrappedContent.append("\n");
				j = 0;
				lines++;
				if (lines > maxLines) {
					wrappedContent.append("...");
					break;
				}
			}
			wrappedContent.append(chars[i]);
			j++;
		}

		return wrappedContent.toString();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);
		super.paint(g);
	}

	public Image getImage() {
		return this.image;
	}

	public void updateContent(String desc, String title, String imgSrc) {
		if (isTooLength(desc)) {
			desc = wrapContent(desc, 65, 21);
		}
		contentText.setTextContent(desc, HState.ALL_STATES);
		titleText.setTextContent(this.wrapContent(title, 60, 3), HState.ALL_STATES);

		try {
			if (!imgSrc.equals("") && imgSrc.length() > 0 && imgSrc.toLowerCase().startsWith("http")) {
				image = Toolkit.getDefaultToolkit().getImage(new URL(imgSrc));
				image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
				ico.setGraphicContent(image, HState.ALL_STATES);
				ico.repaint();
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.parent.unmanagedMenuKey(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		this.parent.unmanagedMenuKey(keyCode);
	}
}
