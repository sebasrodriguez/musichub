package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.DataTypes.RssFeed;

public class ChannelItem extends HContainer {

	private static final long serialVersionUID = 1L;
	private HText channelName;
	private HText channelVotes;
	private boolean selected;
	private boolean isShown;
	private Color backgroundColor = Color.WHITE;
	private RssFeed rssFeed;

	public ChannelItem(RssFeed rssFeed) {
		super();
		this.rssFeed = rssFeed;
		this.setBounds(0, 0, 360, 30);

		channelName = new HText(this.formatName(rssFeed.getName()), new Font("Tiresias", Font.BOLD, 17),
				Color.DARK_GRAY, Color.WHITE, new HDefaultTextLayoutManager());
		channelName.setBounds(5, 5, 280, 20);
		channelName.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		channelName.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		channelVotes = new HText(Integer.toString(rssFeed.getVotes()), new Font("Tiresias", Font.BOLD, 22),
				Color.DARK_GRAY, Color.WHITE, new HDefaultTextLayoutManager());
		channelVotes.setBounds(266, 5, 80, 20);
		channelVotes.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		channelVotes.setHorizontalAlignment(HVisible.HALIGN_CENTER);

		this.add(channelName);
		this.add(channelVotes);
	}

	private String formatName(String name) {
		if (name.length() >= 30) {
			name = name.substring(0, 30);
			name += "...";
		}
		return name;
	}

	public void paint(Graphics g) {
		if (this.isSelected()) {
			this.backgroundColor = Color.LIGHT_GRAY;			
		}
		else {
			this.backgroundColor = Color.WHITE;			
		}

		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(0, 0, this.getWidth() - 2, this.getHeight() - 2, 15, 15);

		g.setColor(this.backgroundColor);
		g.fillRoundRect(1, 1, this.getWidth() - 4, this.getHeight() - 4, 15, 15);

		super.paint(g);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		repaint();
	}

	public boolean isShown() {
		return isShown;
	}

	public void setShown(boolean isShown) {
		this.isShown = isShown;
	}

	public RssFeed getRssFeed() {
		return rssFeed;
	}

	public void setRssFeed(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}
}