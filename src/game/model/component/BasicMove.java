package game.model.component;

import game.model.IMap;
import game.utilities.Vector2D;

public class BasicMove implements Movement {
	
	private Movable movable;
	private int oldX, oldY, newX, newY;
	
	public BasicMove() {
		
	}
	
	public Movable getMovable() {
		return movable;
	}

	private void setMovable(Movable movable) {
		this.movable = movable;
	}

	public int getOldX() {
		return oldX;
	}

	private void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	private void setOldY(int oldY) {
		this.oldY = oldY;
	}

	public int getNewX() {
		return newX;
	}

	private void setNewX(int newX) {
		this.newX = newX;
	}

	public int getNewY() {
		return newY;
	}

	private void setNewY(int newY) {
		this.newY = newY;
	}

	private boolean canMove(int dx, int dy) {
		Movable m = getMovable();
		boolean canMove = false;
		IMap currentMap = m.getCurrentMap();
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
	public void move(Movable movable, int dx, int dy) {
		if(getMovable() == null)
			setMovable(movable);
		Movable m = getMovable();
		IMap map = m.getCurrentMap();
		m.setDirectionFacing(dx, dy);
		m.notifyObservers("changedDirection");
		if(canMove(dx, dy)) {
			setOldX(m.getX()); setOldY(m.getY());
			setNewX(m.getX() + dx); setNewY(m.getY() + dy);
			m.setPosition(m.getX() + dx, m.getY() + dy);
			map.notifyMovement(this);
			m.notifyObservers("moved");
		}
	}

	@Override
	public void autoMove(Movable m) {
		
	}
	
}
