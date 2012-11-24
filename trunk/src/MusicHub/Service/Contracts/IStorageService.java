package MusicHub.Service.Contracts;

import java.util.List;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;

public interface IStorageService {

	List<RssFeed> getRssFeeds();
	void addFeed(RssFeed feed);
	void addComment(RssItem rssItem, String comment);
	String getAbout();
}
