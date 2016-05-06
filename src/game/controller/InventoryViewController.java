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
 *
 */
public final class InventoryViewController implements Observer {
	
	//****************************** Attributes ******************************

	private Player player;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	//****************************** Constructor ******************************

	/**
	 * Void constructor.
	 */
	public InventoryViewController() {
		
	}

	//******************************** Methods ********************************

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
	
}
