package game.model.skill;

import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the area attacks.
 * 
 * @see {@link Skill}
 *
 */
public abstract class Aoe extends Skill {
	
	//****************************** Attributes ******************************
	
	private int radius;
	private int userX, userY;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an area damage attack.
	 */
	public Aoe(int damage, int range, ViewSettings viewSettings,long releaseTime, int radius){
		super(damage, range, viewSettings, releaseTime);
		setRadius(radius);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the radius of the attack.
	 * 
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Sets the radius of the attack.
	 * 
	 * @param radius
	 */
	private void setRadius(int radius) {
		this.radius = radius;
	}
	
	protected int getUserX() {
		return userX;
	}

	protected void setUserX(int userX) {
		this.userX = userX;
	}

	protected int getUserY() {
		return userY;
	}

	protected void setUserY(int userY) {
		this.userY = userY;
	}
	
}
