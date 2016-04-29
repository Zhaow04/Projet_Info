package game.model;

import game.model.item.IItem;
import game.model.item.Item;
import game.model.movement.Movement;
import game.model.skill.SkillTarget;
import game.utilities.ViewSettings;

public interface IMap {
	
	Player getPlayer();
	
	int getSize();
	
	ViewSettings getViewSettings();
	
	void addToMap(Player player, int x, int y);
	
	void addToMap(Item item, int x, int y);
	
	boolean noCollision(int x,int y);
		
	boolean isTargetAt(int x, int y);
	
	SkillTarget getTargetAt(int x, int y);
	
	boolean isItemAt(int x, int y);
	
	IItem getAndRemoveItem(int x, int y);
	
	void notifyMovement(Movement m);
	
	void notifyDead(SkillTarget target);
	
	boolean isActive();

}
