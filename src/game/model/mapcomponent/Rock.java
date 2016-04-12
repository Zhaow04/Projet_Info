package game.model.mapcomponent;

import game.model.component.ViewSettings;

public class Rock extends Obstacle{

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
	public Rock(){
		super(new ViewSettings("game/utilities/forest.png", 0, 0, 60, 60, new int[2]));
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
