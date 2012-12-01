package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Controls.RoundRectBox;

public class AboutView extends BasicContainer implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private HText title;
	private HText about;
	private HIcon logo;
	
	
	
	public AboutView(){	
		super();
		
		String aboutText = ServiceLocator.getStorageManager().getAbout();
		Image img = Toolkit.getDefaultToolkit().getImage("../assets/ude.png");
		logo = new HIcon(img);
		logo.setBounds(250,250,160,85);
		logo.setVisible(true);
			
		
		title = new HText("Acerca de\n", new Font("Tiresias", Font.BOLD, 25),
				Color.BLACK, Color.WHITE, new HDefaultTextLayoutManager());
		title.setBounds(105, 20, 470, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		about = new HText(aboutText, new Font("Tiresias", Font.PLAIN, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		about.setBounds(250, 250, 545, 355);
		about.setVisible(true);
		about.setVerticalAlignment(HVisible.VALIGN_TOP);
		about.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		RoundRectBox box = new RoundRectBox(225, 235, 600, 385, Color.DARK_GRAY);
		
		this.add(logo);
		this.add(title);
		this.add(about);
		this.add(box);
	
		this.popToFront(box);
		this.popToFront(title);		
		this.popToFront(about);	
		this.popToFront(logo);
		
		this.addKeyListener(this);
		
	
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub	
		switch(e.getKeyCode()){
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("MainView", null);
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();		
			break;
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
	
	public void paint(Graphics g){
		super.paint(g);
		this.requestFocus();
	}
}
