package MusicHub.Service;

import MusicHub.Service.Contracts.IRssParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser implements IRssParser {

	private static final Pattern IMG_PATTERN = Pattern.compile(
			"<img\\s+.*?(?:src\\s*=\\s*(?:'|\")(.*?)(?:'|\"))(.*?)/>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

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
	public String cleanHtml(String rssContent) {
		String cleanedHtml = rssContent.replaceAll("\\<.*?\\>", "");

		cleanedHtml = cleanedHtml.replace("&nbsp;", " ");
		cleanedHtml = cleanedHtml.replace("&aacute;", "a");
		cleanedHtml = cleanedHtml.replace("&eacute;", "e");
		cleanedHtml = cleanedHtml.replace("&iacute;", "i");
		cleanedHtml = cleanedHtml.replace("&oacute;", "o");
		cleanedHtml = cleanedHtml.replace("&uacute;", "u");
		
		cleanedHtml = cleanedHtml.replace("&Aacute;", "a");
		cleanedHtml = cleanedHtml.replace("&Eacute;", "e");
		cleanedHtml = cleanedHtml.replace("&Iacute;", "i");
		cleanedHtml = cleanedHtml.replace("&Oacute;", "o");
		cleanedHtml = cleanedHtml.replace("&Uacute;", "u");
		
		cleanedHtml = cleanedHtml.replace("&ldquo;", "\"");
		cleanedHtml = cleanedHtml.replace("&rdquo;", "\"");

		return cleanedHtml;
	}

}
