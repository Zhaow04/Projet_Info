package game.model;

import java.util.ArrayList;

import game.utilities.ImageSettings;
import game.view.Observer;

/**
 * Implements {@code Usable}. <br/>
 * Extends from {@code ImageSettings} <br/>
 * Abstract class that serves as a super class for all the items.
 * 
 * @author ZhaoWen
 * @see {@link Usable}
 *
 */
public abstract class Item implements Observable, Usable {
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private Inventory inventory;
	private int numberOfUse;
	private int[] position;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an item.
	 * 
	 */
	public Item() {
		this(null);
	}
	
	/**
	 * Creates an item ands sets the map on which it is.
	 * 
	 */
	public Item(Map map) {
		currentMap = map;
	}
	
	//************************** Getters and Setters **************************
	/**
	 * Gets the map on which the obstacle currently is.
	 * 
	 * @return current map
	 */
	public Map getCurrentMap(){
		return currentMap;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		notifyObservers(inventory.getItemNumber(this));
	}

	/**
	 * Gets the remaining number of use.
	 * 
	 * @return remaining number of use
	 */
	public int getNumberOfUse() {
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
	
	/**
	 * Gets the position (x,y).
	 * 
	 * @return position
	 */
	public int[] getPosition(){
		return position;
	}
	/**
	 * Sets the position to (x,y).
	 * 
	 * @param x
	 * @param y
	 */
	protected void setPosition(int x, int y){
		position = new int[2];
		position[0] = x; position[1] = y;
		currentMap.addItemOnMap(this);
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public abstract ImageSettings getImageSettings();

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
