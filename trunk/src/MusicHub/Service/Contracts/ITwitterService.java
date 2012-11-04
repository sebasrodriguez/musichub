package MusicHub.Service.Contracts;

import java.util.List;
import MusicHub.DataTypes.Tweet;
import twitter4j.*;

public interface ITwitterService {

	List<Tweet> getTweets();
	
	void postTweet(String tweetToPost);
	
}
