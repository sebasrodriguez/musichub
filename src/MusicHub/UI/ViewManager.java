package MusicHub.UI;

import org.havi.ui.HContainer;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;

public class ViewManager extends HContainer {
	
	private static final long serialVersionUID = 1L;
	private static ViewManager instance = null;
	private HScene scene;
	private BasicContainer visibleView;
	private MainView mainView;
	private ChannelsView channelsView;
	
	private ViewManager(){		
	}
	
	public static ViewManager getInstance(){
		if(instance == null){
			instance = new ViewManager();
		}
		return instance;
	}
	
	public void loadInitialView(){
		HSceneTemplate template = new HSceneTemplate();
		template.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
				new org.havi.ui.HScreenDimension(1, 1), HSceneTemplate.REQUIRED);
		template.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
				new org.havi.ui.HScreenPoint(0, 0), HSceneTemplate.REQUIRED);
		this.scene = HSceneFactory.getInstance().getBestScene(template);		
		
		mainView = new MainView();
		visibleView = mainView;
		scene.add(mainView);
		
		scene.setVisible(true);
	}
	
	public void changeView(String newView){
		scene.remove(visibleView);
		
		if(newView == "ChannelsView"){			
			channelsView = new ChannelsView();
			scene.add(channelsView);
			visibleView = channelsView;
		}
		
		scene.repaint();
	}
}
