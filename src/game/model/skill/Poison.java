package game.model.skill;

import game.model.Map;
import game.utilities.ImageDB;
import game.utilities.Vector2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Extends {@code Dot}. <br/>
 * Poisons the enemy with a damage over time ability + long range attack.
 * 
 * @see {@link Dot}
 */
public final class Poison extends Dot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a long range poison attack with a damage over time ability.
	 */
	public Poison() {
		super(40, 3, ImageDB.getPoisonView(), 20000, 2000, 5);
	}
	
	//******************************** Methods ********************************

	@Override
	public boolean usable(SkillUser user) {
		setTarget(null);
		if(timingIsOk()){
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
				else if(!map.noCollision(targetPos.getIntX(), targetPos.getIntY()))
					break;
				a++;
			}
		}
		return (getTarget() != null);
	}
	
	@Override
	public void use(SkillUser user) {
		Skill skillToNotify = this;
		if(usable(user)) {
			Timer timer = new Timer();
			long startTime=System.currentTimeMillis();
			setLastExecutionTime (startTime);
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() {
						setPosition(getTarget().getX(), getTarget().getY());
						user.notifyObservers(skillToNotify);
						getTarget().loseHp(getDamage());
						if(getTarget().isDead()){
							gainKillXp(user);
							timer.cancel();
					        timer.purge();
					   	}
						else if(System.currentTimeMillis() - startTime > getCount()*getLapse()){
							timer.cancel();
					        timer.purge();
						}
				  }
			}, 0, getLapse());
		}
	}
	
}
