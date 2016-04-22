package game.model.mapcomponent;

import game.model.Map;
import game.utilities.ImageDB;

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
		super(ImageDB.getRockView());
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	
}
