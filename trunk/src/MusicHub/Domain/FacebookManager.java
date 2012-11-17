package MusicHub.Domain;

import MusicHub.Application.ServiceLocator;
import MusicHub.Domain.Contracts.IFacebookManager;

public class FacebookManager implements IFacebookManager {

	@Override
	public void postFacebook(String toPost) {
		// TODO Auto-generated method stub
		if (toPost == null) {
			ServiceLocator.getFacebookService().postFacebook("");
		} else {
			ServiceLocator.getFacebookService().postFacebook(toPost);
		}
	}

	
	
}
