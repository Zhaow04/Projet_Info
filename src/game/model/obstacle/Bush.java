package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle} <br/>
 * Basic obstacle : bush.
 * 
 * @see {@link Obstacle}
 *
 */
public class Bush extends Obstacle {

	//****************************** Constructor ******************************

	/**
	 * Creates a bush.
	 * 
	 */
	public Bush() {
		super(ImageDB.getBushView());
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
