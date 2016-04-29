package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle} <br/>
 * Basic obstacle : rock.
 * 
 * @see {@link Obstacle}
 *
 */
public class Rock extends Obstacle{

	//****************************** Constructor ******************************
	
	/**
	 * Creates a rock.
	 */
	public Rock(){
		super(ImageDB.getRockView());
	}

}
