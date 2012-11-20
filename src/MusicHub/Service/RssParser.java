package MusicHub.Service;

import MusicHub.Service.Contracts.IRssParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser implements IRssParser {

	private static final Pattern IMG_PATTERN = Pattern.compile(
			"<img\\s+.*?(?:src\\s*=\\s*(?:'|\")(.*?)(?:'|\"))(.*?)/>", Pattern.DOTALL
					| Pattern.CASE_INSENSITIVE);

	@Override
	public String getImageFromRssContent(String rssContent) {
		if (rssContent != null && rssContent.length() > 0) {
			Matcher m = IMG_PATTERN.matcher(rssContent);
			while (m.find()) {
				return m.group(1);
			}
		}
		return "";
	}
	
	@Override
	public String cleanHtml(String rssContent){
		return rssContent.replaceAll("\\<.*?\\>", "");
	}

}
