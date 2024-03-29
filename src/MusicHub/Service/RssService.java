package MusicHub.Service;

import java.util.ArrayList;
import java.util.List;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;
import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Service.Contracts.IRssParser;
import MusicHub.Service.Contracts.IRssService;

public class RssService implements IRssService {

	@Override
	public List<RssItem> getRssItems(RssFeed feed) {
		List<RssItem> items = new ArrayList<RssItem>();
		IRssParser parser = ServiceLocator.getRssParser();
		org.horrabin.horrorss.RssParser rss = new RssParser();

		try {
			org.horrabin.horrorss.RssFeed rssFeed = rss.load(feed.getUrl());
			RssItem item = null;

			List<RssItemBean> rssItems = rssFeed.getItems();
			for (int i = 0; i < rssItems.size(); i++) {
				RssItemBean rssItem = rssItems.get(i);

				item = new RssItem();
				item.setTitle(rssItem.getTitle());
				item.setContent(parser.cleanHtml(rssItem.getDescription()));
				item.setDate(rssItem.getPubDate());
				item.setImageUrl(parser.getImageFromRssContent(rssItem.getDescription()));
				item.setItemUrl(rssItem.getLink());
				item.setVotes(66);

				items.add(item);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return items;
	}

	public RssFeed getRssFeed(String url) {
		RssFeed feed = new RssFeed();
		org.horrabin.horrorss.RssParser rss = new RssParser();

		try {
			org.horrabin.horrorss.RssFeed rssFeed = rss.load(url);

			feed.setName(rssFeed.getChannel().getTitle());
			feed.setUrl(url);
			feed.setVotes(0);
			feed.setDescription(rssFeed.getChannel().getDescription());
		}
		catch (Exception ex) {
			return null;
		}

		return feed;
	}

}
