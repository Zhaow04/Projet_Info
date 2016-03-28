package game.model;

import java.util.ArrayList;

/**
 * Only the class in charge of creating all the objects of the model should implements {@code Model}.
 * Allows to get the objects like {@code Map}, {@code Player},...
 * 
 * @author ZhaoWen
 * @see {@link Map}, {@link Player}, {@link LivingBeing}
 *
 */
public interface Model {
	
	/**
	 * Gets the map.
	 * 
	 * @return map
	 */
	public Map getMap();
	
	/**
	 * Gets the player.
	 * 
	 * @return player
	 */
	public Player getPlayer();
	
	/**
	 * Gets the list of living beings.
	 * 
	 * @return list of living beings
	 */
	public ArrayList<LivingBeing> getLivingList();
	
	/**
	 * Gets the list of obstacles.
	 * 
	 * @return list of obstacles
	 */
	public ArrayList<Obstacle> getObstacleList();
	
	/**
	 * Gets the list of safe houses.
	 * 
	 * @return list of safe houses.
	 */
	public ArrayList<SafeHouse> getSafeHouseList();
	
	/**
	 * Gets the list of dungeons.
	 * 
	 * @return list of dungeons.
	 */
	public ArrayList<Dungeon> getDungeonList();
}
