package game.model;

/**
 * Implements {@code Usable}. <br/>
 * Abstract class that serves as a super class for all the items.
 * 
 * @author ZhaoWen
 * @see {@link Usable}
 *
 */
public abstract class Item implements Usable {
	
	//****************************** Attributes ******************************
	
	private int numberOfUse;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an item. Currently void.
	 * 
	 */
	public Item(){
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the remaining number of use.
	 * 
	 * @return remaining number of use
	 */
	private int getNumberOfUse() {
		return numberOfUse;
	}
	
	/**
	 * Sets the number of use.
	 * 
	 * @param numberOfUse
	 */
	protected void setNumberOfUse(int numberOfUse) {
		this.numberOfUse = numberOfUse;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Adds a number of use to the item.
	 * 
	 * @param value
	 */
	protected void addNumberOfUse(int value){
		int x = getNumberOfUse();
		setNumberOfUse(x+value);
	}
	/**
	 * Uses the item once.
	 */
	public void useOnce(){
		numberOfUse --;
	}

}
