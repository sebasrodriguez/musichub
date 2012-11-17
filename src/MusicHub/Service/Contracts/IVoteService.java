package MusicHub.Service.Contracts;

import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;

public interface IVoteService {
	
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem); 
	public int getRssItemVotes(); //para la noticia
	public int getRssFeedVotes(); //para el canal

}
