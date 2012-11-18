package MusicHub.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import MusicHub.DataTypes.RssFeed;
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

		Random random = new Random();
		for (RssFeed rssFeed : rssFeeds) {
			rssFeed.setVotes(random.nextInt(100));
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

}
