package game.model.item;

import game.model.Player;
import game.utilities.ImageDB;

/**
 * Extends from {@code Potion}. <br/>
 * Hp potion that heals the user.
 * 
 * @author ZhaoWen
 *
 */
public class HpPotion extends Potion {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a hp potion with a value defined by {@code value}. Sets the map on which it is.
	 * 
	 * @param value
	 */
	public HpPotion(int value) {
		super(ImageDB.getHpPotionView(),value);
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Uses the object on the target.
	 * 
	 * @param player
	 */
	@Override
	public void use(Player player) {
		useOnce();
		player.addHp(getValue());
	}

}
