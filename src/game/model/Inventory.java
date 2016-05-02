package game.model;

import java.io.Serializable;
import java.util.ArrayList;

import game.model.item.Item;
import game.view.Observer;

/**
 * Implements {@link Serializable}. <br/>
 * Inventory of the player.
 * 
 *
 */
public class Inventory implements Observable, Serializable {
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player owner;
	private ArrayList<Item> items;
	
	private transient ArrayList<Observer> observers;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an inventory with 10 slots.
	 * 
	 */
	public Inventory(Player owner) {
		this.owner = owner;
		setSpace(10);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the owner of the inventory.
	 * @return
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Gets a new {@code ArrayList<Item>} which is a copy of the list of items. Ensure that the list of items
	 * is not altered by an unknown source.
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}

	/**
	 * Gets the max number of slots.
	 * 
	 * @return max number of slots
	 */
	public int getSpace() {
		return items.size();
	}

	/**
	 * Sets the max number of slots.
	 * 
	 * @param space
	 */
	private void setSpace(int space) {
		items = new ArrayList<Item>(space);
		for(int i = 0; i < space; i++) {
			items.add(null);
		}
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public void addObserver(Observer o) {
		if(observers == null)
			observers = new ArrayList<Observer>();
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		for(int i = 0; i < getSpace(); i++) {
			if(items.get(i) == null) {
				items.set(i, item);
				notifyObservers();
				break;
			}
		}
	}
	
	/**
	 * Removes an item from the inventory.
	 * 
	 * @param item
	 */
	public void removeItem(Item item){
		items.remove(item);
	}
	
	/**
	 * Removes an item referenced by its index from the inventory.
	 * @param index
	 */
	public void removeItem(int index) {
		items.set(index, null);
	}
	
	/**
	 * Use an item located in the inventory at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 */
	public void useItem(int itemNumber){
		getItem(itemNumber).use(getOwner());
	}
	
	/**
	 * Removes an item referenced by its index from the inventory.
	 * @param index
	 */
	public void throwItem(int index) {
		removeItem(index);
	}
	
	/**
	 * Gets the item located at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 * @return item
	 */
	public Item getItem(int itemNumber){
		return items.get(itemNumber);
	}
	
	/**
	 * Gets the item number (index) of the given item.
	 * @param item
	 * @return itemNumber
	 */
	public int getItemNumber(Item item) {
		return items.indexOf(item);
	}

}
