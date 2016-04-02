package game.model;

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
	
}
