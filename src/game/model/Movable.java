package game.model;

/**
 * A class implements {@code Movable} when the object can move. Contains the method
 * {@code move}.
 * 
 * @author ZhaoWen
 *
 */
public interface Movable {
	
	/**
	 * Gets the position (x,y).
	 * 
	 * @return position
	 */
	public int[] getPosition();
	
	/**
	 * Move the object in the direction passed in parameter.
	 * 
	 * @param direction
	 */
	public void move(char direction);

}
