package MusicHub.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;

import org.havi.ui.HGraphicButton;
import org.havi.ui.HText;
import org.havi.ui.event.HRcEvent;



import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.DataTypes.RssItem;
import MusicHub.Service.TwitterService;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.UI.Views.ContentView;

public class SocialPanel extends BasicPanel implements IMenuContainer {

	private IMenuContainer parent;
	private HGraphicButton comentarBtn;
	private HGraphicButton votarBtn;
	private HGraphicButton tweetBtn;
	private HGraphicButton fcbkBtn;
	private HGraphicButton [] accButtons;
	private int selected=0;
	private RssItem itemSelected;
	
	
	public SocialPanel(IMenuContainer parent,RssItem itemSelected,int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		
		this.setBounds(x, y, w, h);		
		this.setSize(w, h);
		this.parent=parent;
		this.itemSelected=itemSelected;
		
		
		
		
		accButtons = new HGraphicButton[4];
		
		System.out.println(this.getWidth());
		
		
		HText simpletext= new HText("Social");
		simpletext.setFont(new Font("tiresias",Font.BOLD,13));
		simpletext.setForeground(Color.WHITE);
		simpletext.setBounds(0, 0, this.getWidth(), 20);
		simpletext.setHorizontalAlignment(HText.HALIGN_CENTER);
		
		this.add(simpletext);
		
		
		Image comentarImg = Toolkit.getDefaultToolkit().getImage("../assets/comment_32.png");
		comentarBtn = new HGraphicButton(comentarImg);
		comentarBtn.setBounds(10, 55, 83, 55);
		comentarBtn.setName("comentar");
		comentarBtn.addKeyListener(new KeyListener() {	
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				switch(arg0.getKeyCode()){
					case ControlKeyConstants.OK:
						//Definir un metodo en contentview que pase el canal, la noticia y el contentView
						//a el ComentView -->
							//SocialPanel.this.parent
							//ViewManager.getInstance().changeView("ComentView", args);
						SocialPanel.this.comentar();
						break;
					default:
						//Gestiona SocialPanel						
						SocialPanel.this.keyPressed(arg0);
						break;
				}
				
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		accButtons[0]=comentarBtn;		
		this.add(comentarBtn);
		
		
		
		Image votarImg = Toolkit.getDefaultToolkit().getImage("../assets/votar_32.png");
		votarBtn = new HGraphicButton(votarImg);
		votarBtn.setBounds(10, 105, 83, 55);
		votarBtn.setName("votar");
		votarBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				switch(arg0.getKeyCode()){
				case ControlKeyConstants.OK:
					//Accion de votar
					SocialPanel.this.votar();
					
					break;
				default:
					
					//Gestiona SocialPanel						
					SocialPanel.this.keyPressed(arg0);
					break;
				}
				
			}
		});
		
		
		accButtons[1]=votarBtn;
		
		this.add(votarBtn);
		
		
		Image tweetImg = Toolkit.getDefaultToolkit().getImage("../assets/tweet_32.png");
		tweetBtn = new HGraphicButton(tweetImg);
		tweetBtn.setBounds(10, 160, 83, 55);
		tweetBtn.setName("tweeter");
		tweetBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				switch(arg0.getKeyCode()){
				case ControlKeyConstants.OK:
					//Accion de tweetear
					ServiceLocator.getTwitterManager().postTweet(getItemSelected().getTitle());
				
					break;
				default:
					
					//Gestiona SocialPanel						
					SocialPanel.this.keyPressed(arg0);
					break;
				}
				
			}
		});
		
		accButtons[2]=tweetBtn;		
		this.add(tweetBtn);
		
		
		Image fcbkImg = Toolkit.getDefaultToolkit().getImage("../assets/me_gusta_32.png");
		fcbkBtn = new HGraphicButton(fcbkImg);
		fcbkBtn.setBounds(10, 220, 83, 55);
		fcbkBtn.setName("facebook");
		fcbkBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				switch(arg0.getKeyCode()){
				case ControlKeyConstants.OK:
					
					break;
				default:
					
					//Gestiona SocialPanel						
					SocialPanel.this.keyPressed(arg0);
					break;
				}
				
			}
		});
		
		accButtons[3]=fcbkBtn;		
		this.add(fcbkBtn);
		
		
		addKeyListener(this);
		
	}
	
	
	public RssItem getItemSelected() {
		return itemSelected;
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		//comentarBtn.requestFocus();
		
		g.setColor(Color.RED);
		g.fillRect(30, 0, 10, 2);
		g.setColor(Color.WHITE);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		
		
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


	@Override
	public void unmanagedMenuKey(int keyCode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getKeyCode()){
		
		case ControlKeyConstants.OK:
			//Seteo el foco en el 1ero
			accButtons[selected].requestFocus();
			break;
		case ControlKeyConstants.DOWN:
			//Aumentar el selected
			System.out.println("aaaabajo");
			selected++;
			
			if(selected==accButtons.length) selected=0;			
			accButtons[selected].requestFocus();
			
			break;
		case ControlKeyConstants.UP:
			System.out.println("arriba");
			selected--;
			
			if(selected<0) selected=accButtons.length-1;			
			accButtons[selected].requestFocus();
			break;
		default:
			this.parent.unmanagedMenuKey(e.getKeyCode());
			break;
			
			
		
		}
		
	}
	
	public void comentar(){		
		((ContentView)this.parent).comentar();
	}
	
	public void votar(){		
		((ContentView)this.parent).votar();
	}
	

	

	
	

}
