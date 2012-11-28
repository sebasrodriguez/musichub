package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Util.Conf;

public class UOptionItem extends HContainer {

	private static final long serialVersionUID = 1L;
	private HText txtTitle;
	private boolean selected;
	private boolean isShown;
	private String title;
	private Object value;
	private HIcon icon;
	private String imgUrl;

	public UOptionItem(String imageUrl, String title, Object value, int width, int height) {
		super();

		this.setBounds(0, 0, width, height);
		this.selected = false;
		this.setValue(value);
		this.title = title;
		txtTitle = new HText(this.formatTitle(this.title));
		txtTitle.setBounds(10, 0, this.getWidth(), this.getHeight());
		txtTitle.setForeground(Color.WHITE);
		txtTitle.setFont(new Font(Conf.getFontName(), Font.BOLD, 18));
		txtTitle.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		txtTitle.setVerticalAlignment(HVisible.VALIGN_TOP);

		if (imageUrl != null) {
			this.imgUrl = imageUrl;
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Image image = null;
						image = Toolkit.getDefaultToolkit().getImage(
								new URL(UOptionItem.this.imgUrl));
						image = image.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

						icon = new HIcon(image);
						icon.setBounds(0, 0, 65, 65);
						icon.setBackgroundMode(HIcon.BACKGROUND_FILL);
						icon.setFocusable(false);
						UOptionItem.this.add(icon);
						UOptionItem.this.popToFront(icon);
						txtTitle.setLocation(65, 0);
					}
					catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();
		}
		this.add(txtTitle);
	}

	private String formatTitle(String title) {
		if (title.length() > 40 * 3) {
			title = title.substring(0, 40 * 3) + "...";
		}
		char[] chars = title.toCharArray();
		StringBuilder finalTitle = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			if ((i % 40) == 0) {
				finalTitle.append("\n");
			}
			finalTitle.append(chars[i]);
		}

		return finalTitle.toString();
	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;

		if (selected) {
			this.setBackground(Color.GRAY);
		}
		else {
			this.setBackground(Color.BLACK);
		}
		repaint();
	}

	public boolean isShown() {
		return this.isShown;
	}

	public void isShown(boolean value) {
		this.isShown = value;
	}

	public String getTitle() {
		return title;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setFontStyle(int style, int size) {
		txtTitle.setFont(new Font(Conf.getFontName(), style, size));
	}
}
