package game.model.skill;

import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the damage over time attacks.
 * 
 * @see {@link Skill}
 *
 */
public abstract class Dot extends Skill {
	
	//****************************** Attributes ******************************
	
	private int lapse;
	private int count;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a damage over time attack.
	 */
	public Dot(int damage, int range, ViewSettings viewSettings,long releaseTime, int lapse, int count){
		super(damage, range, viewSettings, releaseTime);
		setLapse(lapse);
		setCount(count);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the lapse between two damages.
	 * 
	 * @return lapse
	 */
	public int getLapse() {
		return lapse;
	}
	
	/**
	 * Sets the lapse between two damages.
	 * 
	 * @param dt
	 */
	private void setLapse(int dt) {
		this.lapse = dt;
	}
	
	

	/**
	 * Gets the total number of periodic damages.
	 * 
	 * @return count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the total number of periodic damages.
	 * @param count
	 */
	protected void setCount(int count) {
		this.count = count;
	}
	
}
