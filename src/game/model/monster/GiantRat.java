package game.model.monster;

import game.model.Stats;
import game.utilities.ImageDB;

/**
 * Extends from {@code Monster} <br/>
 * Giant rat - enemy.
 * 
 * @see {@link Monster}
 *
 */
public class GiantRat extends Monster {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//****************************** Constructor ******************************
	
	/**
	 * Creates a giant rat with 300HP and 50 killXp.
	 */
	public GiantRat(){
		super(ImageDB.getGiantRatView(), new Stats(300, 50));
		setScope(2);
	}
	
}
