package MusicHub.Service.Contracts;

import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;

public interface IVoteService {
	
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem); 
	public int getRssItemVotes(RssItem rssItem,  RssFeed rssFeed); //para la noticia
	public int getRssFeedVotes(RssFeed rssFeed); //para el canal
	public void cleanVotes();

}
