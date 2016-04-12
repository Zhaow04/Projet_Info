package game.view;

import game.model.Observable;
import game.model.component.ViewSettings;
import game.model.monster.Monster;
import game.utilities.MovementAnimation;
import game.utilities.ViewUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MonsterView extends StackPane implements Observer {
	
	private Monster monster;
	private ImageView imageView;
	private MapView mapView;
	
	public MonsterView(Monster monster, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		setMonster(monster);
		monster.addObserver(this);
		setMapView(mapView);
		ViewSettings viewSettings = monster.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.8);
		setImageView(imageView);
		this.getChildren().add(imageView);
		mapView.addToMap(this, viewSettings.getX(), viewSettings.getY());
	}

	private Monster getMonster() {
		return monster;
	}

	private void setMonster(Monster monster) {
		this.monster = monster;
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
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "moved"){
			updatePosition();
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = getMonster().getViewSettings();
			getImageView().setViewport(new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight()));
		}
		else if(arg == "dead") {
			removeView();
		}

	}

	private void updatePosition() {
		ViewSettings imageSettings = getMonster().getViewSettings();
		new MovementAnimation(300, this, getImageView(), imageSettings, getMapView().cellSize());
	}

	private void removeView() {
		getMapView().remove(this);
	}

}
