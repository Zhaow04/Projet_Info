package game.model.monster;

import game.model.Map;
import game.model.component.Stats;
import game.utilities.ImageDB;

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
	public OrangeBat(int level){
		super(ImageDB.getOrangeBatView());
		setScope(2);
		setStats(new Stats(400, 50, level));
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
