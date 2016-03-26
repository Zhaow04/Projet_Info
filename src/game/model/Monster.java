package game.model;

/**
 * Extends from {@code LivingBeing} <br/>
 * Abstract class that serves as a super class for all the different monsters.
 * 
 * @author ZhaoWen
 * @see {@link LivingBeing}
 *
 */
public abstract class Monster extends LivingBeing {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Monster(Map map){
		super(map);
	}

}
