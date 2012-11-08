package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import MusicHub.UI.Contracts.IMenuContainer;

public class ItemsPanelB extends BasicPanel implements IMenuContainer {
	
	private UMenuScrollable itemsMenu;	
	
	public ItemsPanelB(List<UOptionItem> itemList, int x, int y, int w, int h){
		super(x, y, w, h);
		
		this.setBounds(x, y, w, h);		
		this.setSize(w, h);
		
		System.out.println(this.getWidth());
		
		itemsMenu= new UMenuScrollable(itemList, 10, this, this.getX()+10,this.getY()+10);
		itemsMenu.setSize(this.getWidth(), this.getHeight());
		itemsMenu.setFontStyle(Font.PLAIN, 13);
		this.add(itemsMenu);
		this.popToFront(itemsMenu);
		
	}
	
	public ItemsPanelB(int x, int y, int w, int h){
		super(x, y, w, h);

		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
		// TODO Auto-generated method stub
		
	}
	
	public int getSelectedOption(){
		return itemsMenu.getSelectedItemIndex();
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
