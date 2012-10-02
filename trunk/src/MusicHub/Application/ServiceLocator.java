package MusicHub.Application;

import MusicHub.Service.*;
import MusicHub.Service.Contracts.*;
import MusicHub.Domain.*;
import MusicHub.Domain.Contracts.*;

public class ServiceLocator {

	public IRssService getRssService() {
		return (IRssService)new RssManager();
	}

	public IRssParser getRssParser() {
		return (IRssParser)new RssParser();
	}

	public IStorageService getStorageService() {
		return (IStorageService)new StorageService();
	}
	
	public IRssManager getRssManager(){
		return (IRssManager)new RssManager();
	}
	
	public ITwitterManager getTwitterManager(){
		return (ITwitterManager)new TwitterManager();
	}

}
