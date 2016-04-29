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

public class ItemView extends StackPane implements Observer {
	
	private Item item;
	private ImageView imageView;
	private MapView mapView;
	private InventoryViewController inventoryViewController;
	
	public ItemView(Item item, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		setItem(item);
		item.addObserver(this);
		setMapView(mapView);
		//setInventoryViewController(inventoryViewController);
		ViewSettings viewSettings = item.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.5);
		setImageView(imageView);
		this.getChildren().add(imageView);
		mapView.addToMap(this, viewSettings.getX(), viewSettings.getY());
	}
	
	public Item getItem() {
		return item;
	}

	private void setItem(Item item) {
		this.item = item;
	}

	private ImageView getImageView() {
		return imageView;
	}

	private void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	private MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	private InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	private void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}

	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> transferItemView());
	}

	private void transferItemView() {
		ItemView itemView = this;
		ImageView container = getImageView();
		FadeTransition t = new FadeTransition(Duration.millis(300), this);
		t.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getMapView().remove(itemView);
				getMapView().getGameView().getInventoryViewController().addItemView(itemView);
			}
		});
		t.setToValue(0.9);
		t.setCycleCount(1);
		t.setDelay(Duration.millis(100));
		t.play();
	}
	
}
