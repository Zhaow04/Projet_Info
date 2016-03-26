package game.model;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the direct attacks (range 1).
 * 
 * @author ZhaoWen
 * @see {@link Skill}
 *
 */
public abstract class DirectAttack extends Skill {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a direct attack (range 1).
	 */
	public DirectAttack(){
		super();
		setRange(1);
	}
	
}
