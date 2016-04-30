package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle}. <br/>
 * Basic obstacle: rock.
 * 
 * @see {@link Obstacle}
 *
 */
public final class Rock extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//****************************** Constructor ******************************
	
	/**
	 * Creates a rock.
	 */
	public Rock() {
		super(ImageDB.getRockView());
	}

}
