package game.model.skill;

import game.model.Map;
import game.model.Stats;
import game.utilities.Vector2D;

/**
 * A class implements {@code SkillUser} when the object can use a skill against a target.
 * 
 *
 */
public interface SkillUser {
	
	/**
	 * Gets the current map.
	 * @return map
	 */
	Map getCurrentMap();
	
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
	 * Gets the direction the skill user is facing.
	 * @return directionFacing
	 */
	Vector2D getDirectionFacing();
	
	/**
	 * Notify all the observers with the object argument given.
	 * @param arg
	 */
	void notifyObservers(Object arg);
	
	/**
	 * Gets the stats.
	 * @return stats
	 */
	Stats getStats();
	
}
