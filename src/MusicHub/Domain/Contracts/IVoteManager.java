package MusicHub.Domain.Contracts;

import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;

public interface IVoteManager {
	
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem); 
	public int getRssItemVotes(RssItem rssItem,  RssFeed rssFeed);
	public int getRssFeedVotes(RssFeed rssFeed);
	public void cleanVotes();

}
