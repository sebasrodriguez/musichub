package MusicHub.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import org.havi.ui.HIcon;

import MusicHub.UI.Contracts.ISelectedOption;

public class MainView extends BasicContainer implements ISelectedOption {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;
	private Image img;

	public MainView() {
		super();

		img = Toolkit.getDefaultToolkit().getImage("../imgs/arrow_img3.jpg");

		UOptionItem item = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Canales");
		UOptionItem item2 = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Facebook");
		UOptionItem item3 = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Twitter");
		UOptionItem item4 = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Ayuda");
		UOptionItem item5 = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Salir");		

		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		itemList.add(item);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		itemList.add(item5);

		mainMenu = new UMenuScrollable(itemList, 5, 150, 150);		
		
		this.add(mainMenu);
		this.popToFront(mainMenu);
	}

	@Override
	public void paint(Graphics g) {
		mainMenu.requestFocus();
		super.paint(g);
	}

	@Override
	public void selectedOption(int selectedIndex) {
		ViewManager.getInstance().changeView("ChannelsView");		
	}

}
