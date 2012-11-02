package MusicHub.Service;

import java.net.URL;
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
				item.setContent(rssItem.getDescription());
				item.setDate(rssItem.getPubDate());
				item.setImageUrl(parser.getImageFromRssContent(rssItem.getDescription()));
				item.setVotes(66);
				
				items.add(item);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return items;
	}

}
