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
		String url = Conf.getVoteUrl();
		
		
				
		
		

	}

/*	Parámetros entrada: 
		• g [grupo]-> número del 1 al 4
		• [c] articulo-> código del artículo
		• [ch] canal -> código del canal al que pertence el artículo
		ejemplo: http://www.evolutiion.com/ude/damevotos?g=1&c=abcd&ch=xyz */
	@Override
	public int getRssItemVotes() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/*Parámetros entrada: 
		• g [grupo]-> número del 1 al 4
		• [ch] canal-> código del canal 
		ejemplo: http://www.evolutiion.com/ude/damevotoscanal?g=1&ch=xyz */
	
	@Override
	public int getRssFeedVotes() {
		// TODO Auto-generated method stub
		return 0;
	}

	private String enviarDatos(String url, String metodo) {
		String respuesta = "";
		HttpURLConnection httpCon = null;
		URL u = null;
		BufferedReader rd = null;
		String line = null;
		try {
			u = new URL(url);
			httpCon = (HttpURLConnection) u.openConnection();
			httpCon.setRequestMethod(metodo); // GET, POST
			httpCon.setDoInput(true);
			httpCon.setDoOutput(true);
			httpCon.setUseCaches(false);
			// obtener respuesta
			rd = new BufferedReader(new InputStreamReader(
					httpCon.getInputStream()));
			while ((line = rd.readLine()) != null) {
				respuesta = respuesta + line;
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
		return respuesta;
	}

}
