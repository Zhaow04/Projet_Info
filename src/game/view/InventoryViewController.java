package game.view;

import game.model.Observable;
import game.model.Player;
import game.model.item.Item;
import game.view.component.ItemMenu;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class InventoryViewController implements Observer {
	
	private Player player;
	
	@FXML
	private TilePane inventoryContainer; // Contains StackPanes, 1 StackPane = 1 item slot
	
	public InventoryViewController() {
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		//player.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

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
	
	public synchronized void removeItemView(int index) {
		
	}
}
