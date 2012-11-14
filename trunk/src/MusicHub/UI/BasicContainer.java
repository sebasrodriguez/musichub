package MusicHub.UI;

import java.awt.Image;
import org.havi.ui.HContainer;
import org.havi.ui.HIcon;
import java.awt.Toolkit;

public class BasicContainer extends HContainer {

	private static final long serialVersionUID = 1L;
	protected HIcon icon;
	private Image img;
	private int viewWidth = 722;
	private int viewHeight = 576;

	public BasicContainer() {
		this.setBounds(0, 0, this.viewWidth, this.viewHeight);

		img = Toolkit.getDefaultToolkit().getImage("../assets/MusicHub-Background.png");
		icon = new HIcon(img, 0, 0, this.getWidth(),this.getHeight());

		this.add(icon);
		this.pushToBack(icon);
	}
	
	public BasicContainer(String backgroundImage){
		this.setBounds(0, 0, this.viewWidth, this.viewHeight);

		img = Toolkit.getDefaultToolkit().getImage(backgroundImage);
		icon = new HIcon(img, 0, 0, this.getWidth(),this.getHeight());

		this.add(icon);
		this.pushToBack(icon);
	}

	public int getViewWidth() {
		return this.viewWidth;
	}

	public int getViewHeight() {
		return this.viewHeight;
	}
}
