package game.model.skill;

import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the damage over time attacks.
 * 
 * @author ZhaoWen
 * @see {@link Skill}
 *
 */
public abstract class Dot extends Skill {
	
	//****************************** Attributes ******************************
	
	private int lapse;
	private int count;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an damage over time attack.
	 */
	public Dot(int damage, int range, ViewSettings viewSettings, int lapse, int count){
		super(damage, range, viewSettings);
		setLapse(lapse);
		setCount(count);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the lapse between two damages.
	 * 
	 * @return damage dealt over time
	 */
	public int getLapse() {
		return lapse;
	}
	
	/**
	 * Sets the lapse between two damages.
	 * 
	 * @param dot
	 */
	private void setLapse(int dt) {
		this.lapse = dt;
	}
	
	

	/**
	 * Gets the total number of periodic damages.
	 * 
	 * @return damage dealt over time
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the total number of periodic damages.
	 * 
	 */
	protected void setCount(int t) {
		this.count = t;
	}
	
}
