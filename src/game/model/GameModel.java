package game.model;

import java.io.Serializable;

/**
 * A class that creates the model of the game.
 *
 */
public class GameModel implements Serializable {
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static boolean running;
	
	private Map map;
	private Player player;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the components of the model. The size of the map is defined by {@code mapSize}.
	 * 
	 * @param mapSize
	 */
	public GameModel(int mapSize) {
		running = false;
		map = new Map(mapSize);
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	public GameModel() {
		running = false;
	}
	
	//************************** Getters and Setters **************************
	
	protected static boolean isRunning() {
		return running;
	}

	/**
	 * Gets the map.
	 * @return map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Gets the player.
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	//******************************** Methods ********************************

	public void init(int mapSize) {
		map = new Map(mapSize);
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	public void start() {
		if(!running) {
			running = true;
			map.startThreads();
		}
	}
	
	public void stop() {
		running = false;
	}
	
}
