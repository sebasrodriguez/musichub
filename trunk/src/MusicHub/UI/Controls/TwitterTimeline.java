package MusicHub.UI.Controls;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import org.havi.ui.HContainer;
import org.havi.ui.HIcon;

import MusicHub.DataTypes.Tweet;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.Views.TwitterView;

public class TwitterTimeline extends HContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	private List<TweetItem> tweetsItems;
	private HContainer timelineContainer;
	private HIcon upArrow;
	private HIcon downArrow;
	private int currentPosition = 0;
	private int timelineHeight;
	private static int scrollSize = 47;
	private TwitterView twitterView;
	private int timelineContainerHeight = 118 * 4;

	public TwitterTimeline(int x, int y, List<Tweet> tweets, TwitterView twitterView) {
		this.timelineHeight = tweets.size() * 120;
		this.setBounds(x, y, 450, this.timelineHeight);

		this.timelineContainer = new HContainer(50, 0, 380, this.timelineContainerHeight);
		Image upImg = Toolkit.getDefaultToolkit().getImage("../assets/up-arrow.png");
		Image downImg = Toolkit.getDefaultToolkit().getImage("../assets/down-arrow.png");
		this.upArrow = new HIcon(upImg, 10, 10, 30, 30);
		this.downArrow = new HIcon(downImg, 10, this.timelineContainerHeight - 40, 30, 30);
		this.twitterView = twitterView;

		this.addTweetsItems(tweets);

		this.add(this.upArrow);
		this.add(this.downArrow);

		this.upArrow.setVisible(false);
		this.downArrow.setVisible(true);

		addKeyListener(this);
	}

	private void addTweetsItems(List<Tweet> tweets) {
		this.tweetsItems = new ArrayList<TweetItem>();

		int index = 0;
		int itemVPos = 0;

		for (Tweet tweet : tweets) {
			TweetItem tweetItem = new TweetItem(tweet.getUser(), tweet.getText(),
					tweet.getUserImageUrl());
			tweetItem.setLocation(0, itemVPos);
			itemVPos += tweetItem.getHeight() + 15;
			this.timelineContainer.add(tweetItem, index);
			this.tweetsItems.add(tweetItem);
			index++;
		}

		this.timelineContainer.setVisible(true);
		this.add(timelineContainer);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case ControlKeyConstants.UP:
			this.downArrow.setVisible(true);
			currentPosition -= TwitterTimeline.scrollSize;
			if (currentPosition > 0) {
				this.upArrow.setVisible(true);
			}
			else {
				this.upArrow.setVisible(false);
			}
			break;
		case ControlKeyConstants.DOWN:
			this.upArrow.setVisible(true);
			currentPosition += TwitterTimeline.scrollSize;
			if (currentPosition + this.timelineContainerHeight < timelineHeight) {
				this.downArrow.setVisible(true);
			}
			else {
				this.downArrow.setVisible(false);
			}
			break;
		default:
			this.twitterView.timelineUnmanagedKey(key.getKeyCode());
			break;
		}

		if (key.getKeyCode() == ControlKeyConstants.UP
				|| key.getKeyCode() == ControlKeyConstants.DOWN) {
			if (currentPosition < 0) {
				currentPosition = 0;
			}
			else if (currentPosition + this.timelineContainerHeight > timelineHeight) {
				currentPosition = timelineHeight - this.timelineContainerHeight;
			}
			else {
				for (TweetItem tweet : this.tweetsItems) {
					if (key.getKeyCode() == ControlKeyConstants.DOWN) {
						tweet.setBounds(tweet.getX(), tweet.getY() - TwitterTimeline.scrollSize,
								tweet.getWidth(), tweet.getHeight());
					}
					else {
						tweet.setBounds(tweet.getX(), tweet.getY() + TwitterTimeline.scrollSize,
								tweet.getWidth(), tweet.getHeight());
					}
				}
				this.repaint();
			}
		}
		System.out.println("CP:" + currentPosition + " TCH:" + this.timelineContainerHeight
				+ " TH:" + this.timelineHeight);
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
