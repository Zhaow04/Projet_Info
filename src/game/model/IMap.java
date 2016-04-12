package game.model;

import game.model.component.SkillTarget;
import game.model.component.Movement;
import game.model.component.ViewSettings;
import game.model.item.IItem;
import game.model.item.Item;

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

}
