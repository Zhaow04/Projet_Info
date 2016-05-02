package game.view;

import game.controller.InventoryViewController;
import game.model.Observable;
import game.model.item.Item;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Implements {@code Observer}. <br/>
 * Extends from {@code StackPane}. <br/>
 * View of an {@code Item}.
 * 
 * @author ZhaoWen
 *
 */
public class ItemView extends StackPane implements Observer {
	
	private Item item;
	private ImageView imageView;
	private MapView mapView;
	
	/**
	 * Creates the view of {@code Item}.
	 * @param item
	 * @param mapView
	 * @see {@link Item}
	 */
	public ItemView(Item item, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		this.item = item;
		item.addObserver(this);
		this.mapView = mapView;
		ViewSettings viewSettings = item.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.5);
		this.imageView = imageView;
		this.setTranslateX(viewSettings.getX()*mapView.cellSize());
		this.setTranslateY(viewSettings.getY()*mapView.cellSize());
		this.getChildren().add(imageView);
	}
	
	/**
	 * Gets the {@code Item} associated to this {@code ItemView}.
	 * @return associated {@code Item}
	 * @see {@link Item}
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the {@code ImageView} of the {@code Item} associated to this {@code ItemView}.
	 * @return {@code ImageView} of the {@code Item}
	 * @see {@link Item}
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * Transfers the view of the {@code Item} to the inventory window.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> transferItemView());
	}

	/**
	 * Removes the view from {@code MapView} and transfers to {@code InventoryViewController} to show
	 * in the inventory window.
	 * @see {@link InventoryViewController}
	 */
	private void transferItemView() {
		ItemView itemView = this;
		FadeTransition t = new FadeTransition(Duration.millis(300), this);
		t.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapView.remove(itemView);
				//mapView.gameView.inventoryViewController.addItemView(itemView);
			}
		});
		t.setToValue(0.9);
		t.setCycleCount(1);
		t.setDelay(Duration.millis(100));
		t.play();
	}
	
}
