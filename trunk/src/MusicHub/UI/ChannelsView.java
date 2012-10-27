package MusicHub.UI;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.Contracts.IMenuContainer;

public class ChannelsView extends BasicContainer implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;
	private List<UOptionItem> channelsOptionsItems;
	private List<RssFeed> rssFeeds;
	private int menuWidth = 300;
	private int menuHeight = 60;

	public ChannelsView() {
		this.loadRssChannelsMenu();
	}

	@Override
	public void paint(Graphics g) {
		mainMenu.requestFocus();
		super.paint(g);
	}

	private void loadRssChannelsMenu() {
		channelsOptionsItems = new LinkedList<UOptionItem>();
		rssFeeds = ServiceLocator.getRssManager().getRssFeeds();

		for (RssFeed rssFeed : rssFeeds) {
			channelsOptionsItems.add(new UOptionItem(null, rssFeed.getName(), rssFeed, menuWidth,
					menuHeight));
		}

		// mainMenu = new UMenuScrollable(channelsOptionsItems, 5, 30, 100);
		mainMenu = new UMenuScrollable(channelsOptionsItems, 5, this, 30, 100);
		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {		

	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		Object[] args = { selectedOption.getValue() };
		ViewManager.getInstance().changeView("ContentView", args);
	}
}