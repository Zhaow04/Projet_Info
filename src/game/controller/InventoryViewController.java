package game.controller;

import java.util.ArrayList;

import game.model.Observable;
import game.model.Player;
import game.model.item.Item;
import game.utilities.ViewUtils;
import game.view.Observer;
import game.view.component.ItemMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 * Implements {@link Observer}. <br/>
 * Controller class for the inventory window. Allows the user the use or throw items.
 * 
 * @author ZhaoWen
 *
 */
public final class InventoryViewController implements Observer {
	
	private Player player;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	/**
	 * Void constructor.
	 */
	public InventoryViewController() {
		
	}

	/**
	 * Updates the inventory window, i.e. shows the items in the inventory.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> initItems());
	}

	/**
	 * Connects the inventory window with the player.
	 * @param player
	 */
	public void init(Player player) {
		this.player = player;
		player.getInventory().addObserver(this);
		initItems();
	}
	
	/**
	 * Initializes the inventory window, i.e. shows all the items in the inventory.
	 */
	private void initItems() {
		ArrayList<Item> items = player.getInventory().getItems();
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(item != null) {
				StackPane itemContainer = (StackPane) inventoryContainer.getChildren().get(i);
				ImageView imageView = ViewUtils.initImageView(item.getViewSettings(),
						itemContainer.getPrefWidth());
				itemContainer.getChildren().add(imageView);
				new ItemMenu(player, i, itemContainer);
			}
		}
	}

	/**
	 * Adds a {@code ItemView} to the inventory window and allows the user to use or throw the item.
	 * @param itemView
	 */
	/*public synchronized void addItemView(ItemView itemView) {
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
	}*/
	
}
