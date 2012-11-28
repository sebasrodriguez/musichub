package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
import MusicHub.UI.ItemsPanelB;
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
	private RssFeed canal;
	private List<RssItem> feedItemList;
	private List<BasicPanel> panelsList;
	private RssItem itemSelected;
	private HText votosText;
	private HText eventInfo;

	public ContentView(RssFeed canal) {
		this.canal = canal;

		HText canalName = new HText(this.canal.getName());
		canalName.setFont(new Font("tiresias", Font.BOLD, 18));
		canalName.setForeground(Color.WHITE);
		canalName.setBounds(0, 70, this.getWidth(), 50);

		eventInfo = new HText();
		eventInfo.setFont(new Font("tiresias", Font.BOLD, 13));
		eventInfo.setForeground(Color.BLUE);
		eventInfo.setBackground(Color.WHITE);
		eventInfo.setBounds(140, 120, 300, 40);

		panelsList = new ArrayList<BasicPanel>();

		// Toma los items del feed seleccionado
		feedItemList = ServiceLocator.getRssManager().getRssItems(canal);
		setItemSelected(feedItemList.get(0));

		itemsPanelB = new ItemsPanelB(this, this.getItemList(), 0, 160, 300, 540);
		panelsList.add(itemsPanelB);
		detailsPanel = new DetailsPanel(this, 310, 160, 480, 540);
		panelsList.add(detailsPanel);
		socialPanel = new SocialPanel(this, getItemSelected(), 800, 160, 230, 400);
		panelsList.add(socialPanel);

		itemsPanelB.setName("items panel");
		detailsPanel.setName("details panel");
		socialPanel.setName("Social panel");

		votosText = new HText("Votos: " + String.valueOf(canal.getVotes()));
		votosText.setFont(new Font("tiresias", Font.BOLD, 13));
		votosText.setForeground(Color.BLACK);
		votosText.setBackground(Color.WHITE);
		votosText.setBounds(480, 125, 200, 30);
		votosText.setHorizontalAlignment(HVisible.HALIGN_RIGHT);

		setItemSelected(feedItemList.get(0));
		// Muestro en el panel la info del primer Item del Feed
		((DetailsPanel) detailsPanel).showItem(feedItemList.get(0).getTitle(), feedItemList.get(0)
				.getContent(), feedItemList.get(0).getImageUrl());

		this.add(eventInfo);
		this.add(itemsPanelB);
		this.add(socialPanel);
		this.add(detailsPanel);
		this.add(votosText);

		this.add(canalName);
		this.pushToBack(icon);
		this.popToFront(socialPanel);

		selectedPanel = panelsList.get(0);
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

	public RssFeed getCanal() {
		return canal;
	}

	public void setCanal(RssFeed canal) {
		this.canal = canal;
	}

	public RssItem getItemSelected() {
		return itemSelected;
	}

	public HText getEventInfo() {
		return eventInfo;
	}

	public void setItemSelected(RssItem itemSelected) {
		this.itemSelected = itemSelected;
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		int sel;
		switch (keyCode) {
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("ChannelsView", null);
			break;
		case ControlKeyConstants.RIGHT:
			sel = panelsList.indexOf(selectedPanel);
			sel++;

			if (sel >= panelsList.size()) {
				sel = 0;
			}

			panelsList.get(sel).requestFocus();
			selectedPanel = panelsList.get(sel);
			selectedPanel.repaint();
			break;
		case ControlKeyConstants.LEFT:
			sel = panelsList.indexOf(selectedPanel);
			sel--;

			if (sel < 0) {
				sel = panelsList.size() - 1;
			}

			panelsList.get(sel).requestFocus();
			selectedPanel = panelsList.get(sel);
			selectedPanel.repaint();
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
		setItemSelected((RssItem) selectedOption.getValue());
		((DetailsPanel) detailsPanel).updateContent(getItemSelected().getContent(),
				getItemSelected().getTitle(), getItemSelected().getImageUrl());
	}

	public void comentar() {
		Object args[];
		args = new Object[2];
		args[0] = this;
		args[1] = this.getItemSelected();

		ViewManager.getInstance().changeView("CommentsView", args);
	}

	public void votar() {
		ServiceLocator.getVoteManager().voteRssItem(canal, itemSelected);
		int votos = canal.getVotes();
		votos++;

		canal.setVotes(votos);
		votosText.setTextContent("Votos: " + String.valueOf(votos), HText.ALL_STATES);
		votosText.repaint();
	}

	public void facebook() {
		ServiceLocator.getFacebookManager().postFacebook(getItemSelected().getTitle());
	}

	public void sendTweet() {
		ServiceLocator.getTwitterManager().postTweet(getItemSelected().getTitle());
		getEventInfo().setTextContent("Se ha enviado un tweet!!!", HText.ALL_STATES);
	}
}
