package game.model;

/**
 * Extends from {@code ViewableObject} <br/>
 * Special environment with high level monsters and a boss.
 * 
 * @author ZhaoWen
 * @see {@link ViewableObject}
 *
 */
public class Dungeon extends ViewableObject {

	//****************************** Attributes ******************************

	private Map currentMap;
	private int[] position; // w/o initialization

	//****************************** Constructor ******************************

	/**
	 * Creates a dungeon on the current map.
	 * 
	 * @param map
	 * @see {@link ViewableObject}
	 */
	public Dungeon (Map map, int x, int y){
		setCurrentMap(map);
		setPosition(x,y);

		setImageURL("game/model/safehouse.png");
		setOffsetX(250);
		setOffsetY(180);
		setWidth(170);
		setHeight(170);
	}

	//************************** Getters and Setters **************************

	/**
	 * Gets the map on which the dungeon currently is.
	 * 
	 * @return current map
	 */
	protected Map getCurrentMap(){
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
