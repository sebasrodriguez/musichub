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
		this.setBounds(20, 500, 990, 80);

		title = new HText("Descripcion", new Font("Tiresias", Font.BOLD, 20), Color.WHITE, Color.WHITE,
				new HDefaultTextLayoutManager());
		title.setBounds(5, 5, 150, 20);
		title.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		description = new HText("", new Font("Tiresias", Font.BOLD, 15), Color.DARK_GRAY, Color.WHITE,
				new HDefaultTextLayoutManager());
		description.setBounds(5, 35, 550, 50);
		description.setVisible(true);
		description.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);
		description.setVerticalAlignment(HVisible.VALIGN_TOP);
		description.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		this.add(title);
		this.add(description);

		this.popToFront(title);
		this.popToFront(description);
	}

	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(0, 30, this.getWidth(), this.getHeight() - 30, 15, 15);
		super.paint(g);
	}

	public void setDescription(String description) {
		this.description.setTextContent(this.formatDescription(description), HState.ALL_STATES);
	}

	private String formatDescription(String description) {
		char[] chars = description.toCharArray();
		StringBuilder finalDescription = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			if ((i % 90) == 0) {
				finalDescription.append("\n");
			}
			finalDescription.append(chars[i]);
		}

		return finalDescription.toString();
	}
}
