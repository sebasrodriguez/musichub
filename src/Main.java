import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import MusicHub.UI.MainView;
import MusicHub.Util.Conf;

public class Main implements Xlet, KeyListener {

	private XletContext contexto;
	private HScene scene;
	private MainView mainView;	

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
		HSceneFactory.getInstance().dispose(scene);
	}

	@Override
	public void initXlet(XletContext arg0) throws XletStateChangeException {
		this.contexto = arg0;

		Conf.load();
		HSceneTemplate template = new HSceneTemplate();
		template.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
				new org.havi.ui.HScreenDimension(1, 1), HSceneTemplate.REQUIRED);
		template.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
				new org.havi.ui.HScreenPoint(0, 0), HSceneTemplate.REQUIRED);
		this.scene = HSceneFactory.getInstance().getBestScene(template);
		this.scene.addKeyListener(this);

		mainView = new MainView("MainView");

		scene.add(mainView);
	}

	@Override
	public void pauseXlet() {
	}

	@Override
	public void startXlet() throws XletStateChangeException {
		scene.setVisible(true);
	}

}
