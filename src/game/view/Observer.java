package game.view;

import game.model.Observable;

/**
 * A class implements {@code Observer} when it needs to listen to one or multiple {@code Observable}.
 * 
 * @author ZhaoWen
 * @see {@link Observable}
 *
 */
public interface Observer {
	
	/**
	 * Whenever a {@code Observable} changes, this method should be called to update the view consequently.
	 * @param o
	 * @param arg
	 * @see {@link Observable}
	 */
	void update(Observable o, Object arg);
	
}
