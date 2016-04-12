package game.utilities;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Implements {@code Runnable}.
 * Used for fluid transition and animation when a living being moves.
 * 
 * @author ZhaoWen
 * @see {@link Runnable}
 *
 */
public class ObjectTranslate {
	
	//****************************** Attributes ******************************
	
	private TranslateTransition tt;
	
	//****************************** Constructor ******************************
	
	public ObjectTranslate(Duration duration, Node node, double toXorY, String s) {
		tt = new TranslateTransition(duration,node);
		if(s.toLowerCase() == "x")
			tt.setToX(toXorY);
		else if(s.toLowerCase() == "y")
			tt.setToY(toXorY);
		tt.setCycleCount(1);
		tt.play();
	}
	
	public ObjectTranslate(Duration duration, Node node, double toX, double toY) {
		tt = new TranslateTransition(duration,node);
		tt.setToX(toX);
		tt.setToY(toY);
		tt.setCycleCount(1);
		tt.play();
	}
	
	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	
}
