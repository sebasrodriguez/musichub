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
import MusicHub.UI.Controls.ChannelItem;
import MusicHub.UI.Controls.ChannelsList;
import MusicHub.UI.Controls.FeedDescriptionBox;
import MusicHub.UI.Controls.VideoResizer;

public class ChannelsView extends BasicContainer {

	private static final long serialVersionUID = 1L;	
	private FeedDescriptionBox descriptionBox;

	public ChannelsView(XletContext context) {
		// Mostramos background con transparencia
		super("../assets/Background-ChannelsView.png");

		// Cambiamos tamano video
		VideoResizer.getInstance(null).changeVideoSize(new Rectangle(0, 0, 100, 100),
				new Rectangle(350, 99, 330, 300));

		this.loadDescriptionBox();
		ChannelsList channelsList = new ChannelsList(10, 99, ServiceLocator.getRssManager()
				.getRssFeeds(), this);

		this.add(channelsList);
		this.popToFront(channelsList);
	}

	private void loadDescriptionBox() {
		descriptionBox = new FeedDescriptionBox();

		this.add(descriptionBox);
		this.popToFront(descriptionBox);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public void stepedOnOption(ChannelItem channelItem) {
		this.descriptionBox.setDescription(channelItem.getRssFeed().getDescription());
	}

	public void selectedOption(ChannelItem channelItem) {
		Object[] args = { channelItem.getRssFeed() };
		ViewManager.getInstance().changeView("ContentView", args);
	}

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