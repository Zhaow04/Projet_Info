package game.model.mapcomponent;

import game.utilities.ViewSettings;

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
		super(new ViewSettings("game/utilities/treecomp.PNG", 290, 130, 90, 90, new int[2]));
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
