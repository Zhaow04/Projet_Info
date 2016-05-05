package game.model.skill;

import game.model.Map;
import game.model.Player;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the area damage attacks.
 * 
 * @see {@link Skill}
 *
 */
public class Aoe extends Skill {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	
	private int radius;
	private int userX, userY;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates an area damage attack.
	 */
	public Aoe(int damage, int range, ViewSettings viewSettings,long releaseTime, int radius) {
		super(damage, range, viewSettings, releaseTime);
		this.radius = radius;
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the radius of the attack.
	 * 
	 * @return radius
	 */
	protected int getRadius() {
		return radius;
	}
	
	protected int getUserX() {
		return userX;
	}

	protected void setUserX(int userX) {
		this.userX = userX;
	}

	protected int getUserY() {
		return userY;
	}

	protected void setUserY(int userY) {
		this.userY = userY;
	}
	
	

	@Override
	protected boolean usable(SkillUser user) {
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
		return (getTarget()!=null); 
	}

	
	@Override
	public void use(SkillUser user) {
		if(usable(user)) {
			Map map = user.getCurrentMap();
			int targetX = getTarget().getX();
			int targetY = getTarget().getY();
			setPosition(getTarget().getX()-1, getTarget().getY()-1);
			user.notifyObservers(this);
			for(int i = -(getRadius()-2); i <= getRadius()-2; i++) {
				for(int j = -(getRadius()-2); j <= getRadius()-2; j++) {
					if(map.isTargetAt(targetX + j, targetY + i)){
						map.getTargetAt(targetX + j, targetY + i).loseHp(getDamage());
						if (user instanceof Player && getTarget().isDead()){
							gainKillXp(user);
						}
					}
				}
			}
			setLastExecutionTime(System.currentTimeMillis());
		}
	}
}
