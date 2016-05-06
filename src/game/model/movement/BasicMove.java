package game.model.movement;

import java.io.Serializable;

import game.model.Map;
import game.model.Movable;
import game.utilities.Vector2D;
/**
 * Implements {@link Serializable}, {@code Movement}. <br/>
 * Public class that represents a basic type of movement.
 * 
 * @see {@link Movement}
 *
 */
public class BasicMove implements Movement, Serializable {
	
	//****************************** Attributes ******************************

	private static final long serialVersionUID = 1L;
	
	private Movable movable;
	private int oldX, oldY, newX, newY;
	
	//****************************** Constructor ******************************

	/**
	 * Creates a basic move.
	 */
	public BasicMove() {
		
	}
	
	//************************** Getters and Setters **************************

	@Override
	public Movable getMovable() {
		return movable;
	}
	
	@Override
	public int getOldX() {
		return oldX;
	}
	
	/**
	 * Sets the previous X position (right before the movement).
	 * @param oldX
	 */
	private void setOldX(int oldX) {
		this.oldX = oldX;
	}

	@Override
	public int getOldY() {
		return oldY;
	}

	/**
	 * Sets the previous Y position (right before the movement).
	 * @param oldY
	 */
	private void setOldY(int oldY) {
		this.oldY = oldY;
	}

	@Override
	public int getNewX() {
		return newX;
	}

	/**
	 * Sets the new X position (right after the movement).
	 * @param newX
	 */
	private void setNewX(int newX) {
		this.newX = newX;
	}

	@Override
	public int getNewY() {
		return newY;
	}

	/**
	 * Sets the new Y position (right after the movement).
	 * @param newY
	 */
	private void setNewY(int newY) {
		this.newY = newY;
	}

	//******************************** Methods ********************************

	/**
	 * Returns whether or not the movable can move towards the direction (dx,dy).
	 * @param movable
	 * @param dx
	 * @param dy
	 * @return boolean
	 */
	protected boolean canMove(Movable m, int dx, int dy) {
		boolean canMove = false;
		Map currentMap = m.getCurrentMap();
		Vector2D direction = new Vector2D(dx,dy);
		direction.add(m.getX(), m.getY());
		if(direction.isPositive() &&
				direction.getIntX() < currentMap.getSize() &&
				direction.getIntY() < currentMap.getSize() &&
				currentMap.noCollision(direction.getIntX(),direction.getIntY())) {
			canMove = true;
		}
		return canMove;
	}
	

	@Override
	public void move(Movable m, int dx, int dy) {
		m.setDirectionFacing(dx, dy);
		m.notifyObservers("changedDirection");
		if(canMove(m, dx, dy)) {
			Map map = m.getCurrentMap();
			setOldX(m.getX()); setOldY(m.getY());
			setNewX(m.getX() + dx); setNewY(m.getY() + dy);
			m.setPosition(m.getX() + dx, m.getY() + dy);
			map.notifyMovement(this);
			m.notifyObservers("moved");
		}
	}

	@Override
	public void move(Movable m) {
		
	}

	@Override
	public void setBaseX(int baseX) {
		
	}

	@Override
	public void setBaseY(int baseY) {
		
	}

}
