package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.Views.ChannelsView;

public class ChannelsList extends HContainer implements KeyListener {

	private static final long serialVersionUID = 1L;

	private List<ChannelItem> channelsItems;
	private HText channelsHeader;
	private HText votesHeader;
	private HContainer channelsContainer;
	private HIcon upArrow;
	private HIcon downArrow;
	private ChannelItem selectedItem;
	private int selectedItemIndex = 0;
	private ChannelsView channelsView;

	public ChannelsList(int x, int y, List<RssFeed> rssFeeds, ChannelsView channelsView) {
		this.setBounds(x, y, 410, 410);
		
		

		channelsHeader = new HText("Canal");
		channelsHeader.setFont(new Font("tiresias", Font.BOLD, 18));
		channelsHeader.setForeground(Color.WHITE);
		channelsHeader.setBounds(60, 5, 80, 25);
		channelsHeader.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		channelsHeader.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		votesHeader = new HText("Votos", new Font("Tiresias", Font.BOLD, 22), Color.WHITE, Color.DARK_GRAY,
				new HDefaultTextLayoutManager());
		votesHeader.setBounds(323, 5, 70, 25);
		votesHeader.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		votesHeader.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		this.channelsContainer = new HContainer(50, 40, 580, 350);
		Image upImg = Toolkit.getDefaultToolkit().getImage("../assets/up-arrow-small.png");
		Image downImg = Toolkit.getDefaultToolkit().getImage("../assets/down-arrow-small.png");
		this.upArrow = new HIcon(upImg, 10, 40, 25, 25);
		this.downArrow = new HIcon(downImg, 10, 355, 25, 25);
		this.channelsView = channelsView;

		this.add(channelsHeader);
		this.add(votesHeader);

		this.addRssFeeds(rssFeeds);

		this.add(this.upArrow);
		this.add(this.downArrow);

		this.upArrow.setVisible(false);
		this.downArrow.setVisible(true);

		addKeyListener(this);
		this.channelsView.stepedOnOption(selectedItem);
	}

	private void addRssFeeds(List<RssFeed> rssFeeds) {
		this.channelsItems = new ArrayList<ChannelItem>();

		int index = 0;
		int itemVPos = 0;

		for (RssFeed rssFeed : rssFeeds) {
			ChannelItem channelItem = new ChannelItem(rssFeed);

			channelItem.setLocation(0, itemVPos);
			itemVPos += channelItem.getHeight() + 15;

			this.channelsContainer.add(channelItem, index);
			this.channelsItems.add(channelItem);
			index++;
		}
		this.channelsContainer.setVisible(true);
		this.add(channelsContainer);

		// set shown items
		int i = 0;
		for (ChannelItem channelItem : this.channelsItems) {
			if (i > 7) {
				break;
			}
			channelItem.setShown(true);
			i++;
		}

		// Indico que la primera opcion es la seleccionada
		this.channelsItems.get(0).setSelected(true);
		selectedItem = (ChannelItem) this.channelsItems.get(0);
	}

	private List<Integer> getShownItemsIndexes() {
		List<Integer> indexes = new ArrayList<Integer>();

		int index = 0;
		for (ChannelItem channelItem : this.channelsItems) {
			if (channelItem.isShown()) {
				indexes.add(index);
			}
			else if (!channelItem.isShown() && indexes.size() > 0) {
				break;
			}
			index++;
		}

		return indexes;
	}

	private void setShownItems(List<Integer> shownItemsIndexes) {
		int index = 0;

		for (ChannelItem channelItem : this.channelsItems) {
			channelItem.setShown(shownItemsIndexes.contains(index) ? true : false);
			index++;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case ControlKeyConstants.OK:
			this.channelsView.selectedOption(selectedItem);
			break;
		case ControlKeyConstants.UP:
		case ControlKeyConstants.DOWN:
			// set selected item
			selectedItem.setSelected(false);
			if (e.getKeyCode() == 40) {
				selectedItemIndex++;
			}
			else {
				selectedItemIndex--;
			}

			if (selectedItemIndex < 0) {
				selectedItemIndex = 0;
			}
			if (selectedItemIndex >= this.channelsItems.size() - 1) {
				selectedItemIndex = this.channelsItems.size() - 1;
			}

			ChannelItem selected = null;
			if (selectedItemIndex < this.channelsContainer.getComponentCount()) {
				selected = ((ChannelItem) this.channelsContainer.getComponent(selectedItemIndex));
			}
			else {
				selectedItemIndex = this.channelsContainer.getComponentCount() - 1;
				selected = ((ChannelItem) this.getComponent(selectedItemIndex));
			}
			selected.setSelected(true);
			selectedItem = selected;

			// scroll menu if selected item is not visible
			List<Integer> shownItemsIndexes = this.getShownItemsIndexes();

			if (this.channelsItems.size() > 8) {
				// Si movio un elemento para abajo actualizo todos los visibles
				if (shownItemsIndexes.get(shownItemsIndexes.size() - 1) + 1 == selectedItemIndex) {
					// actualizamos la lista de elementos mostrados
					shownItemsIndexes.remove(0);
					shownItemsIndexes.add(selectedItemIndex);
					this.setShownItems(shownItemsIndexes);

					for (ChannelItem channelItem : this.channelsItems) {
						channelItem
								.setLocation(channelItem.getX(), channelItem.getY() - (channelItem.getHeight() + 15));
					}
				}
				else if (shownItemsIndexes.get(0) - 1 == selectedItemIndex) {
					shownItemsIndexes.remove(shownItemsIndexes.size() - 1);
					shownItemsIndexes.add(selectedItemIndex);
					this.setShownItems(shownItemsIndexes);

					for (ChannelItem channelItem : this.channelsItems) {
						channelItem
								.setLocation(channelItem.getX(), channelItem.getY() + (channelItem.getHeight() + 15));
					}
				}
			}

			// mostrar/esconder flechas
			shownItemsIndexes = this.getShownItemsIndexes();

			if (shownItemsIndexes.get(0) > 0) {
				upArrow.setVisible(true);
			}
			else {
				upArrow.setVisible(false);
			}

			if (shownItemsIndexes.get(shownItemsIndexes.size() - 1) == this.channelsItems.size() - 1) {
				downArrow.setVisible(false);
			}
			else {
				downArrow.setVisible(true);
			}

			// // Disparamos el evento de stepedOn
			this.channelsView.stepedOnOption(selectedItem);
			break;
		default:
			this.channelsView.unmanagedMenuKey(e.getKeyCode());
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.requestFocus();
	}
}
