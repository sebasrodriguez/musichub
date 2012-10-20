package MusicHub.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import MusicHub.UI.Contracts.ISelectedOption;

public class MainView extends BasicContainer implements ISelectedOption {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;

	public MainView() {
		super();

		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		itemList.add(new UOptionItem(null, "Canales", 150, 60));
		itemList.add(new UOptionItem(null, "Facebook", 150, 60));
		itemList.add(new UOptionItem(null, "Twitter", 150, 60));
		itemList.add(new UOptionItem(null, "Ayuda", 150, 60));
		itemList.add(new UOptionItem(null, "Salir", 150, 60));

		mainMenu = new UMenuScrollable(itemList, 5, 150, 150);

		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void paint(Graphics g) {
		mainMenu.requestFocus();
		super.paint(g);
	}

	@Override
	public void selectedOption(int selectedIndex) {
		ViewManager.getInstance().changeView("ChannelsView");
	}

}
