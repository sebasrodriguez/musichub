package MusicHub.Application;

import MusicHub.Service.*;
import MusicHub.Service.Contracts.*;
import MusicHub.Domain.*;
import MusicHub.Domain.Contracts.*;

public class ServiceLocator {

	public static IRssService getRssService() {
		return new RssService();
	}

	public static IRssParser getRssParser() {
		return new RssParser();
	}

	public IStorageService getStorageService() {
		return new StorageService();
	}
	
	public IRssManager getRssManager(){
		return new RssManager();
	}
	
	public ITwitterManager getTwitterManager(){
		return new TwitterManager();
	}

}
