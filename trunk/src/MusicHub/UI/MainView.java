package MusicHub.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import org.havi.ui.HContainer;
import org.havi.ui.HScene;

public class MainView extends BasicContainer {
	
	private HScene subScene;
	
	public MainView(HScene scene, String title){
		super(scene, title);
		
		scene.add(this);
	}

	/*@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}*/
	
	
}
