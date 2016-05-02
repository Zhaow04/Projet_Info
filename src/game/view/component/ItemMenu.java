package game.view.component;

import game.Main;
import game.model.Player;
import game.view.ItemView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

/**
 * Extends from {@code MenuButton}. <br/>
 * Transparent {@link MenuButton} to be added on top of a {@link ItemView} to allow the user to use or throw the
 * {@code Item}.
 * 
 * @author ZhaoWen
 * @see {@link MenuButton}
 * @see {@link game.model.item.Item}
 *
 */
public class ItemMenu extends MenuButton {
	
	/**
	 * Creates an transparent {@link MenuButton} with two {@link MenuItem} that allow the following
	 * actions on an {@code Item}: use or throw.
	 * @param player
	 * @param index
	 * @param itemView
	 * @see {@link game.model.item.Item}
	 */
	public ItemMenu(Player player, int index, Pane itemView) {
		super();
		this.setPrefSize(50, 50);
		this.setOpacity(0);
		this.setPopupSide(Side.RIGHT);
		this.getStylesheets().add(Main.class.getResource("view/component/ItemSlot.css").toExternalForm());
		MenuItem useItem = new MenuItem("Utiliser");
		useItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.useItem(index);
				itemView.getChildren().clear();
				//Pane parent = (Pane) itemView.getParent();
				//parent.getChildren().remove(itemView);
			}
		});
		MenuItem throwItem = new MenuItem("Jeter");
		throwItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.throwItem(index);
				itemView.getChildren().clear();
				//Pane parent = (Pane) itemView.getParent();
				//parent.getChildren().remove(itemView);
			}
		});
		this.getItems().addAll(useItem,throwItem);
		itemView.getChildren().add(this);
		
	}
	
}
