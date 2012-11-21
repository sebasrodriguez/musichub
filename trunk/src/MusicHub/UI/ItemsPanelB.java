package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import MusicHub.DataTypes.RssItem;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Views.ContentView;

public class ItemsPanelB extends BasicPanel implements IMenuContainer {
	
	private UMenuScrollable itemsMenu;	
	private IMenuContainer parent;
	
	
	public ItemsPanelB(IMenuContainer container,List<UOptionItem> itemList, int x, int y, int w, int h){
		super(x, y, w, h);
		
		this.setBounds(x, y, w, h);		
		this.setSize(w, h);

		this.parent=container;
		itemsMenu= new UMenuScrollable(itemList, 10, this, 10,10);
		itemsMenu.setSize(this.getWidth(), this.getHeight());
		itemsMenu.setFontStyle(Font.PLAIN, 13);
		this.add(itemsMenu);
		this.popToFront(itemsMenu);
		//addKeyListener(this);
		
	}
	
	public ItemsPanelB(int x, int y, int w, int h){
		super(x, y, w, h);

		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		itemsMenu.requestFocus();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		// TODO Auto-generated method stub		
		this.parent.selectedOption(selectedOption);
		
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
		// TODO Auto-generated method stub

		
		
	}
	
	public String getItemDescription(String description){
		return description;
	}
	
	public int getSelectedOption(){
		return itemsMenu.getSelectedItemIndex();
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		System.out.println(keyCode);
		this.parent.unmanagedMenuKey(keyCode);
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		itemsMenu.requestFocus();
		super.focusGained(e);

	}

	
	

}
