package game.view;

import java.util.Observable;
import java.util.Observer;

import game.model.Inventory;
import game.model.Item;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class InventoryViewController implements Observer {
	
	private Inventory inventory;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	public InventoryViewController() {
		
	}
	
	public void addItemView(ImageView imageView, int index) {
		StackPane itemContainer = (StackPane) inventoryContainer.getChildren().get(index);
		itemContainer.getChildren().add(imageView);
		imageView.setFitWidth(itemContainer.getPrefWidth());
		imageView.setFitHeight(itemContainer.getPrefHeight());
		imageView.setPreserveRatio(true);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(true) {
			Inventory inventory = (Inventory) o;
			int index = (int) arg;
			Item item = inventory.getItem(index);
			StackPane itemContainer = ItemView.getContainer(item);
			ImageView imageView = (ImageView) itemContainer.getChildren().get(0);
			addItemView(imageView, index);
			ItemView.removeContainer(item);
		}
	}
	
}
