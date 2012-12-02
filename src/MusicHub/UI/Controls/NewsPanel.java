package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import MusicHub.UI.Contracts.IMenuContainer;

public class NewsPanel extends BasicPanel implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable itemsMenu;
	private IMenuContainer parent;

	public NewsPanel(IMenuContainer container, List<UOptionItem> itemList, int x, int y, int w, int h) {
		super(x, y, w, h);

		super.hasFocus = true;

		this.setBounds(x, y, w, h);
		this.setSize(w, h);

		this.parent = container;
		itemsMenu = new UMenuScrollable(itemList, 7, this, 10, 10);
		itemsMenu.setFontStyle(Font.PLAIN, 13);
		this.add(itemsMenu);
		this.popToFront(itemsMenu);
	}

	public NewsPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);

		if (super.hasFocus) {
			itemsMenu.requestFocus();
			g.setColor(Color.RED);
			g.fillRect(0, 0, this.getWidth(), 5);
		}

		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		this.parent.selectedOption(selectedOption);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	public String getItemDescription(String description) {
		return description;
	}

	public int getSelectedOption() {
		return itemsMenu.getSelectedItemIndex();
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		this.parent.unmanagedMenuKey(keyCode);
	}
}
