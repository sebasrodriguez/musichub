package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

public class TweetItem extends HContainer {

	private static final long serialVersionUID = 1L;
	private HText userText;
	private HText tweetText;
	private HIcon ico;

	public TweetItem(String user, String tweet, String userImageUrl) {
		super();
		this.setBounds(0, 0, 400, 105);

		ExecutorService executor = Executors.newFixedThreadPool(3);
		Runnable fetcher = new ImageFetcher(userImageUrl, ico, this, 4, 4, 50, 50);

		executor.execute(fetcher);
		executor.shutdown();

		userText = new HText(user, new Font("Tiresias", Font.BOLD, 22), Color.DARK_GRAY,
				Color.WHITE, new HDefaultTextLayoutManager());
		userText.setBounds(60, 10, 300, 20);
		userText.setVisible(true);
		userText.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		tweetText = new HText(this.formatTweet(tweet), new Font("Tiresias", Font.BOLD, 14),
				Color.DARK_GRAY, Color.WHITE, new HDefaultTextLayoutManager());
		tweetText.setBounds(5, 45, 370, 50);
		tweetText.setVisible(true);
		tweetText.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		tweetText.setVerticalAlignment(HVisible.VALIGN_TOP);

		this.add(userText);
		this.add(tweetText);
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(0, 0, 375, 100, 15, 15);
		g.setColor(Color.WHITE);
		g.fillRoundRect(1, 1, 373, 98, 15, 15);		
		super.paint(g);
	}

	private String formatTweet(String tweet) {
		char[] chars = tweet.toCharArray();
		StringBuilder finalTweet = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			if ((i % 50) == 0) {
				finalTweet.append("\n");
			}
			finalTweet.append(chars[i]);
		}

		return finalTweet.toString();
	}	
}