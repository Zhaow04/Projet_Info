package game.model;

import game.utilities.ImageSettings;

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
	public FirstAttack(){
		super();
		setDamage(100);
		setImageSettings(new ImageSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700));
	}
	
}
