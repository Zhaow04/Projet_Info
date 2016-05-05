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
	
	private static final long serialVersionUID = 1L;
	
	private int lapse;
	private int count;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a damage over time attack.
	 */
	public Dot(int damage, int range, ViewSettings viewSettings,long releaseTime, int lapse, int count){
		super(damage, range, viewSettings, releaseTime);
		this.lapse = lapse;
		this.count = count;
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the lapse between two damages.
	 * 
	 * @return lapse
	 */
	protected int getLapse() {
		return lapse;
	}

	/**
	 * Gets the total number of periodic damages.
	 * 
	 * @return count
	 */
	protected int getCount() {
		return count;
	}
	
}
