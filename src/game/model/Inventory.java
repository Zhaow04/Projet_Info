package game.model;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Inventory of the player.
 * 
 * @author ZhaoWen
 *
 */
public class Inventory extends Observable {
	
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
		setChanged();
		notifyObservers(listItem.indexOf(item));
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

}
