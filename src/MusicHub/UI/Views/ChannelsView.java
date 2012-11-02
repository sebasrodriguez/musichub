package MusicHub.UI.Views;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IKeyboardReceiver;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Controls.FeedDescriptionBox;
import MusicHub.UI.Controls.Keyboard;

public class ChannelsView extends BasicContainer implements IMenuContainer,
		IKeyboardReceiver {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;
	private List<UOptionItem> channelsOptionsItems;
	private List<RssFeed> rssFeeds;
	private int menuWidth = 300;
	private int menuHeight = 60;
	private FeedDescriptionBox descriptionBox;
	private boolean focusOnKeyboard = false;
	private Keyboard keyboard;

	public ChannelsView() {
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
		if (!focusOnKeyboard) {
			mainMenu.requestFocus();
		}
		super.paint(g);
	}

	private void loadRssChannelsMenu() {
		channelsOptionsItems = new LinkedList<UOptionItem>();
		rssFeeds = ServiceLocator.getRssManager().getRssFeeds();

		for (RssFeed rssFeed : rssFeeds) {
			channelsOptionsItems.add(new UOptionItem(null, rssFeed.getName(),
					rssFeed, menuWidth, menuHeight));
		}

		mainMenu = new UMenuScrollable(channelsOptionsItems, 5, this, 30, 100);
		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
		this.descriptionBox.setDescription(((RssFeed) option.getValue())
				.getDescription());
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		Object[] args = { selectedOption.getValue() };
		ViewManager.getInstance().changeView("ContentView", args);
	}

	@Override
	public void unmanagedKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("MainView", null);
			break;
		case ControlKeyConstants.GREEN:
			focusOnKeyboard = true;
			keyboard = new Keyboard(this, 50, 50);
			this.add(keyboard);
			this.popToFront(keyboard);
			this.keyboard.requestFocus();
			this.keyboard.addKeyListener(keyboard);
			this.repaint();
			break;
		}
	}

	@Override
	public void keyboardText(String text) {
		System.out.println(text);

	}
}