package game.model.component;

public interface SkillTarget {
	
	Stats getStats();
	
	int getX();
	
	int getY();
	
	void loseHp(int hp);
	
}
