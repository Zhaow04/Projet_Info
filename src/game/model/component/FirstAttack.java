package game.model.component;

import game.model.IMap;
import game.utilities.Vector2D;

/**
 * Extends from {@code DirectAttack}. <br/>
 * First attack of the player.
 * 
 * @author ZhaoWen
 * @see {@link DirectAttack}
 *
 */
public class FirstAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public FirstAttack() {
		super(100, new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, new int[2]));
	}
	
	public void preUse(SkillUser user) {
		IMap map = user.getCurrentMap();
		Vector2D direction = user.getDirectionFacing();
		Vector2D targetPos = direction.plus(user.getX(), user.getY());
		setTarget(map.getTargetAt(targetPos.getIntX(), targetPos.getIntY()));
	}
	
	public boolean usable() {
		return getTarget() != null;
	}
	
	@Override
	public void use(SkillUser user) {
		if(usable()) {
			Stats targetStats = getTarget().getStats();
			targetStats.loseHp(getDamage());
		}
	}

	@Override
	public void notifyAnimationEnd() {
		// TODO Auto-generated method stub
		
	}
	
}
