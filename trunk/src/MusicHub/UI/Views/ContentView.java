package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import org.havi.ui.HText;
import org.havi.ui.HVisible;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.BasicPanel;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.DetailsPanel;
import MusicHub.UI.NewsPanel;
import MusicHub.UI.SocialPanel;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IMenuContainer;

public class ContentView extends BasicContainer implements IMenuContainer, KeyListener {

	private static final long serialVersionUID = 1L;
	private BasicPanel itemsPanelB;
	private BasicPanel detailsPanel;
	private BasicPanel socialPanel;
	private BasicPanel selectedPanel;
	private RssFeed rssFeed;
	private List<RssItem> feedItemList;
	private RssItem selectedItem;
	private HText txtVotes;
	private HText eventInfo;

	public ContentView(RssFeed rssFeed) {
		super("../assets/base_bg.png");

		this.rssFeed = rssFeed;

		HText feedName = new HText(this.rssFeed.getName());
		feedName.setFont(new Font("tiresias", Font.BOLD, 18));
		feedName.setForeground(Color.WHITE);
		feedName.setBounds(0, 47, this.getWidth(), 50);
		feedName.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		eventInfo = new HText();
		eventInfo.setFont(new Font("tiresias", Font.BOLD, 13));
		eventInfo.setForeground(Color.BLUE);
		eventInfo.setBackground(Color.WHITE);
		eventInfo.setBounds(140, 120, 300, 40);
		eventInfo.setVisible(false);

		// Toma los items del feed seleccionado
		feedItemList = ServiceLocator.getRssManager().getRssItems(rssFeed);
		setSelectedItem(feedItemList.get(0));

		itemsPanelB = new NewsPanel(this, this.getItemList(), 10, 110, 300, 540);
		detailsPanel = new DetailsPanel(this, 320, 110, 480, 540);
		socialPanel = new SocialPanel(this, getSelectedItem(), 810, 110, 200, 230);

		itemsPanelB.setName("items panel");
		detailsPanel.setName("details panel");
		socialPanel.setName("Social panel");

		txtVotes = new HText("Votos: " + String.valueOf(rssFeed.getVotes()));
		txtVotes.setFont(new Font("tiresias", Font.BOLD, 13));
		txtVotes.setForeground(Color.WHITE);
		txtVotes.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		txtVotes.setBounds(480, 125, 200, 30);
		txtVotes.setHorizontalAlignment(HVisible.HALIGN_RIGHT);

		setSelectedItem(feedItemList.get(0));
		// Muestro en el panel la info del primer Item del Feed
		((DetailsPanel) detailsPanel).showItem(feedItemList.get(0).getTitle(), feedItemList.get(0)
				.getContent(), feedItemList.get(0).getImageUrl());

		this.add(eventInfo);
		this.add(itemsPanelB);
		this.add(socialPanel);
		this.add(detailsPanel);
		this.add(txtVotes);

		this.add(feedName);
		this.pushToBack(icon);
		this.popToFront(socialPanel);

		selectedPanel = itemsPanelB;
	}

	public BasicPanel getSelectedPanel() {
		return selectedPanel;
	}

	public void setSelectedPanel(BasicPanel selectedPanel) {
		this.selectedPanel = selectedPanel;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	private List<UOptionItem> getItemList() {
		List<UOptionItem> itemList = new LinkedList<UOptionItem>();

		for (RssItem feed : feedItemList) {
			UOptionItem nItem;

			if (feed.getImageUrl() != null && !feed.getImageUrl().equals("")) {
				nItem = new UOptionItem(feed.getImageUrl(), feed.getTitle(), feed, 300, 65);
			}
			else {
				nItem = new UOptionItem(null, feed.getTitle(), feed, 300, 65);
			}
			itemList.add(nItem);
		}

		return itemList;
	}

	public RssFeed getRssFeed() {
		return rssFeed;
	}

	public void setRssFeed(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}

	public RssItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(RssItem selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("ChannelsView", null);
			break;
		case ControlKeyConstants.RIGHT:
		case ControlKeyConstants.LEFT:
			if (selectedPanel == itemsPanelB) {
				socialPanel.focusGained();
				itemsPanelB.focusLost();
				selectedPanel = socialPanel;
			}
			else {
				socialPanel.focusLost();
				itemsPanelB.focusGained();
				selectedPanel = itemsPanelB;
			}

			socialPanel.repaint();
			itemsPanelB.repaint();
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();
			break;
		default:
			break;
		}
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		setSelectedItem((RssItem) selectedOption.getValue());
		((DetailsPanel) detailsPanel).updateContent(getSelectedItem().getContent(),
				getSelectedItem().getTitle(), getSelectedItem().getImageUrl());
	}

	public void commentRssItem() {
		Object args[];
		args = new Object[2];
		args[0] = this;
		args[1] = this.getSelectedItem();

		ViewManager.getInstance().changeView("CommentsView", args);
	}

	public void voteRssItem() {
		ServiceLocator.getVoteManager().voteRssItem(rssFeed, selectedItem);
		int votes = rssFeed.getVotes();
		votes++;

		rssFeed.setVotes(votes);
		txtVotes.setTextContent("Votos: " + String.valueOf(votes), HText.ALL_STATES);
		txtVotes.repaint();
	}

	public void facebook() {
		ServiceLocator.getFacebookManager().postFacebook(getSelectedItem().getTitle());
		this.eventInfo.setVisible(true);
		this.eventInfo.setTextContent("Se ha posteado en facebook!!!", HText.ALL_STATES);
	}

	public void sendTweet() {
		ServiceLocator.getTwitterManager().postTweet(getSelectedItem().getTitle());
		this.eventInfo.setVisible(true);
		this.eventInfo.setTextContent("Se ha enviado un tweet!!!", HText.ALL_STATES);
	}
}
