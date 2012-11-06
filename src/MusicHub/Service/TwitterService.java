package MusicHub.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import MusicHub.DataTypes.Tweet;
import MusicHub.Service.Contracts.ITwitterService;
import MusicHub.Util.Conf;

public class TwitterService implements ITwitterService {

	String username;
	String password;
	String accessToken;
	String secretToken;

	public TwitterService() {
		// TODO Auto-generated constructor stub
		username = Conf.getTwitterConsumerKey();
		password = Conf.getTwitterCosumerSecret();
		accessToken = Conf.getTwitterAccesToken();
		secretToken = Conf.getTwitterSecretToken();
	}

	@Override
	public List<Tweet> getTweets() {
		List <Tweet> twitsToReturn = null;
		try {		
			
			twitsToReturn = new ArrayList<Tweet>();
			
			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(true).setOAuthConsumerKey(username)
					.setOAuthConsumerSecret(password)
					.setOAuthAccessToken(accessToken)
					.setOAuthAccessTokenSecret(secretToken);

						
			Twitter twitter = new TwitterFactory(confBuilder.build()).getInstance();
			
			List <Status> status = twitter.getHomeTimeline();
			Iterator <Status> itStatus = status.iterator();
			
			while(itStatus.hasNext()){
				Status aux = itStatus.next();
				Tweet twt = new Tweet();
				twt.setUser(aux.getUser().getName());
				twt.setText(aux.getText());
				twitsToReturn.add(twt);
			}
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return twitsToReturn;
		
	}

	// Esto es obligatorio!!!
	@Override
	public void postTweet(String tweetToPost) {
		// TODO Auto-generated method stub
		try {

			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(true).setOAuthConsumerKey(username)
					.setOAuthConsumerSecret(password)
					.setOAuthAccessToken(accessToken)
					.setOAuthAccessTokenSecret(secretToken);

			Twitter twitter = new TwitterFactory(confBuilder.build())
					.getInstance();

			Status status = twitter.updateStatus(tweetToPost);

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
