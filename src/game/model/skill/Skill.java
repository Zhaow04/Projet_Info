package game.model.skill;

import game.utilities.ViewSettings;

/**
 * Abstract class that serves as a super class for all the skills.
 * 
 */
public abstract class Skill {
	
	//****************************** Attributes ******************************
	
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
	 * @param relaseTime
	 */
	public Skill(int damage, int range, ViewSettings viewSettings){
		setDamage(damage);
		setRange(range);
		setViewSettings(viewSettings);
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
	private void setDamage(int damage) {
		this.damage = damage;
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
	 * Gets the range of the skill.
	 * 
	 * @param range
	 */
	private void setRange(int range) {
		this.range = range;
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
	 * Sets the starting position of the skill.
	 * @param startX
	 * @param startY
	 */
	protected void setStartPos(int startX, int startY) {
		getViewSettings().setStartPos(startX, startY);
	}
	
	/**
	 * Gets the view settings of the skill.
	 * @return viewSettings
	 */
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	/**
	 * Sets the view settings of the skill.
	 * @param viewSettings
	 */
	private void setViewSettings(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}
	
	/**
	 * Sets the time of the last execution of the skill.
	 * @param executionTime
	 */
	protected void setLastExecutionTime(long executionTime){
		this.lastExecutionTime = executionTime;
	}
	
	/**
	 * Sets the release time of the skill.
	 * @param releaseTime
	 */
	protected void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
		
	//******************************** Methods ********************************
	
	/**
	 * Returns whether or not the skill can be used.
	 * @param user
	 * @return boolean
	 */
	public abstract boolean usable(SkillUser user);
	
	/**
	 * Makes the skill user use the skill.
	 * @param user
	 */
	public abstract void use(SkillUser user);
	
	/**
	 * Makes the user (player) gain the killXp of the monster.
	 * @param user
	 */
	protected void gainKillXp(SkillUser user){
		int killXp = getTarget().getStats().getKillXp();
		user.getStats().gainXp(killXp);
	}
	
	/**
	 * Returns whether or not the release time has been respected before using the skill another time.
	 * @return boolean
	 */
	protected boolean timingIsOk(){
		return System.currentTimeMillis()- this.lastExecutionTime >= this.releaseTime ;
	}

}
