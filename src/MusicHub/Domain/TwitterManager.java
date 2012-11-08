package MusicHub.Domain;

import java.util.List;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.Tweet;
import MusicHub.Domain.Contracts.ITwitterManager;

public class TwitterManager implements ITwitterManager {

	@Override
	public List<Tweet> getTweets() {
		// TODO Auto-generated method stub
		List<Tweet> tweet = ServiceLocator.getTwitterService().getTweets();
		if (tweet.isEmpty()) {
			return null;
		} else {
			return tweet;
		}
	}

	@Override
	public void postTweet(String tweetToPost) {
		// TODO Auto-generated method stub
		if (tweetToPost == null) {
			ServiceLocator.getTwitterService().postTweet("");
		} else {
			ServiceLocator.getTwitterService().postTweet(tweetToPost);
		}

	}

}
