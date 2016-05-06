package game.model.item;

import game.model.Player;
import game.utilities.ImageDB;

/**
 * Extends from {@code Item}. <br/>
 * Xp parchment that gives the user more Xp instantly.
 * 
 * @see {@link Item}
 *
 */
public class XpParchment extends Item{
	
    //****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	
	private int xpValue;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a xp parchment with a value.
	 * 
	 * @param value
	 */
	public XpParchment(int value) {
		super(ImageDB.getParchmentView(), 1);
		this.xpValue = value;
	}
	
	
	//******************************** Methods ********************************
	
	
	@Override
	public void use(Player player) {
		useOnce();
		player.gainXp(xpValue);
	}


}
