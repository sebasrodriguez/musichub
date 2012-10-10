import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HComponent;
import org.havi.ui.HContainer;

import util.Conf;


public class UMenuScrollable extends HContainer implements KeyListener, FocusListener {

	private List listItems;
	private int itemHPos=0;
	private HContainer wrapper;
	private HContainer menuContainer;
	private int selectedItemIndex=0;
	private UOptionItem itemSelected;
	// TODO Agregar el scroll como atributo
	
	public UMenuScrollable(){
		
		listItems= new LinkedList();
		menuContainer= new HContainer(0,0,this.getWidth(), this.getHeight());
		menuContainer.setSize(250,204);
		wrapper= new HContainer();
		addKeyListener(this);
		addFocusListener(this);
	}
	
	
	private int calcHeight(){
		
		//TODO Conf.getItemsHeight
		return Conf.getItemHeight() * getNumItems();
	}
	
	public void addOption (UOptionItem opt){	
		this.listItems.add(opt);
	}	
	
	public int getNumItems(){
		return this.listItems.size();
	}
	
	public void showItems(){
		
		Iterator it=listItems.iterator();
		int index=0;
	
		while(it.hasNext()){						
			
			UOptionItem aux=(UOptionItem)it.next();
			itemHPos+=aux.getHeight();
			aux.setLocation(0,itemHPos);
			
			System.out.println(String.valueOf(aux.getName()));
			this.menuContainer.add(aux,index);
			
			index++;			
		}
		
		//Indico que la primera opcion es la seleccionada
		((UOptionItem)this.listItems.get(0)).setSelected(true);
		itemSelected=(UOptionItem)this.listItems.get(0); // no se si lo preciso
				
		menuContainer.setVisible(true);		
		this.add(menuContainer);	
		

	}

	
	//KeyListeners
	public void keyPressed(KeyEvent e) {
		// TODO logica para bajar o subir en el menu scroleable
		
		//flecha abajo
		if  (e.getKeyCode() == 40){
			
			System.out.println("item d");
			
			UOptionItem next=((UOptionItem)menuContainer.getComponent(selectedItemIndex++));
			next.setSelected(true);
			
			System.out.println(next.getName());
			
			//this.itemSelected.setSelected(false);		
			
			//((UOptionItem)this.listItems.get(selectedItemIndex)).setSelected(false);
			//selectedItemIndex++;
			//((UOptionItem)this.listItems.get(selectedItemIndex)).transferFocus();
			//repaint();
		
		 }  
		
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub		
		/*this.transferFocus();
		this.transferFocusDownCycle();*/
		System.out.println("menu focus");		
		//this.menuContainer.getComponent(0).requestFocus();
		
	}


	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
