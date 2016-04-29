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

	//****************************** Constructor ******************************
	
	/**
	 * Creates a giant rat with 300HP and 50 killXp.
	 */
	public GiantRat(){
		super(ImageDB.getGiantRatView());
		setScope(2);
		setStats(new Stats(300, 50));
	}
	
}
