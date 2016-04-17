package game.model.item;

import game.utilities.ViewSettings;

/**
 * Extends from {@code AbstractItem}. <br/>
 * Abstract class that serves as a super class to all the potions.
 * 
 * @author ZhaoWen
 * @see {@link AbstractItem}
 * 
 */
public abstract class Potion extends Item {
	
	//****************************** Attributes ******************************
	
	private int value;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a potion with a value of 200 and 3 uses.
	 * 
	 * @param value
	 */
	public Potion(ViewSettings viewSettings, int value) {
		super(viewSettings, 3);
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
