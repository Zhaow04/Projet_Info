package game.model.item;

import game.model.Player;

public interface IItem {
	
	int getX();
	
	int getY();
	
	void pickUp(Player player);
	
	void use(Player player);
	
	void notifyObservers();
	
	void removeFromMap();
	
}
