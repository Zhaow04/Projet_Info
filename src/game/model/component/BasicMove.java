package game.model.component;

import java.util.ArrayList;
import java.util.Random;

import game.model.IMap;
import game.model.Player;
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

	protected boolean canMove(Movable m, int dx, int dy) {
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
	public void move(Movable m, int dx, int dy) {
		if(canMove(m, dx, dy)) {
		IMap map = m.getCurrentMap();
			m.setDirectionFacing(dx, dy);
			m.notifyObservers("changedDirection");
			setOldX(m.getX()); setOldY(m.getY());
			setNewX(m.getX() + dx); setNewY(m.getY() + dy);
			m.setPosition(m.getX() + dx, m.getY() + dy);
			map.notifyMovement(this);
			m.notifyObservers("moved");
		}
	}
	
	


	@Override
	public void MoveInX(Movable m) {
		
	}

	@Override
	public void MoveRandomly(Movable m) {
		
	}

	@Override
	public void TrackPlayer(Movable m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FaceThePlayer(Movable m) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
