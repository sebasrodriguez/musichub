package MusicHub.Domain;

import java.util.List;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Domain.Contracts.IRssManager;

public class RssManager implements IRssManager {

	@Override
	public List<RssFeed> getRssFeeds() {
		return ServiceLocator.getStorageService().getRssFeeds();
	}

	@Override
	public List<RssItem> getRssItems(RssFeed feed) {
		return ServiceLocator.getRssService().getRssItems(feed);
	}

	@Override
	public RssFeed addFeed(String url) {
		RssFeed rssFeed = ServiceLocator.getRssService().getRssFeed(url);

		if (rssFeed != null) {
			ServiceLocator.getStorageService().addFeed(rssFeed);
			return rssFeed;
		}

		return null;
	}

	@Override
	public void voteRss(RssItem item) {
	}

}
