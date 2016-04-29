package game.model.monster;

import game.model.Stats;
import game.utilities.ImageDB;

/**
 * Extends from {@code Monster} <br/>
 * Blue Dragon - enemy.
 * 
 * @see {@link Monster}
 *
 */
public class BlueDragon extends Monster {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a blue dragon with 400HP and 100 killXp.
	 */
	public BlueDragon() {
		super(ImageDB.getBlueDragonView());
		setScope(2);
		setStats(new Stats(400, 100));
		}

}
