package game.model.component;

import game.model.IMap;
import game.model.Player;
import game.utilities.ImageDB;
import game.utilities.Vector2D;

public class Fire extends Aoe {
	
	public Fire() {
		super(100, 3, ImageDB.getFireView(), 3);
	}

	@Override
	public void preUse(SkillUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean usable() {
		boolean inRange = false;
		SkillTarget target = getTarget();
		if(Math.abs(getUserX() - target.getX()) <= getRange() &&
				Math.abs(getUserY() - target.getY()) <= getRange())
			inRange = true;
		return inRange; 
	}

	@Override
	public void notifyAnimationEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(SkillUser user) {
		/*if(getTarget() == null) {
			setUserX(user.getX());
			setUserY(user.getY());
			notifyObservers("selectTarget");
		}*/
		IMap map = user.getCurrentMap();
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
		if(getTarget() != null) {
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
			setTarget(null);
		}
	}
	
}
