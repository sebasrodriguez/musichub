package MusicHub.Domain.Contracts;

import java.util.List;

import MusicHub.DataTypes.Tweet;

public interface ITwitterManager {
	
	public List<Tweet> getTweets();
	public void postTweet(String tweetToPost);

}
