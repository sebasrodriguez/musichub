import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import MusicHub.DataTypes.Tweet;
import MusicHub.Service.FacebookService;
import MusicHub.Service.TwitterService;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Controls.VideoResizer;
import MusicHub.Util.Conf;

public class Main implements Xlet, KeyListener {

	private XletContext context;
	private ViewManager viewManager;		

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void destroyXlet(boolean arg0) throws XletStateChangeException {		
	}

	@Override
	public void initXlet(XletContext arg0) throws XletStateChangeException {
		this.context = arg0;
		VideoResizer.getInstance(this.context);		
		Conf.load();
	}

	@Override
	public void pauseXlet() {
	}

	@Override
	public void startXlet() throws XletStateChangeException {
		/*viewManager = ViewManager.getInstance();
		viewManager.loadInitialView();*/
		/*TwitterService tws = new TwitterService();
		Random i = new Random();		
		tws.postTweet("Prueba desde MusicHub " + i.nextInt());
		List<Tweet> twts = tws.getTweets();
		if(twts == null){
			System.out.println("No hay twitts");
		}else{
			Iterator<Tweet> itAux = twts.iterator();
			while(itAux.hasNext()){
				Tweet twAux = itAux.next();
				System.out.println(twAux.getUser() + " : " + twAux.getText());
			}
		}*/
		FacebookService fbs = new FacebookService();
		Random i = new Random();
		fbs.postFacebook("Post desde java " + i.nextInt());
		
		
	}

}
