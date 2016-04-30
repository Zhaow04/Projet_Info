package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle}. <br/>
 * Basic obstacle: bush.
 * 
 * @see {@link Obstacle}
 *
 */
public final class Bush extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//****************************** Constructor ******************************

	/**
	 * Creates a bush.
	 * 
	 */
	public Bush() {
		super(ImageDB.getBushView());
	}
	
}
