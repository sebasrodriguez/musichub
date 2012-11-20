package MusicHub.Service.Contracts;

public interface IRssParser {

	String getImageFromRssContent(String rssContent);
	String cleanHtml(String rssContent);
}
