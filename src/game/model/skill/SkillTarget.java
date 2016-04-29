package game.model.skill;

import game.model.Stats;

/**
 * A class implements {@code SkillTarget} when the object can be the target of a skill.
 *
 */
public interface SkillTarget {
	
	/**
	 * Gets the stats.
	 * @return stats
	 */
	Stats getStats();
	
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
	
}
