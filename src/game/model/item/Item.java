package game.model.item;

import java.util.ArrayList;

import game.model.MapComponent;
import game.model.Observable;
import game.model.Player;
import game.model.component.Inventory;
import game.model.component.ViewSettings;
import game.view.Observer;

/**
 * A class implements {@code Item} when it can be used by another class. Example : item
 * used by the player.
 * Implements {@code Item}. <br/>
 * Extends from {@code ViewSettings} <br/>
 * Abstract class that serves as a super class for all the items.
 * 
 * @author ZhaoWen
 *
 */
public abstract class Item extends MapComponent implements IItem, Observable {
	
	private int numberOfUse;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Item(ViewSettings viewSettings, int numberOfUse) {
		super(viewSettings);
		setNumberOfUse(numberOfUse);
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
	private void setNumberOfUse(int numberOfUse) {
		this.numberOfUse = numberOfUse;
	}
	
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

	@Override
	public void pickUp(Player player) {
		
	}

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
	
}
