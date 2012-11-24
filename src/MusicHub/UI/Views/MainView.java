package MusicHub.UI.Views;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssItem;
import MusicHub.DataTypes.Tweet;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Controls.TweetItem;
import MusicHub.UI.Controls.TwitterTimeline;

public class MainView extends BasicContainer implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;

	public MainView() {
		super();

		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		itemList.add(new UOptionItem(null, "Canales", "ChannelsView", 150, 60));
		itemList.add(new UOptionItem(null, "Facebook", "FacebookView", 150, 60));
		itemList.add(new UOptionItem(null, "Twitter", "TwitterView", 150, 60));
		itemList.add(new UOptionItem(null, "Ayuda", "HelpView", 150, 60));
		itemList.add(new UOptionItem(null, "Acerca de", "AboutView", 150, 60));
		itemList.add(new UOptionItem(null, "Salir", "Exit", 150, 60));

		mainMenu = new UMenuScrollable(itemList, 6, this, 30, 100);

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
		ViewManager.getInstance().changeView((String) selectedOption.getValue(), null);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
	}
}
