package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Controls.RoundRectBox;

public class AboutView extends BasicContainer {
	
	private static final long serialVersionUID = 1L;
	private HText title;
	private HText about;
	
	
	public AboutView(){	
		
		String aboutText = ServiceLocator.getStorageManager().getAbout();
		
		title = new HText("Acerca de\n", new Font("Tiresias", Font.BOLD, 20),
				Color.BLACK, Color.WHITE, new HDefaultTextLayoutManager());
		title.setBounds(105, 20, 545, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		about = new HText(aboutText, new Font("Tiresias", Font.PLAIN, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		about.setBounds(105, 55, 545, 170);
		about.setVisible(true);
		about.setVerticalAlignment(HVisible.VALIGN_TOP);
		about.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		RoundRectBox box = new RoundRectBox(80, 40, 600, 200, Color.DARK_GRAY);
		

		this.add(title);
		this.add(about);
		this.add(box);
	

		this.popToFront(box);
		this.popToFront(title);
		this.popToFront(about);	
	
	}
	
	/*public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(0, 0, 375, 100, 15, 15);
		g.setColor(Color.WHITE);
		g.fillRoundRect(1, 1, 373, 98, 15, 15);		
		super.paint(g);
	}*/
}
