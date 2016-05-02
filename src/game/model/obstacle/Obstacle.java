package game.model.obstacle;

import game.model.MapComponent;
import game.utilities.ViewSettings;

/**
 * Extends {@code MapComponent}. <br/>
 * Abstract class that serves as a super class to all the obstacles.
 * 
 * @see {@link MapComponent}
 * 
 */
public class Obstacle extends MapComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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


