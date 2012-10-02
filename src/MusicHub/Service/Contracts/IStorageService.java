package MusicHub.Service.Contracts;

import java.util.List;
import MusicHub.DataTypes.RssFeed;

public interface IStorageService {

	List<RssFeed> getRssFeeds();
	void addFeed(RssFeed feed);
	
}
