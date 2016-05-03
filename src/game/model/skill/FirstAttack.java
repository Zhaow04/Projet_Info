package game.model.skill;

import game.utilities.ImageDB;

/**
 * Extends from {@code DirectAttack}. <br/>
 * First attack of the player : lightning.
 * 
 * @see {@link DirectAttack}
 *
 */
public final class FirstAttack extends DirectAttack {
	
	private static final long serialVersionUID = 1L;
	
	//****************************** Constructor ******************************

	/**
	 * Creates the first attack of the player : lightning that deals 100 damage and has a release time of 1sec.
	 */
	public FirstAttack() {
		super(100, ImageDB.getLightningView(), 1000);
	}
	
}
