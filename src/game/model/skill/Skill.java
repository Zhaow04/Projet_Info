package game.model.skill;

import java.io.Serializable;

import game.utilities.ViewSettings;

/**
 * Implements {@link Serializable}. <br/>
 * Abstract class that serves as a super class for all the skills.
 * 
 */
public abstract class Skill implements Serializable {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	
	private int damage;
	private int range;
	private SkillTarget target;
	private long lastExecutionTime;
	private long releaseTime;

	private ViewSettings viewSettings;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a skill, sets its damage, range, and viewSettings.
	 * @param damage
	 * @param range
	 * @param viewSettings
	 */
	public Skill(int damage, int range, ViewSettings viewSettings, long releaseTime) {
		this.damage = damage;
		this.range = range;
		this.viewSettings = viewSettings;
		this.releaseTime = releaseTime;
	}
	
	//************************** Getters and Setters **************************

	/**
	 * Gets the damage dealt by the skill.
	 * 
	 * @return damage dealt
	 */
	protected int getDamage() {
		return damage;
	}

	/**
	 * Gets the range of the skill.
	 * 
	 * @return range
	 */
	protected int getRange() {
		return range;
	}
	
	/**
	 * Gets the target of the skill.
	 * @return skill target
	 */
	protected SkillTarget getTarget() {
		return target;
	}

	/**
	 * Sets the target of the skill.
	 * @param skillTarget
	 */
	protected void setTarget(SkillTarget target) {
		this.target = target;
	}
	
	/**
	 * Gets the X position where the skill will take place.
	 * @return x
	 */
	protected int getX() {
		return getViewSettings().getX();
	}
	/**
	 * Gets the Y position where the skill will take place.
	 * @return y
	 */
	protected int getY() {
		return getViewSettings().getY();
	}

	/**
	 * Sets the position where the skill will take place.
	 * @param x
	 * @param y
	 */
	protected void setPosition(int x, int y) {
		getViewSettings().setPosition(x, y);
	}
	
	/**
	 * Gets the view settings of the skill.
	 * @return viewSettings
	 */
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	/**
	 * Sets the time of the last execution of the skill.
	 * @param executionTime
	 */
	protected void setLastExecutionTime(long executionTime){
		this.lastExecutionTime = executionTime;
	}
		
	//******************************** Methods ********************************
	
	/**
	 * Returns whether or not the skill can be used.
	 * @param user
	 * @return boolean
	 */
	protected abstract boolean usable(SkillUser user);
	
	/**
	 * Makes the skill user use the skill.
	 * @param user
	 */
	public abstract void use(SkillUser user);
	
	/**
	 * Makes the user gain the Xp of the target.
	 * @param user
	 */
	protected void gainKillXp(SkillUser user){
		int killXp = getTarget().getXp();
		user.gainXp(killXp);
	}
	
	/**
	 * Returns whether or not the release time has been respected before using the skill another time.
	 * @return boolean
	 */
	protected boolean timingIsOk(){
		return System.currentTimeMillis()- this.lastExecutionTime >= this.releaseTime ;
	}

}
