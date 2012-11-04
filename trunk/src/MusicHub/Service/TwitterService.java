package MusicHub.Service;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import MusicHub.DataTypes.Tweet;
import MusicHub.Service.Contracts.ITwitterService;

public class TwitterService implements ITwitterService {

	@Override
	public List<Tweet> getTweets() {
		return null;

	}

	// Esto es obligatorio!!!
	@Override
	public void postTweet(String tweetToPost) {
		// TODO Auto-generated method stub
		try {
			String username = "8mpaQnevmxijm1jUFghTQ";
			String password = "xNfEbwhGH1Z8kA3jdNO49oOzkTdpVm1VBtXn4ZeVW64";
			String accessToken = "921669576-M0DYgL2hBJIQwJCLwDi4D2yDq4hB67t64jTrKZlA";
			String secretToken = "Jjacp0Xlkvwb02kOarGuDTt4zVhoc5UjVjkXlHO1U";
			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(true).setOAuthConsumerKey(username)
					.setOAuthConsumerSecret(password)
					.setOAuthAccessToken(accessToken)
					.setOAuthAccessTokenSecret(secretToken);

						
			Twitter twitter = new TwitterFactory(confBuilder.build()).getInstance();
			
			Status status = twitter.updateStatus(tweetToPost);			

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
