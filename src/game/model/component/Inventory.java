package game.model.component;
import java.util.ArrayList;

import game.model.IMap;
import game.model.Observable;
import game.model.Player;
import game.model.item.IItem;
import game.model.item.Item;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Inventory of the player.
 * 
 * @author ZhaoWen
 *
 */
public class Inventory implements Observable {
	
	//****************************** Attributes ******************************
	
	private Player owner;
	private ArrayList<IItem> listItem;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	/**
	 * Creates an inventory with 10 slots.
	 * 
	 */
	public Inventory(Player owner) {
		setOwner(owner);
		setSpace(10);
	}
	
	//************************** Getters and Setters **************************
	
	public Player getOwner() {
		return owner;
	}

	private void setOwner(Player owner) {
		this.owner = owner;
	}
	
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
		listItem = new ArrayList<IItem>(space);
		for(int i = 0; i < space; i++) {
			listItem.add(null);
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
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	@Override
	public ViewSettings getViewSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToMap(IMap map, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item
	 */
	public void addItem(IItem item) {
		for(int i = 0; i < getSpace(); i++) {
			if(listItem.get(i) == null) {
				listItem.set(i, item);
				break;
			}
		}
		//item.setInventory(this);
	}
	
	/**
	 * Removes an item from the inventory.
	 * 
	 * @param item
	 */
	public void removeItem(Item item){
		listItem.remove(item);
	}
	
	public void removeItem(int index) {
		listItem.set(index, null);
	}
	
	/**
	 * Use an item located in the inventory at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 */
	public void useItem(int itemNumber){
		getItem(itemNumber).use(getOwner());
	}
	
	public void throwItem(int index) {
		removeItem(index);
	}
	
	/**
	 * Gets the item located at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 * @return item
	 */
	public IItem getItem(int itemNumber){
		return listItem.get(itemNumber);
	}
	
	public int getItemNumber(IItem item) {
		return listItem.indexOf(item);
	}

}
