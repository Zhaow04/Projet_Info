package game.model.item;

import java.util.ArrayList;

import game.model.MapComponent;
import game.model.Observable;
import game.model.Player;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Public abstract class that serves as a super class for all the items.
 * Implements {@code Observable}. <br/>
 * Extends from {@code MapComponent} <br/>
 *
 */
public abstract class Item extends MapComponent implements Observable {
	
	//****************************** Attributes ******************************

	private int numberOfUse;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************

	public Item(ViewSettings viewSettings, int numberOfUse) {
		super(viewSettings);
		setNumberOfUse(numberOfUse);
	}
	
	//************************** Getters and Setters **************************

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
	 * Diminishes the number of use by 1.
	 */
	public void useOnce(){
		numberOfUse --;
	}

	/**
	 * Makes the player use the item.
	 */
	public abstract void use(Player player);

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
