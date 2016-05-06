package game.model.movement;

import game.model.Movable;

/**
 * A class implements {@code Movement} when it is a type of movement that a living being can have.
 */
public interface Movement {
	
	/**
	 * Makes the movable move towards the direction (dx,dy)
	 * @param movable
	 * @param dx
	 * @param dy
	 */
	public void move(Movable movable, int dx, int dy);
	
	/**
	 * Makes the movable move.
	 * @param movable
	 */
	public void move(Movable m);
	
	/**
	 * Gets the movable.
	 * @return movable
	 */
	public Movable getMovable();
	
	/**
	 * Gets the previous X position (right before the movement).
	 * @return oldX
	 */
	public int getOldX();
	
	/**
	 * Gets the previous Y position (right before the movement).
	 * @return oldY
	 */
	public int getOldY();

	/**
	 * Gets the new X position (right after the movement).
	 * @return newX
	 */
	public int getNewX();

	/**
	 * Gets the new Y position (right after the movement).
	 * @return newY
	 */
	public int getNewY();
	
	/**
	 * Sets the base X position of the movement.
	 * @param baseX
	 */
	public void setBaseX(int baseX);
	
	/**
	 * Sets the base Y position of the movement.
	 * @param baseY
	 */
	public void setBaseY(int baseY);
}
