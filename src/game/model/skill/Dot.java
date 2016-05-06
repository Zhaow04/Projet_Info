package game.model.skill;

import java.util.Timer;
import java.util.TimerTask;

import game.model.Map;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the damage over time attacks.
 * 
 * @see {@link Skill}
 *
 */
public class Dot extends Skill {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	
	private int lapse;
	private int count;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a damage over time attack.
	 */
	public Dot(int damage, int range, ViewSettings viewSettings,long releaseTime, int lapse, int count){
		super(damage, range, viewSettings, releaseTime);
		this.lapse = lapse;
		this.count = count;
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the lapse between two damages.
	 * 
	 * @return lapse
	 */
	protected int getLapse() {
		return lapse;
	}

	/**
	 * Gets the total number of periodic damages.
	 * 
	 * @return count
	 */
	protected int getCount() {
		return count;
	}

	
	@Override
	protected boolean usable(SkillUser user) {
			setTarget(null);
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
		return (getTarget() != null);
	}
	
	@Override
	public void use(SkillUser user) {
		Skill skillToNotify = this;
		if (timingIsOk()){
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
			}, 0, getLapse());}
		}
	}
}
