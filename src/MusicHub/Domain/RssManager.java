package MusicHub.Domain;

import java.util.List;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Domain.Contracts.IRssManager;

public class RssManager implements IRssManager {

	@Override
	public List<RssFeed> getRssFeeds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RssItem> getRssItems(RssFeed feed) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFeed(RssFeed feed) {
		// TODO Auto-generated method stub
	}

	@Override
	public void voteRss(RssItem item) {
		// TODO Auto-generated method stub

	}

}
