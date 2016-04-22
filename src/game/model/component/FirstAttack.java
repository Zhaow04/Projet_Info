package game.model.component;

import game.utilities.ImageDB;

/**
 * Extends from {@code DirectAttack}. <br/>
 * First attack of the player.
 * 
 * @author ZhaoWen
 * @see {@link DirectAttack}
 *
 */
public class FirstAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public FirstAttack() {
		super(100, ImageDB.getLightningView());
	}
	
	public void preUse(SkillUser user) {
		
	}
	
	@Override
	public void notifyAnimationEnd() {
		// TODO Auto-generated method stub
		
	}
	
}
