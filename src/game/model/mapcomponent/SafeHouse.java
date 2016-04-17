package game.model.mapcomponent;

import game.model.MapComponent;
import game.utilities.ViewSettings;

public class SafeHouse extends MapComponent {

	//****************************** Attributes ******************************
	

	//****************************** Constructor ******************************

	/**
	 * Creates a safe house on the current map and creates the view.
	 * 
	 * @param map
	 * @see {@link ViewSettings}
	 */
	public SafeHouse () {
		super(new ViewSettings("game/utilities/safehouse.png", 250, 180, 170, 170, new int[2]));
	}

	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	
}
