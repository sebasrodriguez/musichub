package MusicHub.UI;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import org.havi.ui.HContainer;
import MusicHub.Util.Conf;

public class UMenuScrollable extends HContainer implements KeyListener,
		FocusListener {

	private static final long serialVersionUID = 1L;
	private List<UOptionItem> listItems;
	private HContainer menuContainer;
	private int selectedItemIndex = 0;
	private UOptionItem itemSelected;

	private int getMenuHeight(){
		return this.getNumItems() * Conf.getItemHeight();
	}

	// Constructor que acepta una lista de UOptionItem
	public UMenuScrollable(List<UOptionItem> items, int x, int y) {
		this.listItems = items;
		setSubContainers();
		showItems();
		this.setBounds(x, y, Conf.getMenuWidth(), this.getMenuHeight());
	}

	private void setSubContainers() {
		menuContainer = new HContainer(0, 0, Conf.getMenuWidth(), this.getMenuHeight());		
		addKeyListener(this);
		addFocusListener(this);
	}

	public void addOption(UOptionItem opt) {
		this.listItems.add(opt);
	}

	public int getNumItems() {
		return this.listItems.size();
	}

	public void showItems() {
		Iterator<UOptionItem> it = listItems.iterator();
		int index = 0;
		int itemVPos = 0;

		while (it.hasNext()) {
			UOptionItem aux = it.next();
			aux.setLocation(0, itemVPos);
			itemVPos += aux.getHeight();
			this.menuContainer.add(aux, index);
			index++;
		}

		// Indico que la primera opcion es la seleccionada
		((UOptionItem) this.listItems.get(0)).setSelected(true);
		itemSelected = (UOptionItem) this.listItems.get(0);

		menuContainer.setVisible(true);
		this.add(menuContainer);
	}

	// KeyListeners
	public void keyPressed(KeyEvent e) {
		// flecha abajo y arriba		
		switch (e.getKeyCode()) {
		case 10:
			System.out.println("ok");
			break;
		case 40:
		case 38:
			itemSelected.setSelected(false);
			UOptionItem next = null;
			if (e.getKeyCode() == 40) {
				selectedItemIndex++;
			} else {
				selectedItemIndex--;
			}

			if (selectedItemIndex < 0) {
				selectedItemIndex = menuContainer.getComponentCount() - 1;
			}

			if (selectedItemIndex < menuContainer.getComponentCount()) {
				next = ((UOptionItem) menuContainer
						.getComponent(selectedItemIndex));
			} else {
				selectedItemIndex = 0;
				next = ((UOptionItem) menuContainer
						.getComponent(selectedItemIndex));
			}

			next.setSelected(true);
			itemSelected = next;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void focusGained(FocusEvent arg0) {
		System.out.println("menu focus");

	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}
}
