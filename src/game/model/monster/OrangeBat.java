package game.model.monster;

import game.model.component.ViewSettings;

/**
 * Extends from {@code Monster} <br/>
 * Orange bat - enemy.
 * 
 * @see {@link Monster}
 *
 */
public class OrangeBat extends Monster {
	
	//****************************** Attributes ******************************
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an orange bat and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Monster#Monster(Map)}
	 */
	public OrangeBat(){
		super(new ViewSettings("game/model/batman.png", 0, 0, 100, 100, new int[2]));
		setScope(2);
	}
	
	//************************** Getters and Setters **************************
	
	

	//******************************** Methods ********************************
	
	/**
	 * Defines the maximum of Hp this living being can have (depending on his level).
	 * 
	 * return maxHp
	 */
	/*private int maxHp(){
		return 300 + getLevel()*100;  
	}*/
}
