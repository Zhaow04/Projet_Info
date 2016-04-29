package game.view;

import game.model.Observable;
import game.model.Player;
import game.model.skill.ISkill;
import game.utilities.MovementAnimation;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Implements {@code Observer}. <br/>
 * Extends from {@code StackPane}. <br/>
 * View of a {@code Player}.
 * 
 * @author ZhaoWen
 *
 */
public class PlayerView extends StackPane implements Observer {
	
	private Player player;
	private MapView mapView;
	private ImageView imageView;
	private MovementAnimation movementAnimation;
	
	/**
	 * Creates the view of {@code player} and adds to {@code mapView}.
	 * @param player
	 * @param mapView
	 */
	public PlayerView(Player player, MapView mapView) {
		super();
		this.setPrefSize(mapView.cellSize(), mapView.cellSize());
		this.player = player;
		player.addObserver(this);
		this.mapView = mapView;
		ViewSettings viewSettings = player.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.8);
		this.imageView = imageView;
		this.getChildren().add(imageView);
		this.setTranslateX(viewSettings.getX()*mapView.cellSize());
		this.setTranslateY(viewSettings.getY()*mapView.cellSize());
		mapView.add(this);
		movementAnimation = new MovementAnimation(300, this, imageView, viewSettings,
				mapView.cellSize());
	}
	
	/**
	 * Updates the view of the player. This method is used to move the view to a designated position, to 
	 * change the direction in which the player is looking, to create a the view of a skill or to remove
	 * the dead player.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "moved") {
			Platform.runLater(() -> {
				updatePosition();
				mapView.updateWindowView(player);
			});
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = player.getViewSettings();
			Platform.runLater(() -> imageView.setViewport(
					new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight())));
		}
		else if(arg instanceof ISkill) {
			System.out.println((ISkill) arg);
			ISkill skill = (ISkill) arg ;
			Platform.runLater(() -> new SkillView(skill, mapView));
		}
		else if(arg == "dead")
			Platform.runLater(() -> removeView());
	}
	
	/**
	 * Updates the position of the view through a {@code MovementAnimation}.
	 * @see {@link MovementAnimation}
	 */
	private void updatePosition() {
		ViewSettings viewSettings = player.getViewSettings();
		movementAnimation.updateAndPlay(viewSettings);
	}
	
	/**
	 * Removes the view.
	 */
	private void removeView() {
		mapView.remove(this);
	}
	
}
