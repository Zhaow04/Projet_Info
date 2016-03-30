package game.controller;

import game.view.InventoryViewController;
import game.view.ItemView;

public class ItemController {
	
	private ItemView itemView;
	private InventoryViewController inventoryController;
	
	public ItemController() {
		
	}

	public ItemView getItemView() {
		return itemView;
	}
	
	public void setItemView(ItemView itemView) {
		this.itemView = itemView;
	}

	public InventoryViewController getInventoryController() {
		return inventoryController;
	}

	public void setInventoryController(InventoryViewController inventoryController) {
		this.inventoryController = inventoryController;
	}
	
	
}
