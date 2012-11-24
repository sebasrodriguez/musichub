package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.Controls.RoundRectBox;

public class AboutView extends BasicContainer {
	
	private static final long serialVersionUID = 1L;
	private HText title;
	private HText about;
	private HIcon logo;
	
	
	
	public AboutView(){	
		
		String aboutText = ServiceLocator.getStorageManager().getAbout();
		Image img = Toolkit.getDefaultToolkit().getImage("../assets/ude.png");
		logo = new HIcon(img);
		logo.setBounds(105,75,160,85);
		logo.setVisible(true);
			
		
		title = new HText("Acerca de\n", new Font("Tiresias", Font.BOLD, 25),
				Color.BLACK, Color.WHITE, new HDefaultTextLayoutManager());
		title.setBounds(105, 20, 470, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		about = new HText(aboutText, new Font("Tiresias", Font.PLAIN, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		about.setBounds(105, 75, 545, 355);
		about.setVisible(true);
		about.setVerticalAlignment(HVisible.VALIGN_TOP);
		about.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		RoundRectBox box = new RoundRectBox(80, 60, 600, 385, Color.DARK_GRAY);
		
		this.add(logo);
		this.add(title);
		this.add(about);
		this.add(box);
	
		this.popToFront(box);
		this.popToFront(title);		
		this.popToFront(about);	
		this.popToFront(logo);
		
	
	}
	
	/*public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(0, 0, 375, 100, 15, 15);
		g.setColor(Color.WHITE);
		g.fillRoundRect(1, 1, 373, 98, 15, 15);		
		super.paint(g);
	}*/
}
