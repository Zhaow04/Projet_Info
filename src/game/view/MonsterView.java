package game.view;

import game.model.Observable;
import game.model.component.ISkill;
import game.model.component.Skill;
import game.model.monster.Monster;
import game.utilities.MovementAnimation;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MonsterView extends StackPane implements Observer {
	
	private Monster monster;
	private ImageView imageView;
	private MapView mapView;
	private MovementAnimation movementAnimation;
	
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
		movementAnimation = new MovementAnimation(300, this, getImageView(), viewSettings,
				getMapView().cellSize());
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
			Platform.runLater(() -> updatePosition());
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = getMonster().getViewSettings();
			Platform.runLater(() -> getImageView().setViewport(
					new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight())));
		}
		else if(arg instanceof ISkill) {
			ISkill skill = (ISkill) arg;
			Platform.runLater(() -> new SkillView(skill, getMapView()));
		}
		else if(arg == "dead") {
			Platform.runLater(() -> removeView());
		}

	}

	private void updatePosition() {
		ViewSettings viewSettings = getMonster().getViewSettings();
		movementAnimation.updateAndPlay(viewSettings);
	}

	private void removeView() {
		MonsterView v = this;
		PauseTransition pt = new PauseTransition(Duration.millis(500));
		pt.setOnFinished((event) -> getMapView().remove(v));
		pt.play();
	}

}
