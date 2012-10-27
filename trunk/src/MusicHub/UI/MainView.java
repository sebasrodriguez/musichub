package MusicHub.UI;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import MusicHub.UI.Contracts.IMenuContainer;

public class MainView extends BasicContainer implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;

	public MainView() {
		super();

		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		itemList.add(new UOptionItem(null, "Canales", "Canales", 150, 60));
		itemList.add(new UOptionItem(null, "Facebook", "Facebook", 150, 60));
		itemList.add(new UOptionItem(null, "Twitter", "Twitter", 150, 60));
		itemList.add(new UOptionItem(null, "Ayuda", "Ayuda", 150, 60));
		itemList.add(new UOptionItem(null, "Salir", "Salir", 150, 60));

		mainMenu = new UMenuScrollable(itemList, 5, this, 150, 150);

		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void paint(Graphics g) {
		mainMenu.requestFocus();
		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		ViewManager.getInstance().changeView("ChannelsView", null);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
		System.out.println("stepedOnOption" + option.getValue());
	}

}
