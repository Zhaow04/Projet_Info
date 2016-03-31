package game.model;

import game.utilities.ImageSettings;

/**
 * A class implements {@code Viewable} when it needs an image to be displayed on screen
 * 
 * @author ZhaoWen
 *
 */
public interface Viewable {
	
	/**
	 * Gets the {@code ImageSettings} associated with the object.
	 * 
	 * @return {@code ImageSettings}
	 * @see {@link ImageSettings}
	 * 
	 */
	public ImageSettings getImageSettings();
	
	/**
	 * Gets the position (x,y).
	 * 
	 * @return position
	 */
	public int[] getPosition();
	
	/**
	 * Gets the map on which the viewable currently is.
	 * 
	 * @return current map
	 */
	public Map getCurrentMap();
	
}
