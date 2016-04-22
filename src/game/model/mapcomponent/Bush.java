package game.model.mapcomponent;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle} <br/>
 * Basic obstacle.
 * 
 * @author ZhaoWen
 * @see {@link Obstacle}
 *
 */
public class Bush extends Obstacle {

	//****************************** Attributes ******************************
	

	//****************************** Constructor ******************************

	/**
	 * Creates a bush and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Obstacle#Obstacle(Map)}
	 */
	public Bush() {
		super(ImageDB.getBushView());
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
