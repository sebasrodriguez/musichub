package MusicHub.Domain;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Domain.Contracts.IVoteManager;

public class VoteManager implements IVoteManager {

	@Override
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem) {
		if (rssFeed.getUrl() != null && !rssFeed.getUrl().isEmpty() && rssItem.getItemUrl() != null
				&& !rssItem.getItemUrl().isEmpty()) {
			ServiceLocator.getVoteService().voteRssItem(rssFeed, rssItem);
		}
	}

	@Override
	public int getRssItemVotes(RssItem rssItem, RssFeed rssFeed) {
		int result = 0;

		if (rssFeed.getUrl() != null && !rssFeed.getUrl().isEmpty() && rssItem.getItemUrl() != null
				&& !rssItem.getItemUrl().isEmpty()) {
			result = ServiceLocator.getVoteService().getRssItemVotes(rssItem, rssFeed);
		}

		return result;
	}

	@Override
	public int getRssFeedVotes(RssFeed rssFeed) {
		int result = 0;

		if (rssFeed.getUrl() != null && !rssFeed.getUrl().isEmpty()) {
			result = ServiceLocator.getVoteService().getRssFeedVotes(rssFeed);
		}

		return result;
	}

	@Override
	public void cleanVotes() {
		ServiceLocator.getVoteService().cleanVotes();
	}
}
