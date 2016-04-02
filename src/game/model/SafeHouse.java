package game.model;

import java.util.ArrayList;

import game.utilities.ImageSettings;
import game.view.Observer;

public class SafeHouse implements Observable {

	//****************************** Attributes ******************************

	private Map currentMap;
	private int[] position; // w/o initialization
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/safehouse.png", 250, 180, 170, 170);
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	//****************************** Constructor ******************************

	/**
	 * Creates a safe house on the current map and creates the view.
	 * 
	 * @param map
	 * @see {@link ImageSettings}
	 */
	public SafeHouse (Map map, int x, int y){
		setCurrentMap(map);
		setPosition(x,y);
	}

	//************************** Getters and Setters **************************

	/**
	 * Gets the map on which the safe house currently is.
	 * 
	 * @return current map
	 */
	public Map getCurrentMap(){
		return currentMap;
	}

	/**
	 * Sets the map on which the safe house currently is.
	 * 
	 * @param map
	 */
	private void setCurrentMap(Map map){
		currentMap = map;
	}
	public int[] getPosition(){
		return position;
	}

	/**
	 * Sets the position to (x,y).
	 * 
	 * @param x
	 * @param y
	 */
	protected void setPosition(int x, int y){
		position = new int[2];
		position[0] = x; position[1] = y;
		currentMap.addObservableOnMap(this);
	}
	//******************************** Methods ********************************

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}

	/**
	 * Sets the position to (x,y). Used when a previous position is defined.
	 * 
	 * @param x
	 * @param y
	 */
	protected void setNewPosition(int x,int y){
		setPosition(x,y);
		currentMap.addObservableOnMap(this);
	}

	/**
	 * Sets the position (x,y) to empty (no safe house).
	 * 
	 * @param x
	 * @param y
	 */
	public void emptyPosition(int x, int y){
		getCurrentMap().removeObservableOnMap(x, y);
	}
}
