package game.model;

import java.io.Serializable;
import java.util.ArrayList;
import game.model.monster.Monster;

/**
 * A class that creates the model of the game.
 *
 */
public class GameModel implements Serializable{
	
	//****************************** Attributes ******************************
	
	private Map map;
	private Player player;
	
	private static final long serialVersionUID = 1L;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the components of the model. The size of the map is defined by {@code mapSize}.
	 * 
	 * @param mapSize
	 */
	public GameModel(int mapSize) {
		setMap( new Map(mapSize));
		setPlayer (new Player());
		map.addToMap(player, 5, 5);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the map.
	 * 
	 * @return map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Gets the player.
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the map.
	 * @param map
	 */
	private void setMap(Map map) {
		this.map=map;
	}
	
	/**
	 * Sets the player.
	 * @param player
	 */
	private void setPlayer(Player player) {
		this.player=player;
	}

	
	//******************************** Methods ********************************

	/**
	 * Starts the monsters threads.
	 */
	public void startThreads() {
		ArrayList<Monster> monsters = getMap().getMonsters();
		for(Monster m : monsters) {
			new Thread(m).start();
		}
	}
	
}
