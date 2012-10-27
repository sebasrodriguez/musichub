package MusicHub.UI.Controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HState;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

public class FeedDescriptionBox extends HContainer {

	private static final long serialVersionUID = 1L;
	private HText title;
	private HText description;

	public FeedDescriptionBox() {
		this.setBounds(30, 420, 650, 150);
		
		title = new HText("Descripci—n:", new Font("Tiresias", Font.BOLD, 15), Color.WHITE,
				Color.DARK_GRAY, new HDefaultTextLayoutManager());
		title.setBounds(5, 5, 150, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		description = new HText("", new Font("Tiresias", Font.PLAIN, 15), Color.WHITE,
				Color.DARK_GRAY, new HDefaultTextLayoutManager());
		description.setBounds(5, 40, 550, 80);
		description.setVisible(true);
		description.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		this.add(title);
		this.add(description);

		this.popToFront(title);
		this.popToFront(description);
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);
		super.paint(g);
	}

	public void setDescription(String description){		
		this.description.setTextContent(description, HState.ALL_STATES);
	}
}
