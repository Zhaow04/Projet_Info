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
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a direct attack (range 1).
	 */
	public DirectAttack(int damage, ViewSettings viewSettings){
		super(damage, 1, viewSettings);
	}
	
	//******************************** Methods ********************************

	@Override
	public boolean usable(SkillUser user) {
		return getTarget() != null;
	}
	
	
	@Override
	public void use(SkillUser user) {
		Map map = user.getCurrentMap();
		Vector2D direction = user.getDirectionFacing();
		Vector2D targetPos = direction.plus(user.getX(), user.getY());
		setTarget(map.getTargetAt(targetPos.getIntX(), targetPos.getIntY()));
		if(usable(user)) {
			setStartPos(getTarget().getX(), getTarget().getY());
			setPosition(getTarget().getX(), getTarget().getY());
			user.notifyObservers(this);
			getTarget().loseHp(getDamage());
			if (user instanceof Player && getTarget().getStats().getHp()<=0){
				gainKillXp(user);
				System.out.println(user.getStats().getXp());
				System.out.println(user.getStats().getLevel());
			}

		}
	}
	
}
