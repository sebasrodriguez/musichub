package MusicHub.UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HScene;

public class MainView extends BasicContainer {
	
	private HScene subScene;
	private UMenuScrollable mainMenu;
	private Image img;
	private HIcon ico;
	
	public MainView(HScene scene, String title){
		super(scene, title);		
		
		img=Toolkit.getDefaultToolkit().getImage("../imgs/arrow_img3.jpg");		
		//ico= new HIcon(img,0,0,800,500);		
		
		UOptionItem item= new UOptionItem(new HIcon(img,0,0,800,500), "Opcion 1");	
		item.setName("item 0");
		UOptionItem item2= new UOptionItem(new HIcon(img,0,0,800,500), "Opcion 2");
		item2.setName("item 1");
		UOptionItem item3= new UOptionItem(new HIcon(img,0,0,800,500), "Opcion 3");
		item3.setName("item 2");
		UOptionItem item4= new UOptionItem(new HIcon(img,0,0,800,500), "Opcion 4");
		item4.setName("item 3");
		
		
		List itemList=new LinkedList();
		itemList.add(item);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);	
		
		
		mainMenu= new UMenuScrollable(itemList);
		mainMenu.setBounds(150, 60, 500, 500);
		
		
		scene.add(mainMenu);
		scene.add(this);
		mainMenu.requestFocus();

	}

	/*@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}*/
	
	
}
