package game.model.monster;

import game.model.Stats;
import game.utilities.ImageDB;

/**
 * Extends from {@code Monster} <br/>
 * Orange bat - enemy.
 * 
 * @see {@link Monster}
 *
 */
public class OrangeBat extends Monster {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an orange bat with 350HP and 75 killXp.
	 */
	public OrangeBat(){
		super(ImageDB.getOrangeBatView());
		setScope(2);
		setStats(new Stats(350, 75));
	}
	
}
