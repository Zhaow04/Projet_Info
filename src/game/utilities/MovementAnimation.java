package game.utilities;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class that handles the animation during a movement. Plays a {@link TranslateTransition} and a
 * {@link SpriteAnimation} in parallel.
 * 
 * @author ZhaoWen
 *
 */
public final class MovementAnimation {
	
	private TranslateTransition tt;
	private SpriteAnimation sa;
	private ParallelTransition pt;
	private double cellSize;
	private Node node;
	
	/**
	 * Constructor of {@link MovementAnimation}.
	 * @param millis The duration in milliseconds of the animation
	 * @param node The {@code node} which will be translated
	 * @param imageView The {@code ImageView} which will be animated
	 * @param viewSettings The {@code ViewSettings} which contains the information concerning the animation
	 * @param cellSize The size of one map's cell
	 */
	public MovementAnimation(int millis, Node node, ImageView imageView,
			ViewSettings viewSettings, double cellSize) {
		tt = new TranslateTransition(Duration.millis(millis),node);
		this.cellSize = cellSize;
		this.node = node;
		tt.setFromX(node.getTranslateX());
		tt.setFromY(node.getTranslateY());
		tt.setToX(viewSettings.getX()*cellSize);
		tt.setToY(viewSettings.getY()*cellSize);
		tt.setCycleCount(1);
		
		sa = new SpriteAnimation(viewSettings, imageView, millis);
		pt = new ParallelTransition(tt,sa);
	}
	
	/**
	 * Plays the animation.
	 */
	public void play() {
		Platform.runLater(() -> pt.play());
	}

	/**
	 * Updates the settings of the animation and plays it.
	 * @param viewSettings
	 */
	public void updateAndPlay(ViewSettings viewSettings) {
		pt.stop();
		tt.setFromX(node.getTranslateX());
		tt.setFromY(node.getTranslateY());
		tt.setToX(viewSettings.getX()*cellSize);
		tt.setToY(viewSettings.getY()*cellSize);
		sa.update(viewSettings);
		Platform.runLater(() -> pt.playFromStart());
	}

	/**
	 * Adds an {@code action} which will be executed at the end of the animation.
	 * @param action
	 */
	public void addOnFinished(EventHandler<ActionEvent> action) {
		pt.setOnFinished(action);
	}

}
