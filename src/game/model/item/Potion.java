package game.model.item;

import game.utilities.ViewSettings;

/**
 * Extends from {@code Item}. <br/>
 * Abstract class that serves as a super class to all the potions.
 * 
 * @see {@link Item}
 * 
 */
public abstract class Potion extends Item {
	
	//****************************** Attributes ******************************
	
	private int value;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a potion with a value, usable 1 time.
	 * 
	 * @param value
	 */
	public Potion(ViewSettings viewSettings, int value) {
		super(viewSettings, 1);
		setValue(value);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the value of the potion.
	 * 
	 * @return value of potion
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Sets the value of the potion.
	 * 
	 * @param value
	 */
	private void setValue(int value) {
		this.value = value;
	}

}
