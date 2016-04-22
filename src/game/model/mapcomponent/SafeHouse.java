package game.model.mapcomponent;

import game.model.MapComponent;
import game.utilities.ImageDB;
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
		super(ImageDB.getSafeHouseView());
	}

	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	
}
