package MusicHub.UI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import org.havi.ui.HTextButton;
import MusicHub.Util.Conf;

public class UOptionItem extends HContainer {

	private static final long serialVersionUID = 1L;

	private HTextButton button;
	private boolean selected;
	private boolean isShown;
	private String title;
	private Object value;
	private HIcon icon;
	private String imgUrl;

	public UOptionItem(String imageUrl, String title, Object value, int width, int height) {
		super();

		this.setBounds(10, 10, width, height);
		this.selected = false;
		this.setValue(value);
		//this.setBackground(Color.DARK_GRAY);		
		this.title = title;
		button = new HTextButton(title);		
		button.setBounds(10, 0, this.getWidth(), Conf.getItemHeight());
		button.setForeground(Color.white);
		button.setFont(new Font(Conf.getFontName(), Font.BOLD, Conf.getFontSize()));
		button.setHorizontalAlignment(HTextButton.HALIGN_LEFT);

		
		if (imageUrl != null) {
			
			this.imgUrl=imageUrl;
			Thread th= new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					try {
						
						Image image;
						image= Toolkit.getDefaultToolkit().getImage(new URL(UOptionItem.this.imgUrl));
						image= image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);	
						
						icon = new HIcon(image);
						icon.setBounds(0, 0, 50, 50);
						icon.setBackgroundMode(HIcon.BACKGROUND_FILL);
						icon.setFocusable(false);
						UOptionItem.this.add(icon);
						UOptionItem.this.popToFront(icon);
						button.setBounds(50, 0, UOptionItem.this.getWidth(), UOptionItem.this.getHeight());
						
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//icon=null;
					}
					
				}
			});th.start();
			
		}
		
		
		this.add(button);

		
	}

	public void paint(Graphics g) {
		//g.setColor(Color.DARK_GRAY);		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;

		if (selected) {
			this.setBackground(Color.GRAY);
		}
		else {
			this.setBackground(Color.BLACK);
		}
		repaint();
	}

	public boolean isShown() {
		return this.isShown;
	}

	public void isShown(boolean value) {
		this.isShown = value;
	}

	public String getTitle() {
		return title;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public void setFontStyle(int style, int size){		
		button.setFont(new Font(Conf.getFontName(), style, size));
	}
}
