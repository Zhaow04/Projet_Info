package game.model;

import game.utilities.ViewSettings;

/**
 * Abstract class that serves as a super class for all the components of the map.
 *
 */
public abstract class MapComponent {
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private ViewSettings viewSettings;
	
	
	//****************************** Constructor ******************************

	/**
	 * Creates a map component and sets its view settings.
	 * @param viewSettings
	 */
	public MapComponent(ViewSettings viewSettings) {
		setViewSettings(viewSettings);
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
	 * Sets the current map on which the component currently is.
	 * 
	 * @param map
	 */
	private void setCurrentMap(Map map) {
		currentMap = map;
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
	
	/**
	 * Sets the view settings.
	 * @param viewSettings
	 */
	private void setViewSettings(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}
	
	
	//******************************** Methods ********************************
	
	/**
	 * Adds the component to the given map.
	 * @param map
	 * @param x
	 * @param y
	 */
	public void addToMap(Map map, int x, int y) {
		setCurrentMap(map);
		setPosition(x, y);
	}
	
	/**
	 * Removes the component from the map.
	 */
	public void removeFromMap() {
		setCurrentMap(null);
		setPosition(-1, -1);
	}
	
}
