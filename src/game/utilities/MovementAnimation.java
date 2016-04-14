package game.utilities;

import game.model.component.ViewSettings;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MovementAnimation {
	
	private TranslateTransition tt;
	private SpriteAnimation sa;
	private ParallelTransition pt;
	private double cellSize;
	private Node node;
	
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
	
	public void play() {
		Platform.runLater(() -> pt.play());
	}

	public void updateAndPlay(ViewSettings viewSettings) {
		pt.stop();
		tt.setFromX(node.getTranslateX());
		tt.setFromY(node.getTranslateY());
		tt.setToX(viewSettings.getX()*cellSize);
		tt.setToY(viewSettings.getY()*cellSize);
		sa.update(viewSettings);
		Platform.runLater(() -> pt.playFromStart());
	}

	public void addOnFinished(EventHandler<ActionEvent> action) {
		pt.setOnFinished(action);
	}

}
