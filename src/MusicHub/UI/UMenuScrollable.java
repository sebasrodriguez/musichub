package MusicHub.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.havi.ui.HContainer;
import MusicHub.UI.Contracts.IMenuContainer;
import MusicHub.Util.Conf;

public class UMenuScrollable extends HContainer implements KeyListener {

	private static final long serialVersionUID = 1L;
	private IMenuContainer menuParentContainer;
	private List<UOptionItem> listItems;
	private int itemsToShow;
	private HContainer menuContainer;
	private int selectedItemIndex = 0;
	private UOptionItem itemSelected;

	private int getMenuWidth() {
		if (listItems == null || listItems.size() == 0) {
			return 100;
		}
		return listItems.get(0).getWidth();
	}

	private int getMenuHeight() {
		return this.itemsToShow * Conf.getItemHeight();
	}

	// Constructor que acepta una lista de UOptionItem
	public UMenuScrollable(List<UOptionItem> items, int itemsToShow,
			IMenuContainer menuParentContainer, int x, int y) {
		this.listItems = items;
		this.itemsToShow = itemsToShow;
		this.setMenuParentContainer(menuParentContainer);
		this.setSubContainers();
		this.showItems();
		this.initializeShownItems();
		this.setBounds(x, y, this.getMenuWidth(), this.getMenuHeight());
		this.getMenuParentContainer().stepedOnOption(itemSelected);
	}

	private void initializeShownItems() {
		for (int i = 0; i < itemsToShow; i++) {
			if (this.listItems.size() >= itemsToShow) {
				this.listItems.get(i).isShown(true);
			}
		}
	}

	private void setShownItems(List<Integer> shownItemsIndexes) {
		int index = 0;

		for (UOptionItem optionItem : this.listItems) {
			optionItem.isShown(shownItemsIndexes.contains(index) ? true : false);
			index++;
		}
	}

	private List<Integer> getShownItemsIndexes() {
		List<Integer> indexes = new ArrayList<Integer>();

		int index = 0;
		for (UOptionItem optionItem : this.listItems) {
			if (optionItem.isShown()) {
				indexes.add(index);
			}
			else if (!optionItem.isShown() && indexes.size() > 0) {
				break;
			}
			index++;
		}

		return indexes;
	}

	private void setSubContainers() {
		menuContainer = new HContainer(0, 0, this.getMenuWidth(), this.getMenuHeight());
		addKeyListener(this);
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

	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	public IMenuContainer getMenuParentContainer() {
		return menuParentContainer;
	}

	public void setMenuParentContainer(IMenuContainer menuParentContainer) {
		this.menuParentContainer = menuParentContainer;
	}

	// KeyListeners
	public void keyPressed(KeyEvent e) {
		// flecha abajo y arriba
		switch (e.getKeyCode()) {
		case 10:
			this.getMenuParentContainer().selectedOption(itemSelected);
			break;
		case 40:
		case 38:
			// set selected item
			itemSelected.setSelected(false);
			if (e.getKeyCode() == 40) {
				selectedItemIndex++;
			}
			else {
				selectedItemIndex--;
			}

			if (selectedItemIndex < 0) {
				selectedItemIndex = 0;
			}
			if (selectedItemIndex >= this.listItems.size() - 1) {
				selectedItemIndex = this.listItems.size() - 1;
			}

			UOptionItem selected = null;
			if (selectedItemIndex < menuContainer.getComponentCount()) {
				selected = ((UOptionItem) menuContainer.getComponent(selectedItemIndex));
			}
			else {
				selectedItemIndex = menuContainer.getComponentCount() - 1;
				selected = ((UOptionItem) menuContainer.getComponent(selectedItemIndex));
			}

			selected.setSelected(true);
			itemSelected = selected;

			// scroll menu if selected item is not visible
			List<Integer> shownItemsIndexes = this.getShownItemsIndexes();

			if (this.listItems.size() > this.itemsToShow) {
				if (!shownItemsIndexes.contains(selectedItemIndex)) {
					// Si movio un elemento para abajo actualizo todos los
					// visibles
					if (shownItemsIndexes.get(shownItemsIndexes.size() - 1) + 1 == selectedItemIndex) {
						// actualizamos la lista de elementos mostrados
						shownItemsIndexes.remove(0);
						shownItemsIndexes.add(selectedItemIndex);
						this.setShownItems(shownItemsIndexes);

						for (UOptionItem optionItem : listItems) {
							optionItem.setLocation(optionItem.getX(),
									optionItem.getY() - Conf.getItemHeight());
						}
					} // si movio un elemento hacia arriba muevo todos los
						// contenidos hacia arriba
					else if (shownItemsIndexes.get(0) - 1 == selectedItemIndex) {
						// actualizamos la lista de elementos mostrados
						shownItemsIndexes.remove(shownItemsIndexes.size() - 1);
						shownItemsIndexes.add(selectedItemIndex);

						this.setShownItems(shownItemsIndexes);

						for (UOptionItem optionItem : listItems) {
							optionItem.setLocation(optionItem.getX(),
									optionItem.getY() + Conf.getItemHeight());
						}
					}
				}
			}
			// Disparamos el evento de stepedOn
			this.getMenuParentContainer().stepedOnOption(itemSelected);
			break;
		default:
			this.getMenuParentContainer().unmanagedKey(e.getKeyCode());
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
