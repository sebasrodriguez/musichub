package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
			if (imgURL != null && !imgURL.equals("")) {
				image = Toolkit.getDefaultToolkit().getImage(new URL(imgURL));
				image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			}
		}
		catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
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

		titleText = new HText(this.title);
		titleText.setFont(new Font("tiresias", Font.BOLD, 13));
		titleText.setForeground(Color.WHITE);
		titleText.setBounds(10, 10, this.getWidth(), 20);
		titleText.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		if (isTooLength(this.content)) {
			this.content = wrapContent(this.content);
		}

		contentText = new HText(this.content);
		contentText.setFont(new Font("tiresias", Font.PLAIN, 13));
		contentText.setForeground(Color.WHITE);
		contentText.setBounds(10, 180, this.getWidth(), this.getHeight() - 10);
		contentText.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		contentText.setVerticalAlignment(HVisible.VALIGN_TOP);

		this.add(contentText);
		this.add(titleText);

		addKeyListener(this);
	}

	private boolean isTooLength(String content) {
		return content.length() > 25;
	}

	private String wrapContent(String content) {
		final int WRAP_LENGTH = 62;
		int i = 0, x = 0;
		boolean f = false;
		String wContent = new String();

		while (i < content.length() && !f) {
			if (Character.isSpaceChar(content.charAt(i)) && x > WRAP_LENGTH) {
				wContent += '\n';
				x = 0;
			}
			else {
				wContent += content.charAt(i);
				x++;
			}
			i++;
		}
		return wContent;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

	public Image getImage() {
		return this.image;
	}

	public void updateContent(String desc, String title, String imgSrc) {
		if (isTooLength(desc)) {
			desc = wrapContent(desc);
		}
		contentText.setTextContent(desc, HState.ALL_STATES);
		titleText.setTextContent(title, HState.ALL_STATES);

		try {
			if (!imgSrc.equals("")) {
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
