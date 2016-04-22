package game.model.movement;

import game.model.IMap;

/**
 * A class implements {@code Movable} when the object can move. Contains the method
 * {@code move}.
 * 
 * @author ZhaoWen
 *
 */
public interface Movable {
	
	IMap getCurrentMap();
	
	int getX();
	
	int getY();
	
	void setPosition(int x, int y);
	
	void setDirectionFacing(int x, int y);
	
	void notifyObservers(Object arg);
	
	void notifyObservers();

}
