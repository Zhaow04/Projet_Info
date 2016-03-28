package game.model;

/**
 * Extends from {@code ViewableObject} <br/>
 * Special environment with high level monsters and a boss.
 * 
 * @author ZhaoWen
 * @see {@link ViewableObject}
 *
 */
public class Donjon extends ViewableObject {

	//****************************** Attributes ******************************

	private Map currentMap;
	private int[] position; // w/o initialization

	//****************************** Constructor ******************************

	/**
	 * Creates a donjon on the current map.
	 * 
	 * @param map
	 * @see {@link ViewableObject}
	 */
	public Donjon (Map map, int x, int y){
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
	 * Gets the map on which the donjon currently is.
	 * 
	 * @return current map
	 */
	protected Map getCurrentMap(){
		return currentMap;
	}

	/**
	 * Sets the map on which the donjon currently is.
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
		currentMap.addDonjonOnMap(this);
	}

	/**
	 * Sets the position (x,y) to empty (no donjon).
	 * 
	 * @param x
	 * @param y
	 */
	public void emptyPosition(int x, int y){
		getCurrentMap().removeDonjonOnMap(x, y);
		getCurrentMap().setEmpty(x,y);
	}
}
