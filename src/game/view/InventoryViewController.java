package game.view;

import game.model.Inventory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class InventoryViewController {
	
	private Inventory inventory;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	public InventoryViewController() {
		
	}
	
	public void addItemView(ImageView imageView) {
		StackPane itemContainer = (StackPane) inventoryContainer.getChildren().get(0);
		itemContainer.getChildren().add(imageView);
		imageView.setFitWidth(itemContainer.getWidth());
		imageView.setFitHeight(itemContainer.getHeight());
		imageView.setPreserveRatio(true);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
