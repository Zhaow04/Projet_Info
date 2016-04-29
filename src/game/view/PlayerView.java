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
		setPlayer(player);
		player.addObserver(this);
		setMapView(mapView);
		ViewSettings viewSettings = player.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings, mapView.cellSize()*0.8);
		setImageView(imageView);
		this.getChildren().add(imageView);
		this.setTranslateX(viewSettings.getX()*mapView.cellSize());
		this.setTranslateY(viewSettings.getY()*mapView.cellSize());
		mapView.add(this);
		movementAnimation = new MovementAnimation(300, this, imageView, viewSettings,
				getMapView().cellSize());
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
			Platform.runLater(() -> {
				updatePosition();
				mapView.updateWindowView(getPlayer());
			});
		}
		else if(arg == "changedDirection") {
			ViewSettings viewSettings = getPlayer().getViewSettings();
			Platform.runLater(() -> getImageView().setViewport(
					new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight())));
		}
		else if(arg instanceof ISkill) {
			System.out.println((ISkill) arg);
			ISkill skill = (ISkill) arg ;
			Platform.runLater(() -> new SkillView(skill, getMapView()));
		}
		else if(arg == "dead")
			Platform.runLater(() -> removeView());
	}
	
	/**
	 * Updates the position of the view through a {@code MovementAnimation}.
	 * @see {@link MovementAnimation}
	 */
	private void updatePosition() {
		ViewSettings viewSettings = getPlayer().getViewSettings();
		movementAnimation.updateAndPlay(viewSettings);
	}
	
	/**
	 * Removes the view.
	 */
	private void removeView() {
		getMapView().remove(this);
	}
	
}
