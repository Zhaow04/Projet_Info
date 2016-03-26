package game.model;

/**
 * A class implements {@code Usable} when it can be used by another class. Example : item
 * used by the player.
 * 
 * @author ZhaoWen
 *
 */
public interface Usable {
	
	/**
	 * Uses the object on the player.
	 * 
	 * @param player
	 */
	public void use(Player player);
	
}
