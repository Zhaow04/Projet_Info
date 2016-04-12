package game.model;


import game.view.Observer;

/**
 * A class implements {@code Observable} when its instances are physical entities present on the map.
 * 
 * @author ZhaoWen
 *
 */
public interface Observable extends Viewable {
	
	void addObserver(Observer o);
	
	void notifyObservers();
	
	void notifyObservers(Object arg);
	
}
