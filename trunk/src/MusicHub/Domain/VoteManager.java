package MusicHub.Domain;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Domain.Contracts.IVoteManager;

public class VoteManager implements IVoteManager {

	@Override
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem) {
		// TODO Auto-generated method stub
		if(rssFeed.getUrl().isEmpty() || rssItem.getItemUrl().isEmpty()){
			
		}else{
			ServiceLocator.getVoteService().voteRssItem(rssFeed, rssItem);
		}
		
	}

	@Override
	public int getRssItemVotes(RssItem rssItem, RssFeed rssFeed) {
		// TODO Auto-generated method stub
		int result = 0;
		if(rssFeed.getUrl().isEmpty() || rssItem.getItemUrl().isEmpty()){
			
		}else{
			result = ServiceLocator.getVoteService().getRssItemVotes(rssItem, rssFeed);
		}
		return result;
	}

	@Override
	public int getRssFeedVotes(RssFeed rssFeed) {
		// TODO Auto-generated method stub
		int result = 0;
		if(rssFeed.getUrl().isEmpty()){
			
		}else{
			result = ServiceLocator.getVoteService().getRssFeedVotes(rssFeed);
		}
		return result;		
	}

	@Override
	public void cleanVotes() {
		// TODO Auto-generated method stub
		ServiceLocator.getVoteService().cleanVotes();
		
	}

}
