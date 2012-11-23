import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
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
		viewManager = ViewManager.getInstance();
		viewManager.loadInitialView();
	}

}
