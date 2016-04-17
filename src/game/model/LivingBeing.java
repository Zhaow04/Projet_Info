package game.model;

import game.model.component.Movable;
import game.model.component.Movement;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

/**
 * Extends from {@code ViewSettings}. <br/>
 * Abstract class that serves as a super class for all the living beings of the game.
 * 
 * @author ZhaoWen
 * @see {@link ViewSettings}
 *
 */
public abstract class LivingBeing extends MapComponent implements Movable {
	
	//****************************** Attributes ******************************
	
	private Vector2D directionFacing = new Vector2D();
	private Movement movement;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being and sets the map on which it is.
	 * 
	 * @param map
	 */
	public LivingBeing(ViewSettings viewSettings, Movement movement) {
		super(viewSettings);
		setDirectionFacing(0, 1);
		setMovement(movement);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the direction the living being is facing to.
	 * 
	 * @return direction facing
	 */
	public Vector2D getDirectionFacing() {
		return directionFacing;
	}

	/**
	 * Sets the direction the living being is facing to.
	 * 
	 * @param directionFacing
	 */
	public void setDirectionFacing(int x, int y) {
		directionFacing.setXY(x, y);
		int i = 0;
		if(getDirectionFacing().equals(0, -1))
			i = 3;
		else if(getDirectionFacing().equals(-1, 0))
			i = 1;
		else if(getDirectionFacing().equals(1, 0))
			i = 2;
		if(getViewSettings() != null) {
			getViewSettings().updateDirection(i);
		}
	}

	public Movement getMovement() {
		return movement;
	}

	protected void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Removes hp to the living being.
	 * 
	 * @param hp
	 */
	/*public void loseHp(int hp) {
		if (getHp() >= 0) {
			addHp(-hp);
			System.out.println(getHp());
		}
		if(getHp() <= 0) {
			int[] pos = getPosition();
			emptyPosition(pos[0],pos[1]);
			currentMap.removeMovable(this);
			currentMap.removeObservableOnMap(pos[0],pos[1]);
			//System.out.println(currentMap.getLivingOnMap()[pos[1]][pos[0]]);
			System.out.println(this.toString() + " dead");
			notifyObservers("dead");
		}
			
	}*/

	/**
	 * Makes the living being move if possible.
	 * 
	 * @param direction
	 */
	
	public void move(int dx, int dy) {
		getMovement().move(this, dx, dy);
	}
	
	/*
	public void Kill() {
		int[] pos = getPosition();
		emptyPosition(pos[0],pos[1]);
		getCurrentMap().removeFromMap(this);
		//System.out.println(currentMap.getLivingOnMap()[pos[1]][pos[0]]);
		System.out.println(this.toString() + " dead");
	}
*/
}
