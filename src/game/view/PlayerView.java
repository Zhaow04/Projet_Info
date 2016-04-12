package game.view;

import game.model.Observable;
import game.model.Player;
import game.model.component.ViewSettings;
import game.utilities.MovementAnimation;
import game.utilities.ViewUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PlayerView extends StackPane implements Observer {
	
	private Player player;
	private MapView mapView;
	private ImageView imageView;
	
	public PlayerView(Player player, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		setPlayer(player);
		player.addObserver(this);
		setMapView(mapView);
		ViewSettings imageSettings = player.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(imageSettings, mapView.cellSize()*0.8);
		setImageView(imageView);
		this.getChildren().add(imageView);
		mapView.addToMap(this, imageSettings.getX(), imageSettings.getY());
	}
	
	private Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	private MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	private ImageView getImageView() {
		return imageView;
	}

	private void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "moved") {
			updatePosition();
			mapView.updateWindowView(getPlayer());
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = getPlayer().getViewSettings();
			getImageView().setViewport(new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight()));
		}
		else if(arg == "dead")
			removeView();
	}
	
	private void updatePosition() {
		ViewSettings imageSettings = getPlayer().getViewSettings();
		new MovementAnimation(300, this, getImageView(), imageSettings, getMapView().cellSize());
	}
	
	private void removeView() {
		getMapView().remove(this);
	}
	
}
