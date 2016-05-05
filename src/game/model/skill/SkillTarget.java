package game.model.skill;

/**
 * A class implements {@code SkillTarget} when the object can be the target of a {@link Skill}.
 *
 */
public interface SkillTarget {
	
	/**
	 * Gets the X position.
	 * @return x
	 */
	int getX();
	
	/**
	 * Gets the Y position.
	 * @return y
	 */
	int getY();
	
	/**
	 * Makes the skill target lose HP.
	 * @param hp
	 */
	void loseHp(int hp);
	
	/**
	 * Returns whether or not the target is dead.
	 * @return boolean
	 */
	boolean isDead();
	
	/**
	 * Gets the xp of the target.
	 * @return xp
	 */
	int getXp();
	
}
