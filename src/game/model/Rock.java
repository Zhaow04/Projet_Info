package game.model;

public class Rock extends Obstacle{
	
	//****************************** Attributes ******************************
	
	
		//****************************** Constructor ******************************
		
		/**
		 * Creates a rock and sets the map on which it is and its position.
		 * 
		 * @param map
		 * @param x
		 * @param y
		 * @see {@link Obstacle#Obstacle(Map)}
		 */
		public Rock(Map map, int x, int y){
			super(map);
			setPosition(x,y);
			
			setImageURL("game/model/forest.png");
			setOffsetX(0);
			setOffsetY(0);
			setWidth(60);
			setHeight(60);
		}
		
		//************************** Getters and Setters **************************
		
		//******************************** Methods ********************************
	
}
