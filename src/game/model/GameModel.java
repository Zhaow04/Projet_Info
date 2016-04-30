package game.model;

/**
 * A class that creates the model of the game.
 *
 */
public class GameModel  {
	
	//****************************** Attributes ******************************
	
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

	public void start() {
		running = true;
		map.startThreads();
	}
	
	public void stop() {
		running = false;
	}
	
}
