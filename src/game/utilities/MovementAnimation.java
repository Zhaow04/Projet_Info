package game.utilities;

import game.model.component.ViewSettings;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MovementAnimation {
	
	public MovementAnimation(int millis, Node node, ImageView imageView,
			ViewSettings viewSettings, double cellSize) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(millis),node);
		tt.setToX(viewSettings.getX()*cellSize);
		tt.setToY(viewSettings.getY()*cellSize);
		tt.setCycleCount(1);
		
		SpriteAnimation sa = new SpriteAnimation(viewSettings, imageView, millis);
		ParallelTransition pt = new ParallelTransition(tt,sa);
		pt.play();
	}
	
}
