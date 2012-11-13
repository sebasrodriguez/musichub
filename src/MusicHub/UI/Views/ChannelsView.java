package MusicHub.UI.Views;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.tv.xlet.XletContext;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Controls.FeedDescriptionBox;
import MusicHub.UI.Controls.VideoResizer;

public class ChannelsView extends BasicContainer implements IMenuContainer {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;
	private List<UOptionItem> channelsOptionsItems;
	private List<RssFeed> rssFeeds;
	private int menuWidth = 300;
	private int menuHeight = 60;
	private FeedDescriptionBox descriptionBox;

	public ChannelsView(XletContext context) {
		// Mostramos background con transparencia
		super("../assets/Background-ChannelsView.png");
		
		// Cambiamos tamano video
		VideoResizer.getInstance(null).changeVideoSize(new Rectangle(0, 0, 100, 100),
				new Rectangle(350, 99, 330, 300));
		
		this.loadDescriptionBox();
		this.loadRssChannelsMenu();
	}

	private void loadDescriptionBox() {
		descriptionBox = new FeedDescriptionBox();

		this.add(descriptionBox);
		this.popToFront(descriptionBox);
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

		mainMenu = new UMenuScrollable(channelsOptionsItems, 5, this, 30, 100);
		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
		this.descriptionBox.setDescription(((RssFeed) option.getValue()).getDescription());
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		Object[] args = { selectedOption.getValue() };
		ViewManager.getInstance().changeView("ContentView", args);
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.RED:
			VideoResizer.getInstance(null).returnToOriginalSize();
			ViewManager.getInstance().changeView("MainView", null);
			break;
		case ControlKeyConstants.GREEN:
			VideoResizer.getInstance(null).returnToOriginalSize();
			ViewManager.getInstance().changeView("AddChannelView", null);
			break;
		}
	}
}