package game.model;

import java.io.Serializable;

import game.utilities.ViewSettings;

/**
 * Implements {@link Serializable}. <br/>
 * Abstract class that serves as a super class for all the components of the map.
 *
 */
public abstract class MapComponent implements Serializable {
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map currentMap;
	private ViewSettings viewSettings;
	
	
	//****************************** Constructor ******************************

	/**
	 * Creates a map component and sets its view settings.
	 * @param viewSettings
	 */
	public MapComponent(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}
	
	//************************** Getters and Setters **************************

	/**
	 * Gets the current map of the component.
	 * @return Map
	 */
	public Map getCurrentMap(){
		return currentMap;
	}

	/**
	 * Gets the X position of the map component.
	 * @return x
	 */
	public int getX() {
		return getViewSettings().getX();
	}
	
	/**
	 * Gets the Y position of the map component.
	 * @return y
	 */
	public int getY() {
		return getViewSettings().getY();
	}
	
	/**
	 * Sets the position to (x,y).
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		getViewSettings().setPosition(x, y);
	}
	
	/**
	 * Gets the view settings.
	 * @return viewSettings
	 */
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Adds the component to the given map.
	 * @param map
	 * @param x
	 * @param y
	 */
	public void addToMap(Map map, int x, int y) {
		currentMap = map;
		setPosition(x, y);
	}
	
	/**
	 * Removes the component from the map.
	 */
	public void removeFromMap() {
		currentMap = null;
		setPosition(-1, -1);
	}
	
}
