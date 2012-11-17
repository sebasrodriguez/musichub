package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.FocusManager;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HStaticText;
import org.havi.ui.HText;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.BasicPanel;
import MusicHub.UI.DetailsPanel;
import MusicHub.UI.ItemsPanelB;
import MusicHub.UI.SocialPanel;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.Contracts.IMenuContainer;

public class ContentView extends BasicContainer implements IMenuContainer, KeyListener{
	
	//private BasicPanel itemsPanel;
	private BasicPanel itemsPanelB;
	private BasicPanel detailsPanel;
	private BasicPanel socialPanel;
	
	private BasicPanel focusedPanel;
	private RssFeed canal;
	private UMenuScrollable itemsMenu;
	private List<RssItem> feedItemList;
	
	
	public ContentView(RssFeed canal){		
		this.canal= canal;
		
		HText canalName= new HText(this.canal.getName());
		canalName.setFont(new Font("tiresias",Font.BOLD,13));
		canalName.setForeground(Color.WHITE);
		canalName.setBounds(0, 10, this.getWidth(),50);	
		
		//Toma los items del feed seleccionado
		feedItemList=ServiceLocator.getRssManager().getRssItems(canal);	

		itemsPanelB = new ItemsPanelB(this,this.getItemList(),0, 100, 200, 400);
		detailsPanel= new DetailsPanel(210,100,380,400);
		socialPanel = new SocialPanel(600, 100, 100, 400);
		
		HText votosText= new HText("Votos: " + String.valueOf(canal.getVotes()));
		votosText.setFont(new Font("tiresias",Font.BOLD,13));
		votosText.setForeground(Color.BLACK);
		votosText.setBackground(Color.WHITE);
		votosText.setBounds(480, 65, 200, 30);
		votosText.setHorizontalAlignment(votosText.HALIGN_RIGHT);
		
		
		//Muestro en el panel la info del primer Item del Feed
		((DetailsPanel)detailsPanel).showItem(feedItemList.get(0).getTitle(),feedItemList.get(0).getContent(),feedItemList.get(0).getImageUrl());	
		
		
		this.add(itemsPanelB);	
		this.add(socialPanel);
		this.add(detailsPanel);
		this.add(votosText);
		
		this.add(canalName);
		this.pushToBack(icon);
		this.popToFront(socialPanel);
		
	
	
	}	
	
	

	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//itemsPanelB.requestFocus();
		super.paint(g);
		
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
			//transferFocus();	
			
			System.out.println("Cambio de panel");
			
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




	@Override
	public void stepedOnOption(UOptionItem option) {
	}
	
	private List<UOptionItem> getItemList(){
		
		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		
		for (RssItem feed:feedItemList){	
			
			System.out.println(feed.getImageUrl());
			HIcon ico=null;
			UOptionItem nItem;
			
			//aux=aux.getScaledInstance(20, 20, Image.SCALE_DEFAULT);	
			
				
			if(!feed.getImageUrl().equals("")){

				nItem= new UOptionItem(feed.getImageUrl(), feed.getTitle(), feed ,150,50);		
			}
			else{
				nItem= new UOptionItem(null, feed.getTitle(), feed ,150,50);		
			}
					
			itemList.add(nItem);
			
			
			
		/*	RssItemContainer item= new RssItemContainer(feed.getImageUrl(), feed.getTitle());
			this.add(item);*/
			
		}
		
		return itemList;
	}




	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void selectedOption(UOptionItem selectedOption) {
		// TODO Auto-generated method stub
		
		RssItem it= new RssItem();
		it.setTitle(((RssItem) selectedOption.getValue()).getTitle());
		it.setImageUrl(((RssItem) selectedOption.getValue()).getImageUrl());
		it.setContent(((RssItem) selectedOption.getValue()).getContent());
		
		
		
		//((DetailsPanel)detailsPanel).showItem(it.getTitle(),it.getContent(),it.getImageUrl());
		/*((DetailsPanel)detailsPanel).setDescription(it.getContent());
		((DetailsPanel)detailsPanel).setTitle(it.getTitle());
		((DetailsPanel)detailsPanel).setImg(it.getImageUrl());*/
		
		((DetailsPanel)detailsPanel).updateContent(it.getContent(),it.getTitle(), it.getImageUrl());
		
		
		
	}




	
	

}
