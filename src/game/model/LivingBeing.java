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
	
	private static final long serialVersionUID = 1L;
	
	private Vector2D directionFacing = new Vector2D();
	private Movement movement;
	private Stats stats;
	
	private transient ArrayList<Observer> observers;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being, sets its direction facing, its stats and its movement.
	 * 
	 * @param ViewSettings
	 * @param Movement
	 */
	public LivingBeing(ViewSettings viewSettings, Movement movement, Stats stats) {
		super(viewSettings);
		setDirectionFacing(0, 1);
		this.movement = movement;
		this.stats = stats;
	}
	
	//************************** Getters and Setters **************************
	
	
	
	/**
	 * Gets the direction the living being is facing.
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
	
	/**
	 * Gets the stats of the living being.
	 * @return stats
	 */
	protected Stats getStats() {
		return stats;
	}
	
	/**
	 * Gets the Hp of the living being.
	 * @return Hp
	 */
	public int getHp() {
		return stats.getHp();
	}
	
	/**
	 * Gets the maximum of Hp the living being can reach.
	 * @return maxHp
	 */
	public int getMaxHp() {
		return stats.getMaxHp();
	}
	
	public int getLevel() {
		return stats.getLevel();
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
		if(observers == null)
			observers = new ArrayList<Observer>();
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	/**
	 * Adds the specified amount of health to the {@code LivingBeing}.
	 * @param hp
	 */
	public void addHp(int hp) {
		stats.addHp(hp);
		notifyObservers();
	}
	
	/**
	 * Returns whether or not the {@code LivingBeing} is alive (health > 0).
	 * @return boolean
	 */
	public boolean isAlive() {
		return (getHp() > 0);
	}
	
	/**
	 * Returns whether or not the {@code LivingBeing} is dead (health <= 0).
	 * @return boolean
	 */
	public boolean isDead() {
		return !isAlive();
	}
}
