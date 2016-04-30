package game.model.monster;

import game.model.Stats;
import game.utilities.ImageDB;

/**
 * Extends from {@code Monster} <br/>
 * Red Dragon - enemy.
 * 
 * @see {@link Monster}
 *
 */
public class RedDragon extends Monster {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		
	//****************************** Constructor ******************************
	
	/**
	 * Creates a red dragon with 450HP and 150 killXp.
	 */
	public RedDragon(){
		super(ImageDB.getRedDragonView());
		setScope(2);
		setStats(new Stats(450, 150));
	}
	
}
