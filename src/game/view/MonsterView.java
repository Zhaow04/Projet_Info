package game.view;

import game.model.Observable;
import game.model.monster.Monster;
import game.model.skill.ISkill;
import game.utilities.MovementAnimation;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Implements {@code Observer}. <br/>
 * Extends from {@code StackPane}. <br/>
 * View of a {@code Monster}.
 * 
 * @author ZhaoWen
 *
 */
public class MonsterView extends StackPane implements Observer {
	
	private Monster monster;
	private ImageView imageView;
	private MapView mapView;
	private MovementAnimation movementAnimation;
	
	/**
	 * Creates the view of {@code monster} and adds to {@code mapView}.
	 * @param monster
	 * @param mapView
	 */
	public MonsterView(Monster monster, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		this.monster = monster;
		monster.addObserver(this);
		this.mapView = mapView;
		ViewSettings viewSettings = monster.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.8);
		this.imageView = imageView;
		this.getChildren().add(imageView);
		this.setTranslateX(viewSettings.getX()*mapView.cellSize());
		this.setTranslateY(viewSettings.getY()*mapView.cellSize());
		movementAnimation = new MovementAnimation(300, this, imageView, viewSettings,
				mapView.cellSize());
	}
	
	/**
	 * Updates the view of the monster. This method is used to move the view to a designated position, to 
	 * change the direction in which the monster is looking, to create a the view of a skill or to remove
	 * the dead monster.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "moved"){
			Platform.runLater(() -> updatePosition());
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = monster.getViewSettings();
			Platform.runLater(() -> imageView.setViewport(
					new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight())));
		}
		else if(arg instanceof ISkill) {
			ISkill skill = (ISkill) arg;
			Platform.runLater(() -> new SkillView(skill, mapView));
		}
		else if(arg == "dead") {
			Platform.runLater(() -> removeView());
		}

	}

	/**
	 * Updates the position of the view through a {@code MovementAnimation}.
	 * @see {@link MovementAnimation}
	 */
	private void updatePosition() {
		ViewSettings viewSettings = monster.getViewSettings();
		movementAnimation.updateAndPlay(viewSettings);
	}

	/**
	 * Removes the view.
	 */
	private void removeView() {
		MonsterView v = this;
		PauseTransition pt = new PauseTransition(Duration.millis(500));
		pt.setOnFinished((event) -> mapView.remove(v));
		pt.play();
	}

}
