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

public class FacebookService implements IFacebookService{

	String accessToken;
	
	public FacebookService(){
		accessToken = Conf.getFacebookAccessToken();
	}
			
	@Override
	public void postFacebook(String toPost) {
		// TODO Auto-generated method stub
		
		try {
			String messageToPost = URLEncoder.encode(toPost, "UTF-8");
			
			//URL url = new URL("https://graph.facebook.com/" + Conf.getFacebookId() + "/feed?access_token=" + Conf.getFacebookAccessToken());
			URL url = new URL("https://graph.facebook.com/me/feed?access_token=" + Conf.getFacebookAccessToken());
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setRequestMethod("POST");
			
			
			OutputStreamWriter writer = new OutputStreamWriter(httpUrlConnection.getOutputStream());		
			writer.write("message=" + messageToPost);
			writer.close();
					
			BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			/*String line;
			while((line = input.readLine()) != null){
				System.out.println(line);
			}*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*
	 * Para extender el token por mas tiempo acceder a la URL (CAMBIANDO LOS DATOS POR LOS NUESTROS)
	 * https://graph.facebook.com/oauth/access_token?client_id=371669686256588&client_secret=aaa7c6f551165355813dea5fe581f85a&grant_type=fb_exchange_token&fb_exchange_token=EXISTING_ACCESS_TOKEN
	 */
}
