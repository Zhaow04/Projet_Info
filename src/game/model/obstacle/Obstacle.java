package game.model.obstacle;

import game.model.MapComponent;
import game.utilities.ViewSettings;

/**
 * Abstract class that serves as a super class to all the obstacles. <br/> 
 * Extends {@code MapComponent}.
 */
public abstract class Obstacle extends MapComponent {

	//****************************** Constructor ******************************
	
	/**
	 * Creates an obstacle.
	 * 
	 * @param viewSettings
	 */
	public Obstacle(ViewSettings viewSettings){
		super(viewSettings);
	}

}


