package game.model;

/**
 * Implements {@code Usable}. <br/>
 * Abstract class that serves as a super class for all the skills.
 * 
 * @author ZhaoWen
 * @see {@link Usable}
 *
 */
public abstract class Skill implements Usable {
	
	//****************************** Attributes ******************************
	
	private int damage;
	private int range;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a skill. Currently void.
	 */
	public Skill(){
		
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the damage dealt by the skill.
	 * 
	 * @return damage dealt
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Sets the damage dealt by the skill.
	 * 
	 * @param damage
	 */
	protected void setDamage(int damage) {
		this.damage = damage;
	}
	/**
	 * Gets the range of the skill.
	 * 
	 * @return
	 */
	public int getRange() {
		return range;
	}
	
	/**
	 * Gets the range of the skill.
	 * 
	 * @param range
	 */
	protected void setRange(int range) {
		this.range = range;
	}
	
	//******************************** Methods ********************************
	
	// Still needs implementation
	@Override
	public void use(LivingBeing target){
		int damage = getDamage();
		target.loseHp(damage);
		}
	
}
