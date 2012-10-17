package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HTextButton;
import MusicHub.Util.Conf;

public class UOptionItem extends HContainer {

	private static final long serialVersionUID = 1L;
	private HIcon ico;
	private HTextButton button;
	private boolean selected;

	public UOptionItem(HIcon ico, String title) {
		super();

		this.setBounds(10, 10, 0, 0);
		this.setSize(Conf.getMenuWidth(), Conf.getMenuHeight());
		this.selected = false;

		this.ico = ico;
		ico.setBounds(0, 0, 20, 30);
		ico.setFocusable(false);

		button = new HTextButton(title);
		button.setBounds(0, 0, this.getWidth(), Conf.getItemHeight());
		button.setForeground(Color.white);
		button.setFont(new Font(Conf.getFontName(), Font.BOLD, Conf
				.getFontSize()));

		this.add(ico);
		this.add(button);
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
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
		} else {
			this.setBackground(Color.BLACK);
		}
		repaint();
	}
}
