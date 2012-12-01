package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.UOptionItem;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IMenuContainer;

public class MainView extends BasicContainer implements IMenuContainer, KeyListener {

	private static final long serialVersionUID = 1L;
	private HTextButton htbChannel;
	private HTextButton htbTwitter;
	private HTextButton htbAbout;
	private HTextButton htbExit;
	private int xPosition = 460;
	private int yPosition = 200;
	private int width = 100;
	private int height = 30;
	private String selectedOption;

	public MainView() {
		super("../assets/init_bg.png");

		htbChannel = new HTextButton("Canales");
		htbChannel.setFont(new Font("tiresias",Font.BOLD,22));
		htbChannel.setForeground(Color.WHITE);		
		htbChannel.setBounds(xPosition, yPosition, width, height);
		htbChannel.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		
		
			
		htbTwitter = new HTextButton("Twitter");
		htbTwitter.setFont(new Font("tiresias",Font.BOLD,22));
		htbTwitter.setForeground(Color.WHITE);
		htbTwitter.setBounds(xPosition, yPosition + 40, width, height);
		htbTwitter.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		
		
		htbAbout = new HTextButton("Acerca de");
		htbAbout.setFont(new Font("tiresias",Font.BOLD,22));
		htbAbout.setForeground(Color.WHITE);
		htbAbout.setBounds(xPosition, yPosition + 80, width, height);
		htbAbout.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		
		
		htbExit = new HTextButton("Salir");
		htbExit.setFont(new Font("tiresias",Font.BOLD,22));
		htbExit.setForeground(Color.WHITE);
		htbExit.setBounds(xPosition, yPosition + 120, width, height);
		htbExit.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		
			
		this.add(htbChannel);
		this.add(htbTwitter);
		this.add(htbAbout);
		this.add(htbExit);
		
		
		this.popToFront(htbChannel);
		this.popToFront(htbTwitter);
		this.popToFront(htbAbout);
		this.popToFront(htbExit);
		
		this.selectedOption = "Channel";	
		this.addKeyListener(this);		
	}

	@Override
	public void paint(Graphics g) {		
		switch(this.selectedOption){
		case "Channel":
			htbChannel.setForeground(new Color(244,244,134));
			htbTwitter.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
			break;
		case "Twitter":
			htbTwitter.setForeground(new Color(244,244,134));
			htbChannel.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
			break;
		case "About":
			htbAbout.setForeground(new Color(244,244,134));
			htbTwitter.setForeground(Color.WHITE);
			htbChannel.setForeground(Color.WHITE);
			htbExit.setForeground(Color.WHITE);
			break;
		case "Exit":
			htbExit.setForeground(new Color(244,244,134));
			htbTwitter.setForeground(Color.WHITE);
			htbAbout.setForeground(Color.WHITE);
			htbChannel.setForeground(Color.WHITE);
			break;
		}
		this.requestFocus();		
		super.paint(g);
	}

	@Override
	public void selectedOption(UOptionItem selectedOption) {
		ViewManager.getInstance().changeView((String) selectedOption.getValue(), null);
	}

	@Override
	public void stepedOnOption(UOptionItem option) {
	}

	@Override
	public void unmanagedMenuKey(int keyCode) {
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		// TODO Auto-generated method stub		
		switch(keyEvent.getKeyCode()){
		case ControlKeyConstants.UP:
			switch(this.selectedOption){
			case "Channel":
				this.selectedOption = "Channel";				
				break;
			case "Twitter":
				this.selectedOption = "Channel";					
				break;
			case "About":
				this.selectedOption = "Twitter";	
				break;
			case "Exit":
				this.selectedOption = "About";
				break;			
			}			
			break;
		case ControlKeyConstants.DOWN:
			switch(this.selectedOption){
			case "Channel":
				this.selectedOption = "Twitter";
				break;
			case "Twitter":
				this.selectedOption = "About";
				break;
			case "About":
				this.selectedOption = "Exit";
				break;
			case "Exit":
				this.selectedOption = "Exit";
				break;			
			}			
			break;	
		case ControlKeyConstants.OK:
			switch(this.selectedOption){
			case "Channel":
				ViewManager.getInstance().changeView("ChannelsView", null);
				break;
			case "Twitter":
				ViewManager.getInstance().changeView("TwitterView", null);
				break;
			case "About":
				ViewManager.getInstance().changeView("AboutView", null);				
				break;
			case "Exit":
				ViewManager.getInstance().exitApplication();
				break;			
			}		
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();		
			break;
		}		
		this.repaint();		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
