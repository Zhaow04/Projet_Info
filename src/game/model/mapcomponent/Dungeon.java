package game.model.mapcomponent;

import game.model.MapComponent;
import game.model.component.ViewSettings;

/**
 * Extends from {@code ViewSettings} <br/>
 * Special environment with high level monsters and a boss.
 * 
 * 
 * @see {@link ViewSettings}
 *
 */
public class Dungeon extends MapComponent {

	//****************************** Attributes ******************************
	
	// we still need a good picture of a dungeon on the map, it needs to take 9 places

	//****************************** Constructor ******************************

	/**
	 * Creates a dungeon on the current map.
	 * 
	 * @param map
	 * @see {@link ViewSettings}
	 */
	public Dungeon() {
		super(new ViewSettings("game/model/safehouse.png",0,0,190,190, new int[2]));
	}

	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	
}
