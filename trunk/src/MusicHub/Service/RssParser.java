package MusicHub.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MusicHub.Service.Contracts.IRssParser;

public class RssParser implements IRssParser {
	
	private static final Pattern IMG_PATTERN = Pattern.compile(
            "<img\\s+.*?(?:src\\s*=\\s*(?:'|\")(.*?)(?:'|\"))(.*?)/>", 
            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);

	@Override
	public String getImageFromRssContent(String rssContent) {
		Matcher m = IMG_PATTERN.matcher(rssContent);
		while (m.find()) {
			return m.group(1);			
		}
		return "";
	}

}
