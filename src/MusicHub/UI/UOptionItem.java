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

	private HTextButton button;
	private boolean selected;
	private boolean isShown;
	private String title;
	private Object value;

	public UOptionItem(HIcon ico, String title, Object value, int width, int height) {
		super();

		this.setBounds(10, 10, width, height);
		this.selected = false;

		if (ico != null) {
			ico.setBounds(0, 0, 20, 30);
			ico.setFocusable(false);
			this.add(ico);
		}

		this.setValue(value);
		this.setBackground(Color.DARK_GRAY);		
		this.title = title;
		button = new HTextButton(title);		
		button.setBounds(0, 0, this.getWidth(), Conf.getItemHeight());
		button.setForeground(Color.white);
		button.setFont(new Font(Conf.getFontName(), Font.BOLD, Conf.getFontSize()));

		this.add(button);
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);		
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
			this.setBackground(Color.DARK_GRAY);
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
}