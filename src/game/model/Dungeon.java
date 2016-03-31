package game.model;

import game.utilities.ImageSettings;

/**
 * Extends from {@code ImageSettings} <br/>
 * Special environment with high level monsters and a boss.
 * 
 * 
 * @see {@link ImageSettings}
 *
 */
public class Dungeon implements Viewable {

	//****************************** Attributes ******************************

	private Map currentMap;
	private int[] position; // w/o initialization
	
	// we still need a good picture of a dungeon on the map, it needs to take 9 places
	private ImageSettings imageSettings = new ImageSettings("game/model/safehouse.png",0,0,190,190);

	//****************************** Constructor ******************************

	/**
	 * Creates a dungeon on the current map.
	 * 
	 * @param map
	 * @see {@link ImageSettings}
	 */
	public Dungeon (Map map, int x, int y){
		setCurrentMap(map);
		setPosition(x,y);
	}

	//************************** Getters and Setters **************************
	
	@Override
	public Map getCurrentMap(){
		return currentMap;
	}

	/**
	 * Sets the map on which the dungeon currently is.
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
		currentMap.setOccupied(x, y);
	}
	//******************************** Methods ********************************

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
		currentMap.addDungeonOnMap(this);
	}

	/**
	 * Sets the position (x,y) to empty (no dungeon).
	 * 
	 * @param x
	 * @param y
	 */
	public void emptyPosition(int x, int y){
		getCurrentMap().removeDungeonOnMap(x, y);
		getCurrentMap().setEmpty(x,y);
	}
}
