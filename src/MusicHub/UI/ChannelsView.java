package MusicHub.UI;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.Contracts.ISelectedOption;

public class ChannelsView extends BasicContainer implements ISelectedOption {

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

	@Override
	public void selectedOption(int selectedIndex) {
		Object[] args = { this.rssFeeds.get(selectedIndex) };
		ViewManager.getInstance().changeView("ContentView",
				args);
	}

	private void loadRssChannelsMenu() {
		channelsOptionsItems = new LinkedList<UOptionItem>();
		rssFeeds = ServiceLocator.getRssManager().getRssFeeds();

		for (RssFeed rssFeed : rssFeeds) {
			channelsOptionsItems.add(new UOptionItem(null, rssFeed.getName(),
					menuWidth, menuHeight));
		}

		mainMenu = new UMenuScrollable(channelsOptionsItems, 5, 30, 100);
		this.add(mainMenu);
		this.popToFront(mainMenu);
	}
}