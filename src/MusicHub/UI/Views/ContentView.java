package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.havi.ui.HIcon;
import org.havi.ui.HText;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.BasicPanel;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.DetailsPanel;
import MusicHub.UI.ItemsPanelB;
import MusicHub.UI.SocialPanel;
import MusicHub.UI.UMenuScrollable;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IMenuContainer;

public class ContentView extends BasicContainer implements IMenuContainer, KeyListener{
	
	private static final long serialVersionUID = 1L;
	//private BasicPanel itemsPanel;
	private BasicPanel itemsPanelB;
	private BasicPanel detailsPanel;
	private BasicPanel socialPanel;
	
	private BasicPanel selectedPanel;
	private RssFeed canal;
	private List<RssItem> feedItemList;
	private List<BasicPanel> panelsList;
	private RssItem itemSelected;
	private HText votosText;
	
	
	public ContentView(RssFeed canal){		
		this.canal= canal;
		
		HText canalName= new HText(this.canal.getName());
		canalName.setFont(new Font("tiresias",Font.BOLD,13));
		canalName.setForeground(Color.WHITE);
		canalName.setBounds(0, 10, this.getWidth(),50);	
		
		panelsList = new ArrayList<BasicPanel>();
		
		//Toma los items del feed seleccionado
		feedItemList=ServiceLocator.getRssManager().getRssItems(canal);	
		setItemSelected(feedItemList.get(0));

		itemsPanelB = new ItemsPanelB(this,this.getItemList(),0, 100, 200, 400);
		panelsList.add(itemsPanelB);
		detailsPanel= new DetailsPanel(this,210,100,380,400);
		panelsList.add(detailsPanel);
		socialPanel = new SocialPanel(this,getItemSelected(),600, 100, 100, 400);
		panelsList.add(socialPanel);
		
		itemsPanelB.setName("items panel");
		detailsPanel.setName("details panel");
		socialPanel.setName("Social panel");
		

		
		votosText= new HText("Votos: " + String.valueOf(canal.getVotes()));
		votosText.setFont(new Font("tiresias",Font.BOLD,13));
		votosText.setForeground(Color.BLACK);
		votosText.setBackground(Color.WHITE);
		votosText.setBounds(480, 65, 200, 30);
		votosText.setHorizontalAlignment(votosText.HALIGN_RIGHT);
		
		
		setItemSelected(feedItemList.get(0));
		//Muestro en el panel la info del primer Item del Feed
		((DetailsPanel)detailsPanel).showItem(feedItemList.get(0).getTitle(),feedItemList.get(0).getContent(),feedItemList.get(0).getImageUrl());	
		
		
		this.add(itemsPanelB);	
		this.add(socialPanel);
		this.add(detailsPanel);
		this.add(votosText);
		
		this.add(canalName);
		this.pushToBack(icon);
		this.popToFront(socialPanel);
		
		selectedPanel = panelsList.get(0);
	
	}	

	
	public BasicPanel getSelectedPanel() {
		return selectedPanel;
	}

	public void setSelectedPanel(BasicPanel selectedPanel) {
		this.selectedPanel = selectedPanel;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		super.paint(g);
		//itemsPanelB.requestFocus();
		
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
			UOptionItem nItem;
			
				
			if(!feed.getImageUrl().equals("")){

				nItem= new UOptionItem(feed.getImageUrl(), feed.getTitle(), feed ,150,50);		
			}
			else{
				nItem= new UOptionItem(null, feed.getTitle(), feed ,150,50);		
			}
					
			itemList.add(nItem);
			

			
		}
		
		return itemList;
	}




	public RssFeed getCanal() {
		return canal;
	}

	public void setCanal(RssFeed canal) {
		this.canal = canal;
	}

	public RssItem getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(RssItem itemSelected) {
		this.itemSelected = itemSelected;
	}


	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		int sel;
		switch(keyCode){
		
			case ControlKeyConstants.RED:
				
				ViewManager.getInstance().changeView("ChannelsView", null);				
				break;
			case ControlKeyConstants.RIGHT:
				
				sel=panelsList.indexOf(selectedPanel);
				sel++;
				
				if(sel>=panelsList.size()){
					sel=0;
				}	
				
				
				panelsList.get(sel).requestFocus();
				selectedPanel=panelsList.get(sel);
				System.out.println("panel :" + selectedPanel.getName() );
				selectedPanel.repaint();				
				break;
			case ControlKeyConstants.LEFT:
				
				sel=panelsList.indexOf(selectedPanel);
				sel--;
				
				if(sel<0){
					sel=panelsList.size()-1;
				}
				
				panelsList.get(sel).requestFocus();
				selectedPanel=panelsList.get(sel);
				System.out.println("panel :" + selectedPanel.getName() );
				selectedPanel.repaint();
				
				break;
				
			case ControlKeyConstants.EXIT:					
				ViewManager.getInstance().exitApplication();
				break;
				
			default:
				
				break;
		}
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		// TODO Auto-generated method stub
		
		setItemSelected((RssItem) selectedOption.getValue());		
		((DetailsPanel)detailsPanel).updateContent(getItemSelected().getContent(),getItemSelected().getTitle(), getItemSelected().getImageUrl());	
		
		
	}



	public void comentar() {
		System.out.println("Comenta Content");
		Object args[];
		args = new Object[2];
		args[0] = this;
		args[1] = this.getItemSelected();
		
		

		

		ViewManager.getInstance().changeView("CommentsView", args);
	}
	
	public void votar(){		
		ServiceLocator.getVoteManager().voteRssItem(canal, itemSelected);
		int votos=canal.getVotes();
		votos++;
		
		canal.setVotes(votos);
		votosText.setTextContent("Votos: " + String.valueOf(votos), HText.ALL_STATES);
		votosText.repaint();
		
		
	}
	public void facebook(){
		ServiceLocator.getFacebookManager().postFacebook(getItemSelected().getTitle());
	}
	
	public void sendTweet(){
		ServiceLocator.getTwitterManager().postTweet(getItemSelected().getTitle());
	}
	
	

}
