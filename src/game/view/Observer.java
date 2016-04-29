package game.view;

import game.model.Observable;

public interface Observer {
	
	/**
	 * oui
	 * @param o
	 * @param arg
	 */
	void update(Observable o, Object arg);
	
}
