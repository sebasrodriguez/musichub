package MusicHub.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import MusicHub.Service.Contracts.IFacebookService;
import MusicHub.Util.Conf;

public class FacebookService implements IFacebookService {

	String accessToken;

	public FacebookService() {
		accessToken = Conf.getFacebookAccessToken();
	}

	@Override
	public void postFacebook(String toPost) {
		try {
			String messageToPost = URLEncoder.encode(toPost, "UTF-8");

			URL url = new URL("https://graph.facebook.com/me/feed?access_token="
					+ Conf.getFacebookAccessToken());
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setRequestMethod("POST");

			OutputStreamWriter writer = new OutputStreamWriter(httpUrlConnection.getOutputStream());
			writer.write("message=" + messageToPost);
			writer.close();

			new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
