package MusicHub.UI;

import java.awt.Color;
import java.awt.Image;
import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import java.awt.Toolkit;

public class BasicContainer extends HContainer {

	private static final long serialVersionUID = 1L;	
	private HIcon icon;
	private Image img;

	public BasicContainer() {
		this.setBounds(0, 0, 722, 576);

		img = Toolkit.getDefaultToolkit().getImage("../assets/MusicHub-Background.png");
		icon = new HIcon(img, 0, 0, this.getWidth(),this.getHeight());

		this.add(icon);
		this.pushToBack(icon);
	}
}
