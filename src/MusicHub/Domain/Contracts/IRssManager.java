package MusicHub.Domain.Contracts;

import java.util.List;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;

public interface IRssManager {
	
	List<RssFeed> getRssFeeds();
	List<RssItem> getRssItems(RssFeed feed);
	void addFeed(RssFeed feed);
	void voteRss(RssItem item);

}
