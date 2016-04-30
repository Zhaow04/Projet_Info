package game.model.item;

import game.model.Player;
import game.utilities.ImageDB;

/**
 * Extends from {@code Potion}. <br/>
 * Hp potion that heals the user.
 * 
 * @see {@link Potion}
 *
 */
public final class HpPotion extends Potion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		player.addHp(getValue());
	}

}
