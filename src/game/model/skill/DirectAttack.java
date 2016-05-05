package game.model.skill;

import game.model.Map;
import game.model.Player;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the direct attacks (range 1).
 * 
 * @see {@link Skill}
 *
 */
public abstract class DirectAttack extends Skill {
	
	private static final long serialVersionUID = 1L;
	
	//****************************** Constructor ******************************

	/**
	 * Creates a direct attack (range 1).
	 */
	public DirectAttack(int damage, ViewSettings viewSettings, long releaseTime) {
		super(damage, 1, viewSettings, releaseTime);
	}
	
	//******************************** Methods ********************************

	@Override
	public boolean usable(SkillUser user) {
		return getTarget() != null && timingIsOk();
	}
	
	
	@Override
	public void use(SkillUser user) {
		Map map = user.getCurrentMap();
		Vector2D direction = user.getDirectionFacing();
		Vector2D targetPos = direction.plus(user.getX(), user.getY());
		setTarget(map.getTargetAt(targetPos.getIntX(), targetPos.getIntY()));
		if(usable(user) ) {
			setPosition(getTarget().getX(), getTarget().getY());
			user.notifyObservers(this);
			getTarget().loseHp(getDamage());
			setLastExecutionTime(System.currentTimeMillis());
			if(user instanceof Player && getTarget().isDead()) {
				gainKillXp(user);
			}
		}
	}
	
}
