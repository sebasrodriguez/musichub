package MusicHub.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Service.Contracts.IVoteService;
import MusicHub.Util.Conf;

public class VoteService implements IVoteService {

	@Override
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem) {
		String finalUrl = Conf.getVoteUrl() + "?g=" + Conf.getGroup() + "&c="
				+ rssItem.getItemUrl() + "&ch=" + rssFeed.getUrl();
		this.sendData(finalUrl, "GET");
	}

	@Override
	public int getRssItemVotes(RssItem rssItem, RssFeed rssFeed) {
		String finalUrl = Conf.getItemVotes() + "?g=" + Conf.getGroup() + "&c="
				+ rssItem.getItemUrl() + "&ch=" + rssFeed.getUrl();
		String answer = this.sendData(finalUrl, "GET");

		answer = answer.replace("{", "");
		answer = answer.replace("}", "");
		String answers[] = answer.split(",");
		String answers2[] = answers[2].split(":");
		String answerFinal = answers2[1].replace("\"", "");

		return Integer.parseInt(answerFinal);
	}

	@Override
	public int getRssFeedVotes(RssFeed rssFeed) {
		String finalUrl = Conf.getFeedVotes() + "?g=" + Conf.getGroup() + "&ch=" + rssFeed.getUrl();
		String answer = this.sendData(finalUrl, "GET");
		answer = answer.replace("{", "");
		answer = answer.replace("}", "");
		String answers[] = answer.split(",");
		String answers2[] = answers[2].split(":");

		return Integer.parseInt(answers2[1].replace("\"", ""));
	}

	private String sendData(String url, String method) {
		String answer = "";
		HttpURLConnection httpCon = null;
		URL u = null;
		BufferedReader rd = null;
		String line = null;
		try {
			u = new URL(url);
			httpCon = (HttpURLConnection) u.openConnection();
			httpCon.setRequestMethod(method); // GET, POST
			httpCon.setDoInput(true);
			httpCon.setDoOutput(true);
			httpCon.setUseCaches(false);
			// obtener respuesta
			rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			while ((line = rd.readLine()) != null) {
				answer = answer + line;
			}
			rd.close();
			if (httpCon != null) {
				httpCon.disconnect();
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			httpCon.disconnect();
			httpCon = null;
		}
		return answer;
	}

	@Override
	public void cleanVotes() {
		String finalUrl = Conf.getCleanUrl() + "?g=" + Conf.getGroup();
		this.sendData(finalUrl, "GET");
	}
}