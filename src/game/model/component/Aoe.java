package game.model.component;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the area attacks.
 * 
 * @author ZhaoWen
 * @see {@link Skill}
 *
 */
public abstract class Aoe extends Skill {
	
	//****************************** Attributes ******************************
	
	private int radius;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an area attack. Currently void.
	 */
	public Aoe(int damage, int range, ViewSettings viewSettings, int radius){
		super(damage, range, viewSettings);
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
	
}
