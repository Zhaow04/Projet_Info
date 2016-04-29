package game.model.item;

import game.model.Player;
import game.utilities.ImageDB;

/**
 * Extends from {@code Potion}. <br/>
 * Hp potion that heals the user.
 * 
 *
 */
public class HpPotion extends Potion {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a hp potion with a value.
	 * 
	 * @param value
	 */
	public HpPotion(int value) {
		super(ImageDB.getHpPotionView(),value);
	}
	
	//******************************** Methods ********************************
	
	
	@Override
	public void use(Player player) {
		useOnce();
		player.getStats().addHp(getValue());
	}

}
