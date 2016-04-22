package game.model.skill;

import game.model.IMap;
import game.model.component.Stats;
import game.utilities.Vector2D;

public interface SkillUser {
	
	IMap getCurrentMap();
	
	int getX();
	
	int getY();
	
	Vector2D getDirectionFacing();
	
	void useSkill(int skilNumber);
	
	void notifyObservers(Object arg);
	
	Stats getStats();
	
}
