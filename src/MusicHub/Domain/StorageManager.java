package MusicHub.Domain;

import MusicHub.Application.ServiceLocator;
import MusicHub.Domain.Contracts.IStorageManager;

public class StorageManager implements IStorageManager {

	@Override
	public String getAbout() {
		// TODO Auto-generated method stub
		String about = ServiceLocator.getStorageService().getAbout();		
		
		return about;
	}

}
