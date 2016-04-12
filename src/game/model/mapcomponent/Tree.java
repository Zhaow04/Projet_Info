package game.model.mapcomponent;

import game.model.component.ViewSettings;

public class Tree extends Obstacle{

	//****************************** Attributes ******************************
	
	
	//****************************** Constructor ******************************

	/**
	 * Creates a rock and sets the map on which it is and its position. Creates the view.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Obstacle#Obstacle(Map)}
	 */
	public Tree () {
		super(new ViewSettings("game/utilities/treecomp.PNG", 0, 65, 63, 63, new int[2]));
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
