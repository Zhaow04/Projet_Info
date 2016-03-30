package game.model;

public class Tree extends Obstacle{
	
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
		public Tree (Map map, int x, int y){
			super(map);
			setPosition(x,y);
			
			setImageURL("game/utilities/treecomp.PNG");
			setOffsetX(0);
			setOffsetY(65);
			setWidth(63);
			setHeight(63);
		}
		
		//************************** Getters and Setters **************************
		
		//******************************** Methods ********************************
	
}
