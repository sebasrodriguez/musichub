package MusicHub.Domain;

import java.util.List;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Domain.Contracts.IRssManager;
import MusicHub.Domain.Contracts.IVoteManager;

public class RssManager implements IRssManager {

	@Override
	public List<RssFeed> getRssFeeds() {
		List<RssFeed> feeds = ServiceLocator.getStorageService().getRssFeeds();
		IVoteManager voteManager = ServiceLocator.getVoteManager();

		for (RssFeed rssFeed : feeds) {
			rssFeed.setVotes(voteManager.getRssFeedVotes(rssFeed));
		}

		return feeds;
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
	public void addComment(RssItem rssItem, String comment) {
		ServiceLocator.getStorageService().addComment(rssItem, comment);
	}

}
