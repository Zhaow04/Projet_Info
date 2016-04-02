package game.model;
import java.util.ArrayList;

/**
 * Inventory of the player.
 * 
 * @author ZhaoWen
 *
 */
public class Inventory {
	
	//****************************** Attributes ******************************
	
	private ArrayList<Item> listItem;
	
	//****************************** Constructor ******************************
	/**
	 * Creates an inventory with 10 slots.
	 * 
	 */
	public Inventory(){
		setSpace(10);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the max number of slots.
	 * 
	 * @return max number of slots
	 */
	public int getSpace() {
		return listItem.size();
	}
	
	/**
	 * Sets the max number of slots.
	 * 
	 * @param space
	 */
	private void setSpace(int space) {
		listItem = new ArrayList<Item>(space);
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item
	 */
	public void addItem(Item item){
		listItem.add(item);
		item.setInventory(this);
	}
	
	/**
	 * Removes an item from the inventory.
	 * 
	 * @param item
	 */
	public void removeItem(Item item){
		listItem.remove(item);
	}
	
	/**
	 * Gets the item located at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 * @return item
	 */
	public Item getItem(int itemNumber){
		return listItem.get(itemNumber);
	}
	
	public int getItemNumber(Item item) {
		return listItem.indexOf(item);
	}

}
