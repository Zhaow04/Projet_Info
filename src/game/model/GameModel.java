package game.model;

import java.util.ArrayList;

import game.model.monster.Monster;

/**
 * Implements {@code Model}. <br/>
 * A class that serves to create a model of the game.
 * 
 *
 */
public class GameModel implements Model {
	
	//****************************** Attributes ******************************
	
	private Map map;
	private Player player;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the components of the model. The size of the map is defined by {@code mapSize}.
	 * 
	 * @param mapSize
	 */
	public GameModel(int mapSize) {
		map = new Map(mapSize);
		
		player = new Player();
		map.addToMap(player, 5, 5);
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Map getMap() {
		return map;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}

	
	public void startThreads() {
		ArrayList<Monster> monsters = getMap().getMonsters();
		for(Monster m : monsters) {
			new Thread(m).start();
		}
	}
	
}
