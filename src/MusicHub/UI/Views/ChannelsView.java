package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.tv.xlet.XletContext;

import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Controls.ChannelItem;
import MusicHub.UI.Controls.ChannelsList;
import MusicHub.UI.Controls.FeedDescriptionBox;
import MusicHub.UI.Controls.VideoResizer;

public class ChannelsView extends BasicContainer {

	private static final long serialVersionUID = 1L;
	private FeedDescriptionBox descriptionBox;

	public ChannelsView(XletContext context) {
		super("../assets/channel-view-bg.png");

		// Cambiamos tamano video
		VideoResizer.getInstance(null)
				.changeVideoSize(new Rectangle(0, 0, 100, 100), new Rectangle(428, 140, 582, 365));

		HText channelsTitle = new HText("Canales");
		channelsTitle.setFont(new Font("tiresias", Font.BOLD, 18));
		channelsTitle.setForeground(Color.WHITE);
		channelsTitle.setBounds(0, 47, this.getWidth(), 50);
		channelsTitle.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		this.loadDescriptionBox();
		ChannelsList channelsList = new ChannelsList(10, 99, ServiceLocator.getRssManager().getRssFeeds(), this);

		this.add(channelsTitle);
		this.popToFront(channelsTitle);

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
		if (channelItem.getRssFeed().getDescription() != null && channelItem.getRssFeed().getDescription().length() > 0) {
			this.descriptionBox.setDescription(channelItem.getRssFeed().getDescription());
		}
		else {
			this.descriptionBox.setDescription("No hay descripcion para el canal seleccionado");
		}
	}

	public void selectedOption(ChannelItem channelItem) {
		VideoResizer.getInstance(null).returnToOriginalSize();
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