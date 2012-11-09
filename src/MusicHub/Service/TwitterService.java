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
		username = Conf.getTwitterConsumerKey();
		password = Conf.getTwitterCosumerSecret();
		accessToken = Conf.getTwitterAccesToken();
		secretToken = Conf.getTwitterSecretToken();
	}

	@Override
	public List<Tweet> getTweets() {
		List<Tweet> tweetsToReturn = null;
		try {

			tweetsToReturn = new ArrayList<Tweet>();

			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(true).setOAuthConsumerKey(username)
					.setOAuthConsumerSecret(password).setOAuthAccessToken(accessToken)
					.setOAuthAccessTokenSecret(secretToken);

			Twitter twitter = new TwitterFactory(confBuilder.build()).getInstance();

			List<Status> status = twitter.getHomeTimeline();
			Iterator<Status> itStatus = status.iterator();

			while (itStatus.hasNext()) {
				Status aux = itStatus.next();
				Tweet twt = new Tweet();
				twt.setUserImageUrl(aux.getUser().getProfileImageURL().toString());
				twt.setUser(aux.getUser().getName());
				twt.setText(aux.getText());
				tweetsToReturn.add(twt);
			}
		}
		catch (TwitterException e) {
			e.printStackTrace();
		}

		return tweetsToReturn;

	}

	@Override
	public void postTweet(String tweetToPost) {
		try {

			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(true).setOAuthConsumerKey(username)
					.setOAuthConsumerSecret(password).setOAuthAccessToken(accessToken)
					.setOAuthAccessTokenSecret(secretToken);

			Twitter twitter = new TwitterFactory(confBuilder.build()).getInstance();

			twitter.updateStatus(tweetToPost);
		}
		catch (TwitterException e) {
			e.printStackTrace();
		}

	}

}
