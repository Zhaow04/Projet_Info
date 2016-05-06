package game.model;


import game.view.Observer;

/**
 * A class implements {@code Observable} when its instances are physical entities present on the map.
 * 
 *
 */
public interface Observable {
	
	/**
	 * Adds an observer.
	 * @param observer
	 */
	void addObserver(Observer observer);
	
	/**
	 * Notifies the observers with an object given as an argument.
	 * @param arg
	 */
	void notifyObservers(Object arg);
	
	/**
	 * Notifies the oserver with a null argument.
	 */
	void notifyObservers();
	
}
