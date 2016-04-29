package game.model.skill;

import game.model.Map;
import game.model.Player;
import game.utilities.ImageDB;
import game.utilities.Vector2D;

/**
 * Extends {@code Aoe}. <br/>
 * A fire explosion, long range attack with area damage and a release time.
 * @see {@link Aoe} 
 */
public class FireExplosion extends Aoe {
	
	//****************************** Constructor ******************************

	/**
	 * Creates a fire explosion attack (range: 3 + area damage: 100) and sets its release time (5sec).
	 */
	public FireExplosion() {
		super(100, 3, ImageDB.getFireView(),5000, 3);
		//setReleaseTime(5000);
	}

	//******************************** Methods ********************************
	
	@Override
	public boolean usable(SkillUser user) {
		setTarget(null);
		if (timingIsOk()){
			Map map = user.getCurrentMap();
			Vector2D direction = user.getDirectionFacing();
			Vector2D targetPos = new Vector2D(user.getX(), user.getY());
			int a = 1;
			while(a <= getRange()) {
				targetPos.add(direction);
				if(map.isTargetAt(targetPos.getIntX(), targetPos.getIntY())) {
					setTarget(map.getTargetAt(targetPos.getIntX(), targetPos.getIntY()));
					break;
				}
				a++;
			}
		}
		return (getTarget()!=null); 
	}

	
	@Override
	public void use(SkillUser user) {
		if(usable(user)) {
			Map map = user.getCurrentMap();
			int targetX = getTarget().getX();
			int targetY = getTarget().getY();
			setStartPos(getTarget().getX()-1, getTarget().getY()-1);
			setPosition(getTarget().getX()-1, getTarget().getY()-1);
			user.notifyObservers(this);
			for(int i = -(getRadius()-2); i <= getRadius()-2; i++) {
				for(int j = -(getRadius()-2); j <= getRadius()-2; j++) {
					if(map.isTargetAt(targetX + j, targetY + i)){
						map.getTargetAt(targetX + j, targetY + i).loseHp(getDamage());
						if (user instanceof Player && getTarget().getStats().getHp()<=0){
							gainKillXp(user);
							System.out.println(user.getStats().getXp());
							System.out.println(user.getStats().getLevel());
						}
					}
				}
			}
			setLastExecutionTime (System.currentTimeMillis());
		}
	}
	
}
