package game.model.component;

import game.model.IMap;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;
import java.util.Timer;
import java.util.TimerTask;

public class Poison extends Dot {

	
	public Poison() {
		super(40, 3, new ViewSettings("game/utilities/poison.png", 0, 0, 50, 50, new int[2]), 2000, 5);
	}

	
	private int localCount=0;
	Skill toNotify=this;

	@Override
	public void use(SkillUser user) {
		if (usable(user)) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() {
					  	setStartPos(getTarget().getX(), getTarget().getY());
						setPosition(getTarget().getX(), getTarget().getY());
						user.notifyObservers(toNotify);
						getTarget().loseHp(getDamage());
						localCount ++;
						if (getTarget().getStats().getHp()<=0){
							gainKillXp(user);
							System.out.println(user.getStats().getXp());
							System.out.println(user.getStats().getLevel());
							localCount=0;
							timer.cancel();
					        timer.purge();
					   	}
						else if (localCount>=getCount()){
							localCount=0;
							timer.cancel();
					        timer.purge();
						}
				  }
			}, 0, getLapse());
		}
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
		return (getTarget() != null);
	}
	
}
