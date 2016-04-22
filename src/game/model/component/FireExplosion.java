package game.model.component;

import game.model.IMap;
import game.model.Player;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

public class FireExplosion extends Aoe {
	
	public FireExplosion() {
		super(100, 3, new ViewSettings("game/utilities/fire_001.png", 0, 0, 192, 192, new int[2],3, 3), 3);
	}

	
	@Override
	public boolean usable(SkillUser user) {
		setTarget(null);
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
		return (getTarget()!=null); 
	}

	
	@Override
	public void use(SkillUser user) {
		if(usable(user)) {
			IMap map = user.getCurrentMap();
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
			//setTarget(null);
		}
	}
	
}
