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



import MusicHub.UI.Contracts.IMenuContainer;

public class SocialPanel extends BasicPanel implements IMenuContainer {

	private IMenuContainer parent;
	private HGraphicButton comentarBtn;
	private HGraphicButton votarBtn;
	
	public SocialPanel(IMenuContainer parent,int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		
		this.setBounds(x, y, w, h);		
		this.setSize(w, h);
		this.parent=parent;
		
		System.out.println(this.getWidth());
		
		
		HText simpletext= new HText("Social");
		simpletext.setFont(new Font("tiresias",Font.BOLD,13));
		simpletext.setForeground(Color.WHITE);
		simpletext.setBounds(0, 0, this.getWidth(), 20);
		simpletext.setHorizontalAlignment(HText.HALIGN_CENTER);
		
		this.add(simpletext);
		
		
		Image comentarImg = Toolkit.getDefaultToolkit().getImage("../assets/comment.png");
		comentarBtn = new HGraphicButton(comentarImg);
		comentarBtn.setBounds(10, 55, 55, 55);
		comentarBtn.setName("comentar");

		
		this.add(comentarBtn);
		
		comentarBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				switch (e.getKeyCode()) {
				case ControlKeyConstants.OK:
					System.out.println("comentar...");	
						
					//Definir un metodo en contentview que pase el canal, la noticia y el contentView
					//a el ComentView -->
						//SocialPanel.this.parent
						//ViewManager.getInstance().changeView("ComentView", args);
			
					break;
				case ControlKeyConstants.RIGHT:
					SocialPanel.this.keyPressed(e);
				break;
				default:
					votarBtn.requestFocus();
				break;

				}
				
			}
		});
		
		Image votarImg = Toolkit.getDefaultToolkit().getImage("../assets/votar.png");
		votarBtn = new HGraphicButton(votarImg);
		votarBtn.setBounds(10, 105, 55, 55);
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
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				switch (e.getKeyCode()) {
				case ControlKeyConstants.OK:
					System.out.println("votar...");
					// Llamar al servicio de votos
					
					
					break;
				case ControlKeyConstants.RIGHT:
					SocialPanel.this.keyPressed(e);
				break;
				default:
					comentarBtn.requestFocus();
				break;

				}
				
			}
		});
		this.add(votarBtn);
		
		addKeyListener(this);
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		//comentarBtn.requestFocus();
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
			
		
		switch (e.getKeyCode()) {
		case 10:
			System.out.println("social enter...");
			comentarBtn.requestFocus();
			break;
		default:
			System.out.println("Sale del panel....");
			this.parent.unmanagedMenuKey(e.getKeyCode());
			break;
		
	}
	
	
	
	
	

	}
	
	

}
