package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HStaticText;
import org.havi.ui.HText;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.Contracts.ISelectedOption;

public class ContentView extends BasicContainer implements ISelectedOption, KeyListener{
	
	private BasicPanel itemsPanel;
	private BasicPanel detailsPanel;
	
	private BasicPanel focusedPanel;
	private RssFeed canal;
	private UMenuScrollable itemsMenu;
	List<RssItem> feedList;
	List<UOptionItem> itemList;
	
	public ContentView(RssFeed canal){		
		this.canal= canal;
		
		HText canalName= new HText(this.canal.getName());
		canalName.setFont(new Font("tiresias",Font.BOLD,13));
		canalName.setForeground(Color.WHITE);
		canalName.setBounds(0, 10, 250, 50);	
		
		loadItemsbyFeed();		
		
		this.add(canalName);
		itemsPanel = new ItemsPanel(itemList,15, 15, 150, 100);
		detailsPanel= new DetailsPanel(200,200,200,200);
		
		this.add(itemsPanel);
		this.add(detailsPanel);
		this.popToFront(itemsPanel);
		this.popToFront(canalName);
		//itemsMenu = new UMenuScrollable(itemList, 10, 15, 15);		
	}	
	
	
	private void loadItemsbyFeed(){
		
		feedList = new ArrayList<RssItem>();
		feedList=ServiceLocator.getRssManager().getRssItems(canal);
		itemList = new LinkedList<UOptionItem>();		
			
		for (RssItem feed:feedList){			
			HIcon ico= new HIcon(Toolkit.getDefaultToolkit().getImage(feed.getImageUrl())) ;
			ico.setBounds(0, 0, 20, 20);
			UOptionItem nItem= new UOptionItem(ico, feed.getTitle(),40,15);			
			itemList.add(nItem);
		}
	}
	
	private void showItemDetails(){		
		String itemContent=feedList.get(((ItemsPanel)itemsPanel).getSelectedIndexOption()).getContent();
		String itemImgUrl=feedList.get(((ItemsPanel)itemsPanel).getSelectedIndexOption()).getImageUrl();
		
		((DetailsPanel)detailsPanel).showItem(itemContent,itemImgUrl );
						
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}


	@Override
	public void selectedOption(int selectedIndex) {
		// TODO Auto-generated method stub
				
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//boton azul key code = 406
		//boton amarillo key code = 405
		//boton verde key code = 404
		//boton rojo key code = 403
		//boton arriba key code = 38
		//boton derecha key code = 39
		//boton abajo key code = 40
		//boton izquierda key code = 37
		//boton ok key code = 10
		
		if(e.getKeyCode()==39){
			
			transferFocus();
			
		}
		
		
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
