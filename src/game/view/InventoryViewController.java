package game.view;

import game.model.Player;
import game.model.item.Item;
import game.view.component.ItemMenu;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 * Controller class for the inventory window. Allows the user the use or throw items.
 * 
 * @author ZhaoWen
 *
 */
public class InventoryViewController {
	
	private Player player;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	/**
	 * Void constructor.
	 */
	public InventoryViewController() {
		
	}
	
	/**
	 * Connects the inventory window with the player.
	 * @param player
	 */
	public void init(Player player) {
		this.player = player;
	}

	/**
	 * Adds a {@code ItemView} to the inventory window and allows the user to use or throw the item.
	 * @param itemView
	 */
	public synchronized void addItemView(ItemView itemView) {
		Item item = itemView.getItem();
		int index = player.getInventory().getItemNumber(item);
		StackPane itemContainer = (StackPane) inventoryContainer.getChildren().get(index);
		itemView.setTranslateX(0); itemView.setTranslateY(0);
		new ItemMenu(player, index, itemView);
		itemContainer.getChildren().add(itemView);
		itemView.setPrefWidth(itemContainer.getPrefWidth());
		itemView.setPrefHeight(itemContainer.getPrefHeight());
		ImageView imageView = (ImageView) itemView.getChildren().get(0);
		imageView.setFitWidth(itemContainer.getPrefWidth());
		imageView.setFitHeight(itemContainer.getPrefHeight());
		//itemView.setPreserveRatio(true);
	}
	
}
