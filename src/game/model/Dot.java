package game.model;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the damage over time attacks.
 * 
 * @author ZhaoWen
 * @see {@link Skill}
 *
 */
public class Dot extends Skill {
	
	//****************************** Attributes ******************************
	
	private int dot;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an damage over time attack. Currently void.
	 */
	public Dot(){
		super();
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the damage dealt over time.
	 * 
	 * @return damage dealt over time
	 */
	public int getDot() {
		return dot;
	}
	
	/**
	 * Sets the damage dealt over time.
	 * 
	 * @param dot
	 */
	protected void setDot(int dot) {
		this.dot = dot;
	}
	
}
