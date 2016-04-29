package game.model;

import game.utilities.ThreadPool;

/**
 * Implements {@code Model}. <br/>
 * A class that serves to create a model of the game.
 * 
 *
 */
public class GameModel implements Model {
	
	//****************************** Attributes ******************************
	
	private static boolean running;
	
	private ThreadPool threadPool;
	
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
		threadPool = new ThreadPool();
		map = new Map(mapSize);
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	//************************** Getters and Setters **************************
	
	public static boolean isRunning() {
		return running;
	}
	
	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public Player getPlayer() {
		return player;
	}
	
	public void start() {
		running = true;
		map.startThreads();
	}
	
	public void stop() {
		running = false;
	}
	
}
