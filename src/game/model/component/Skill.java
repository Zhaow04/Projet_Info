package game.model.component;

import game.model.item.Item;

/**
 * Implements {@code Item}. <br/>
 * Abstract class that serves as a super class for all the skills.
 * 
 * @author ZhaoWen
 * @see {@link Item}
 *
 */
public abstract class Skill implements ISkill {
	
	//****************************** Attributes ******************************
	
	private int damage;
	private int range;
	
	private ViewSettings viewSettings;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a skill. Currently void.
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
	 * @return
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
	
	protected int getX() {
		return getViewSettings().getX();
	}
	
	protected int getY() {
		return getViewSettings().getY();
	}

	protected void setPosition(int x, int y) {
		getViewSettings().setPosition(x, y);
	}
	
	protected void setStartPos(int startX, int startY) {
		getViewSettings().setStartPos(startX, startY);
	}
	
	@Override
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	private void setViewSettings(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}
	
	//******************************** Methods ********************************
	
	public abstract void use(SkillUser user);

}
