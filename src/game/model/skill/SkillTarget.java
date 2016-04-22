package game.model.skill;

import game.model.component.Stats;

public interface SkillTarget {
	
	Stats getStats();
	
	int getX();
	
	int getY();
	
	void loseHp(int hp);
	
}
