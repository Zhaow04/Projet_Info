package game.model;

/**
 * A class implements {@code Movable} when the object can move.
 * 
 *
 */
public interface Movable {
	
	/**
	 * Gets the map on which the map component currently is.
	 * 
	 * @return current map
	 */
	Map getCurrentMap();
	
	/**
	 * Gets the x position.
	 * @return x position
	 */
	int getX();
	
	/**
	 * Gets the y position.
	 * @return y position
	 */
	int getY();
	
	/**
	 * Sets the position (x,y).
	 */
	void setPosition(int x, int y);
	
	/**
	 * Sets the direction the living being is facing.
	 * 
	 * @param directionFacing
	 */
	void setDirectionFacing(int x, int y);
	
	/**
	 * Notifies the observers with an object given as an argument.
	 * @param arg
	 */
	void notifyObservers(Object arg);
	
	/**
	 * Notifies the observer with a null argument.
	 */
	void notifyObservers();

}
