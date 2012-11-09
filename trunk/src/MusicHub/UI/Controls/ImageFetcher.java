package MusicHub.UI.Controls;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.havi.ui.HContainer;
import org.havi.ui.HIcon;

public class ImageFetcher implements Runnable {
	
	private String imageUrl;
	private HIcon icon;
	private HContainer container;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public ImageFetcher(String imageUrl, HIcon icon, HContainer container, int x, int y, int width, int height){
		this.imageUrl = imageUrl;
		this.icon = icon;
		this.container = container;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void run() {
		try {
			URL url = new URL(this.imageUrl);
			Image image = ImageIO.read(url);
			
			this.icon = new HIcon(image);
			this.icon.setBounds(this.x, this.y, this.width, this.height);
			this.icon.setVisible(true);
			
			container.add(this.icon);
			container.popToFront(this.icon);
			
			container.repaint();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
