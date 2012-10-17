package MusicHub.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import MusicHub.DataTypes.RssFeed;
import MusicHub.Service.Contracts.IStorageService;

public class StorageService implements IStorageService {

	@Override
	public List<RssFeed> getRssFeeds() {
		List<RssFeed> rssFeeds = new ArrayList<RssFeed>();

		try {
			FileInputStream feedsFile = new FileInputStream("assets/rssFeeds");
			ObjectInputStream restore = new ObjectInputStream(feedsFile);

			rssFeeds = (List<RssFeed>) restore.readObject();

			restore.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rssFeeds;
	}

	@Override
	public void addFeed(RssFeed feed) {
		List<RssFeed> rssFeeds = this.getRssFeeds();
		rssFeeds.add(feed);

		try {
			FileOutputStream saveFile = new FileOutputStream("assets/rssFeeds");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			save.writeObject(rssFeeds);

			save.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
