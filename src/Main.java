import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;

import MusicHub.UI.ItemContainer;
import MusicHub.UI.MainView;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.Util.Conf;


public class Main implements Xlet, KeyListener {
	
	private XletContext contexto;
	private HScene scene;
	private MainView mainView;
	private Image img;
	private UMenuScrollable mainMenu;

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyXlet(boolean arg0) throws XletStateChangeException {
		// TODO Auto-generated method stub
		HSceneFactory.getInstance().dispose(scene);

	}

	@Override
	public void initXlet(XletContext arg0) throws XletStateChangeException {
		// TODO Auto-generated method stub
		
		this.contexto = arg0;
		
		Conf.load();
		HSceneTemplate template = new HSceneTemplate();
		template.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
								   new org.havi.ui.HScreenDimension(1, 1),
		                           HSceneTemplate.REQUIRED);
		template.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
		                           new org.havi.ui.HScreenPoint(0, 0),
		                           HSceneTemplate.REQUIRED);
		this.scene = HSceneFactory.getInstance().getBestScene(template);
		this.scene.addKeyListener(this);
		
		mainView= new MainView("MainView");
		
		scene.add(mainView);
		
		
	}

	@Override
	public void pauseXlet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startXlet() throws XletStateChangeException {
		// TODO Auto-generated method stub			
		System.out.println("Estado START");
		scene.setVisible(true);

		
	}


}
