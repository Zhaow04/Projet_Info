package game.model.mapcomponent;

import game.model.MapComponent;
import game.model.component.ViewSettings;

/**
 * Abstract class that serves as a super class to all the obstacles.
 * 
 * @author ZhaoWen
 *
 */
public abstract class Obstacle extends MapComponent {

	//****************************** Attributes ******************************

	//****************************** Constructor ******************************
	
	/**
	 * Creates an obstacle on the current map.
	 * 
	 * @param map
	 * @see {@link ViewSettings}
	 */
	public Obstacle(ViewSettings viewSettings){
		super(viewSettings);
	}

	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************

	
}


