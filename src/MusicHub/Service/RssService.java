package MusicHub.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Service.Contracts.IRssParser;
import MusicHub.Service.Contracts.IRssService;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssService implements IRssService {

	@Override
	public List<RssItem> getRssItems(RssFeed feed) {
		List<RssItem> items = new ArrayList<RssItem>();
		IRssParser parser = ServiceLocator.getRssParser();
		
		try {
			URL feedUrl = new URL(feed.getUrl());
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed newFeed = input.build(new XmlReader(feedUrl));

			List<SyndEntryImpl> feeds = newFeed.getEntries();
			RssItem item = null;
			for (SyndEntryImpl syndFeed : feeds) {
				item = new RssItem();
				
				item.setTitle(syndFeed.getTitle());
				item.setContent(syndFeed.getDescription().getValue());
				item.setDate(syndFeed.getPublishedDate());
				item.setImageUrl(parser.getImageFromRssContent(item.getContent()));
				// TODO: cargar votos de servicio
				item.setVotes(66);
				
				items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

}
