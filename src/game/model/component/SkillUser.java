package game.model.component;

import game.model.IMap;
import game.utilities.Vector2D;

public interface SkillUser {
	
	IMap getCurrentMap();
	
	int getX();
	
	int getY();
	
	Vector2D getDirectionFacing();
	
	void useSkill(int skilNumber);
	
}
