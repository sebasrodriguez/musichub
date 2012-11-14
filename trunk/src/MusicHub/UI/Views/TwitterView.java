package MusicHub.UI.Views;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Controls.TwitterTimeline;

public class TwitterView extends BasicContainer {

	private static final long serialVersionUID = 1L;

	public TwitterView() {
		TwitterTimeline timeline = new TwitterTimeline(150, 50, ServiceLocator.getTwitterService()
				.getTweets(), this);

		this.add(timeline);
		this.popToFront(timeline);
	}

	public void timelineUnmanagedKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("MainView", null);
			break;
		}
	}
}