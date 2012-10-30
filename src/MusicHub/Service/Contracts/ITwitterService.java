package MusicHub.Service.Contracts;

import java.util.List;
import MusicHub.DataTypes.Tweet;

public interface ITwitterService {

	List<Tweet> getTweets();
	
}
