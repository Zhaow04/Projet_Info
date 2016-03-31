package game.model;

/**
 * Extends from {@code Item}. <br/>
 * Abstract class that serves as a super class to all the potions.
 * 
 * @author ZhaoWen
 * @see {@link Item}
 * 
 */
public abstract class Potion extends Item {
	
	//****************************** Attributes ******************************
	
	private int value;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a potion with a value of 200 and 3 uses. Sets the map on which it is.
	 * 
	 * @param value
	 */
	public Potion(Map map, int value) {
		super(map);
		setNumberOfUse(3);
		this.value = 200;
	}
	
	/**
	 * Creates a potion with a value of 200 and 3 uses.
	 * 
	 * @param value
	 */
	public Potion(int value){
		super();
		setNumberOfUse(3);
		setValue(200);
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
