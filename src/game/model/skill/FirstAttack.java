package game.model.skill;

import game.utilities.ImageDB;

/**
 * Extends from {@code DirectAttack}. <br/>
 * First attack of the player : lightning.
 * 
 * @see {@link DirectAttack}
 *
 */
public class FirstAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack of the player : lightning that deals 100 damage.
	 */
	public FirstAttack() {
		super(100, ImageDB.getLightningView(), 1000);
	}
	
}
