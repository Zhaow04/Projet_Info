package game.model;


import game.utilities.ImageSettings;
import game.view.Observer;

/**
 * A class implements {@code Observable} when its instances are physical entities present on the map.
 * 
 * @author ZhaoWen
 *
 */
public interface Observable {
	
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
	
	void addObserver(Observer o);
	
	void notifyObservers();
	
	void notifyObservers(Object arg);
}
