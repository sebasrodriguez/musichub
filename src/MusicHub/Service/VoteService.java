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

	/*Codigo noticia = rssItem.getItemUrl
	 * Codigo canal = rrsFeed.getUrl
	 * Parametros entrada: 
• [g] grupo-> número del 1 al 4
• [c] articulo-> código del artículo
• [ch] canal -> código del canal al que pertence el artículo
ejemplo: http://www.evolutiion.com/ude/agregarvoto?g=1&c=abcd&ch=xyz
	 */
	
	@Override
	public void voteRssItem(RssFeed rssFeed, RssItem rssItem) {
		// TODO Auto-generated method stub
		
		String finalUrl = Conf.getVoteUrl() + "?g=" + Conf.getGroup() + "&c=" + rssItem.getItemUrl() + "&ch=" + rssFeed.getUrl();
		
		String answer = this.sendData(finalUrl, "POST");
		
		System.out.println(answer);
	}

/*	Parámetros entrada: 
		• g [grupo]-> número del 1 al 4
		• [c] articulo-> código del artículo
		• [ch] canal -> código del canal al que pertence el artículo
		ejemplo: http://www.evolutiion.com/ude/damevotos?g=1&c=abcd&ch=xyz */
	@Override
	public int getRssItemVotes(RssItem rssItem, RssFeed rssFeed) {
		// TODO Auto-generated method stub
		
		String finalUrl = Conf.getVoteUrl() + "?g=" + Conf.getGroup() + "&c=" + rssItem.getItemUrl() + "&ch=" + rssFeed.getUrl();
		String answer = this.sendData(finalUrl, "GET");
		
		answer = answer.replace("{", "");
		answer = answer.replace("}", "");
		String answers[] = answer.split(",");
		String answers2[] = answers[2].split(":");	
		String answerFinal = answers2[1].replace("\"", "");
	
		return Integer.parseInt(answerFinal);		
	}

	
	/*Parámetros entrada: 
		• g [grupo]-> número del 1 al 4
		• [ch] canal-> código del canal 
		ejemplo: http://www.evolutiion.com/ude/damevotoscanal?g=1&ch=xyz */
	
	@Override
	public int getRssFeedVotes(RssFeed rssFeed) {
		// TODO Auto-generated method stub
		String finalUrl = Conf.getVoteUrl() + "?g=" + Conf.getGroup() + "&ch=" + rssFeed.getUrl();
		String answer = this.sendData(finalUrl, "GET");
		
		answer = answer.replace("{", "");
		answer = answer.replace("}", "");
		String answers[] = answer.split(",");
		String answers2[] = answers[2].split(":");		
	
		return Integer.parseInt(answers2[1]);
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
			rd = new BufferedReader(new InputStreamReader(
					httpCon.getInputStream()));
			while ((line = rd.readLine()) != null) {
				answer = answer + line;
			}
			rd.close();
			if (httpCon != null) {
				httpCon.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpCon.disconnect();
			httpCon = null;
		}
		return answer;
	}

}
