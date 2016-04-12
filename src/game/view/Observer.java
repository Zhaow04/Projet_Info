package game.view;

import game.model.Observable;

public interface Observer {
	
	void update(Observable o, Object arg);
	
}
