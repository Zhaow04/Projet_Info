package game.model;

import game.utilities.ViewSettings;

public abstract class MapComponent {
	
	private IMap currentMap;
	
	private ViewSettings viewSettings;
	
	public MapComponent(ViewSettings viewSettings) {
		setViewSettings(viewSettings);
	}
	
	/**
	 * Gets the map on which the viewable currently is.
	 * 
	 * @return current map
	 */
	public IMap getCurrentMap(){
		return currentMap;
	}
	
	/**
	 * Sets the map on which the living being currently is.
	 * 
	 * @param map
	 */
	private void setCurrentMap(IMap map) {
		currentMap = map;
	}

	public int getX() {
		return getViewSettings().getX();
	}
	
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
	
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	private void setViewSettings(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}
	
	public void addToMap(IMap map, int x, int y) {
		setCurrentMap(map);
		setPosition(x, y);
	}
	
	public void removeFromMap() {
		setCurrentMap(null);
		setPosition(-1, -1);
	}
	
}
