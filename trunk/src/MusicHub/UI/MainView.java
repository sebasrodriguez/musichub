package MusicHub.UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HScene;

public class MainView extends BasicContainer implements KeyListener, FocusListener {
	
	private HScene subScene;
	private UMenuScrollable mainMenu;
	private Image img;
	private HIcon ico;
	
	public MainView(String title){
		super(title);
		
		
		
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
		
		this.add(mainMenu);	
		//scene.add(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mainView focus");
		//mainMenu.requestFocus();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("main lost");
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		mainMenu.requestFocus();
		super.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
