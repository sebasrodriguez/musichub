package MusicHub.Service.Contracts;

import java.util.List;
import MusicHub.DataTypes.RssItem;
import MusicHub.DataTypes.RssFeed;

public interface IRssService {
	
	List<RssItem> getRssItems(RssFeed feed);
	RssFeed getRssFeed(String url);

}
