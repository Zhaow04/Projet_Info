package game.model;

/**
 * Abstract class that serves as a super class to all the obstacles.
 * 
 * @author ZhaoWen
 *
 */
public abstract class Obstacle extends ViewableObject{
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private int[] position; // w/o initialization
	
	//****************************** Constructor ******************************
	
		/**
		 * Creates an obstacle on the current map.
		 * 
		 * @param map
		 * @see {@link ViewableObject}
		 */
		public Obstacle(Map map){
			setCurrentMap(map);
		}
		
	//************************** Getters and Setters **************************
		
		/**
		 * Gets the map on which the obstacle currently is.
		 * 
		 * @return current map
		 */
		protected Map getCurrentMap(){
			return currentMap;
		}
		
		/**
		 * Sets the map on which the obstacle currently is.
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
			currentMap.addObstacleOnMap(this);
		}
		
		/**
		 * Sets the position (x,y) to empty (no obstacle).
		 * 
		 * @param x
		 * @param y
		 */
		public void emptyPosition(int x, int y){
			getCurrentMap().removeObstacleOnMap(x, y);
			getCurrentMap().setEmpty(x,y);
		}
}


