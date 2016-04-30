package game.model.item;

import java.util.ArrayList;

import game.model.MapComponent;
import game.model.Observable;
import game.model.Player;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Implements {@code Observable}. <br/>
 * Extends from {@code MapComponent}. <br/>
 * Public abstract class that serves as a super class for all the items.
 * 
 * @see {@link Observable}
 * @see {@link MapComponent}
 *
 */
public abstract class Item extends MapComponent implements Observable {
	
	//****************************** Attributes ******************************

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numberOfUse;
	
	private transient ArrayList<Observer> observers;
	
	//****************************** Constructor ******************************

	public Item(ViewSettings viewSettings, int numberOfUse) {
		super(viewSettings);
		this.numberOfUse = numberOfUse;
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
	
	//******************************** Methods ********************************

	/**
	 * Adds a number of use to the item.
	 * 
	 * @param value
	 */
	protected void addNumberOfUse(int value){
		this.numberOfUse += value;
	}
	
	/**
	 * Diminishes the number of use by 1.
	 */
	protected void useOnce(){
		numberOfUse --;
	}

	/**
	 * Makes the player use the item.
	 */
	public abstract void use(Player player);

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
	
	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}
	
}
