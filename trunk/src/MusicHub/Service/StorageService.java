package MusicHub.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Service.Contracts.IStorageService;

public class StorageService implements IStorageService {

	@Override
	public List<RssFeed> getRssFeeds() {
		List<RssFeed> rssFeeds = new ArrayList<RssFeed>();

		try {
			FileInputStream feedsFile = new FileInputStream("../assets/rssFeeds");
			ObjectInputStream restore = new ObjectInputStream(feedsFile);

			rssFeeds = (List<RssFeed>) restore.readObject();

			restore.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Collections.sort(rssFeeds, new Comparator<RssFeed>() {
			public int compare(RssFeed rssFeed1, RssFeed rssFeed2) {
				return rssFeed2.getVotes() - rssFeed1.getVotes();
			}
		});

		return rssFeeds;
	}

	@Override
	public void addFeed(RssFeed feed) {
		List<RssFeed> rssFeeds = this.getRssFeeds();
		rssFeeds.add(feed);

		try {
			FileOutputStream saveFile = new FileOutputStream("../assets/rssFeeds");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			save.writeObject(rssFeeds);

			save.close();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addComment(RssItem rssItem, String comment) {
		StringBuilder formattedComment = new StringBuilder();

		formattedComment.append("\n\nRss:" + rssItem.getTitle() + "\n");
		formattedComment.append("Comentario:\n" + comment);

		try {
			FileWriter out = new FileWriter("../assets/comments.txt", true);
			out.write(formattedComment.toString());
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAbout() {
		// TODO Auto-generated method stub
		String aboutToReturn = "";
		
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader("../assets/Help.txt"));
			
			StringBuilder strBuilder = new StringBuilder();
			String line = buffReader.readLine();
			
			while(line != null){
				strBuilder.append(line);
				strBuilder.append("\n");
				line = buffReader.readLine();
			}
			
			aboutToReturn = strBuilder.toString();
			buffReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}			
		return aboutToReturn;
	}
}
