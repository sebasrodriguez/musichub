package MusicHub.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.Contracts.ISelectedOption;

//Despliega los items de un canal
public class ItemsPanel extends BasicPanel implements KeyListener, ISelectedOption{

	private UMenuScrollable itemsMenu;
	private int selectedIndex=0;
	
	
	public ItemsPanel(List<UOptionItem> itemsList, int x, int y, int w, int h){
		super(x,y,w,h);
		
		//itemsMenu= new UMenuScrollable(items, itemsToShow, x, y);
		this.setBounds(x,y,w,h);		
		itemsMenu = new UMenuScrollable(itemsList, 10, 15, 15);
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.paint(g);
	}


	@Override
	public void selectedOption(int selectedIndex) {
		// TODO Auto-generated method stub
		this.selectedIndex=selectedIndex;
		
	}
	
	public int getSelectedIndexOption(){
		return this.selectedIndex;
		
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
