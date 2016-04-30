package game.utilities;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Used for fluid transition when a living being moves.
 * 
 * @author ZhaoWen
 * @see {@link Runnable}
 *
 */
public class ObjectTranslate {
	
	//****************************** Attributes ******************************
	
	private TranslateTransition tt;
	
	//****************************** Constructor ******************************
	
	/**
	 * Constructor of {@link ObjectTranslate}.
	 * @param duration The duration of the transition
	 * @param node The {@code node} which will be translated
	 * @param toXorY The x or y coordinate to reach at the end of the transition
	 * @param s The string which indicates the direction of the transition (x or y)
	 */
	public ObjectTranslate(Duration duration, Node node, double toXorY, String s) {
		tt = new TranslateTransition(duration,node);
		if(s.toLowerCase() == "x")
			tt.setToX(toXorY);
		else if(s.toLowerCase() == "y")
			tt.setToY(toXorY);
		tt.setCycleCount(1);
		tt.play();
	}
	
	/**
	 * 
	 * @param duration The duration of the transition
	 * @param node The {@code node} which will be translated
	 * @param toX The x coordinate to reach at the end of the transition
	 * @param toY The y coordinate to reach at the end of the transition
	 */
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
