import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.havi.ui.HContainer;
import org.havi.ui.HScene;


public class UScroll extends HContainer {
	
	private HScene scene;
	private int menuHeight;
	private int contHeight;
	private final int SCROLL_WIDTH=5;
	
	public UScroll(HScene scene, int menuHeight, int contHeight){
		this.scene=scene;
		this.menuHeight=menuHeight;
		this.contHeight=contHeight;		
		this.setBounds(80, 200, SCROLL_WIDTH, contHeight);
		scene.add(this);
	}
	
	private int getMenuHeight(){
		return this.menuHeight;
	}
	
	private int getContHeight(){
		return this.contHeight;
	}
	
	private int getFrontBarHeight(){
		
		int per=(getContHeight() * 100)/getMenuHeight();
		int barHeight=(per*getContHeight())/100;
		
		return barHeight;
	}
	
	

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		//Back bar
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.SCROLL_WIDTH, this.getContHeight());
		
		//Front bar
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.SCROLL_WIDTH, getFrontBarHeight());
		
		
		super.paint(g);
	}
	
	

}
