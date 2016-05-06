package game.model;

import java.io.Serializable;

/**
 * Implements {@link Serializable}. <br/>
 * A class that creates the model of the game.
 *
 */
public class GameModel implements Serializable {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	private static boolean running;	
	private Map map;
	private Player player;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the components of the model. The size of the map is defined by {@code mapSize}.
	 * @param mapSize
	 */
	public GameModel(int mapSize) {
		running = false;
		map = new Map(mapSize);
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	/**
	 * Creates a void model and sets its state to non-running.
	 */
	public GameModel() {
		running = false;
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Returns whether or not the model is running.
	 * @return
	 */
	public static boolean isRunning() {
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

	/**
	 * Initializes the model by creating a {@link Map}, a {@link Player} and adds the {@code player} to the
	 * map (current position: (5,5)).
	 * @param mapSize
	 */
	public void init(int mapSize) {
		map = new Map(mapSize);
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	/**
	 * Starts running the game by starting all the threads. All {@link Runnable} of the game should wrap its
	 * {@link Runnable#run()} body inside a {@code while} loop which checks if the model is running.
	 */
	public void start() {
		if(!running) {
			running = true;
			map.run();
			player.startRegen();
		}
	}
	
	/**
	 * Stops running the game by setting its {@code running} attribute to false. Only works if every
	 * {@link Runnable} of the game wrapped its {@link Runnable#run()} body inside a {@code while} loop
	 * which checks if the model is running.
	 */
	public void stop() {
		running = false;
	}
	
}
