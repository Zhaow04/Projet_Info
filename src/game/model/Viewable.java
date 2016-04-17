package game.model;

import game.utilities.ViewSettings;

/**
 * A class implements {@code Viewable} when it needs an image to be displayed on screen.
 * 
 * @author ZhaoWen
 *
 */
public interface Viewable {
	
	/**
	 * Gets the {@code ViewSettings} associated with the object.
	 * 
	 * @return {@code ViewSettings}
	 * @see {@link ViewSettings}
	 * 
	 */
	public ViewSettings getViewSettings();
	
	public void addToMap(IMap map, int x, int y);
	
}
