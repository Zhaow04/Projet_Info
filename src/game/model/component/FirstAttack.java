package game.model.component;

import game.model.IMap;
import game.model.Player;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

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
		super(100, new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, new int[2]));
	}
	
}
