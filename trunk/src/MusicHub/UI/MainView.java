package MusicHub.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import org.havi.ui.HIcon;

public class MainView extends BasicContainer implements KeyListener,
		FocusListener {

	private static final long serialVersionUID = 1L;
	private UMenuScrollable mainMenu;
	private Image img;

	public MainView(String title) {
		super(title);

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
				"Ayuda 5");
		UOptionItem item6 = new UOptionItem(new HIcon(img, 0, 0, 800, 500),
				"Ayuda 6");

		List<UOptionItem> itemList = new LinkedList<UOptionItem>();
		itemList.add(item);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		itemList.add(item5);
		itemList.add(item6);

		mainMenu = new UMenuScrollable(itemList, 3, 150, 150);

		this.add(mainMenu);
		this.popToFront(mainMenu);
		// scene.add(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mainView focus");
		// mainMenu.requestFocus();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("main lost");

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		mainMenu.requestFocus();
		super.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
