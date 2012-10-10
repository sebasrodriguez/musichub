package MusicHub.UI;
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

import MusicHub.Util.Conf;




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
		
		setSubContainers();
		
	}
	
	//Constructor que acepta una lista de UOptionItem
	public UMenuScrollable(List items){		
		this.listItems=items;
		setSubContainers();
		showItems();
	}
	
	
	private int calcHeight(){
		
		//TODO Conf.getItemsHeight
		return Conf.getItemHeight() * getNumItems();
	}
	
	private void setSubContainers(){
		menuContainer= new HContainer(0,0,this.getWidth(), this.getHeight());
		menuContainer.setSize(250,204);
		wrapper= new HContainer();
		addKeyListener(this);
		addFocusListener(this);
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
		itemSelected=(UOptionItem)this.listItems.get(0); 
				
		menuContainer.setVisible(true);		
		this.add(menuContainer);	
		

	}

	
	//KeyListeners
	public void keyPressed(KeyEvent e) {
		// TODO logica para bajar o subir en el menu scroleable
		
		//flecha abajo
		if  (e.getKeyCode() == 40){
			
			itemSelected.setSelected(false);
			UOptionItem next=null;
			selectedItemIndex++;
			
			if(selectedItemIndex<menuContainer.getComponentCount()){				
				next=((UOptionItem)menuContainer.getComponent(selectedItemIndex));
			}
			else{
				selectedItemIndex=0;
				next=((UOptionItem)menuContainer.getComponent(selectedItemIndex));
			}
			
			next.setSelected(true);
			itemSelected=next;		//Guardo una referencia a la opcion seleccionada		
			
			System.out.println(next.getName());
			
		
		 }  
		
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void focusGained(FocusEvent arg0) {
		System.out.println("menu focus");		
		
	}


	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
