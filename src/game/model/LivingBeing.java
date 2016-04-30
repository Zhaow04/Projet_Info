package game.model;

import java.util.ArrayList;

import game.model.movement.Movement;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Implements {@code Movable, Observable}. <br/>
 * Extends from {@code MapComponent}. <br/>
 * Abstract class that serves as a super class for all the living beings of the game.
 * 
 * @see {@link MapComponent}
 *
 */
public abstract class LivingBeing extends MapComponent implements Movable, Observable {
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean alive;
	private Vector2D directionFacing = new Vector2D();
	private Movement movement;
	private Stats stats;
	
	private transient ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being, sets its view settings and its movement.
	 * 
	 * @param ViewSettings
	 * @param Movement
	 */
	public LivingBeing(ViewSettings viewSettings, Movement movement) {
		super(viewSettings);
		setDirectionFacing(0, 1);
		setMovement(movement);
	}
	
	//************************** Getters and Setters **************************
	
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Gets the direction the living being is facing to.
	 * 
	 * @return direction facing
	 */
	public Vector2D getDirectionFacing() {
		return directionFacing;
	}

	@Override
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

	/**
	 * Gets the movement of the living being.
	 * @return movement
	 */
	public Movement getMovement() {
		return movement;
	}

	/**
	 * Sets the movement of the living being.
	 * @param movement
	 */
	protected void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	public Stats getStats() {
		return stats;
	}

	protected void setStats(Stats stats) {
		this.stats = stats;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Makes the living being move if possible in the direction (dx,dy).
	 * 
	 * @param direction (dx,dy)
	 */
	public void move(int dx, int dy) {
		getMovement().move(this, dx, dy);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void addHp(int hp) {
		getStats().addHp(hp);
		notifyObservers();
	}
	
}
