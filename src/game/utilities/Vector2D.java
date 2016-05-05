package game.utilities;

import java.io.Serializable;

/**
 * Implements {@link Serializable}. <br/>
 * Class that allows to create 2-dimensional vectors and provides vector addition and vector subtraction.
 * 
 *
 */
public class Vector2D implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double x,y;
	
	//****************************** Constructor ******************************

	/**
	 * Void constructor.
	 */
	public Vector2D() {
	}
	
	/**
	 * Creates a 2-dimensional vector.
	 * @param x
	 * @param y
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//************************** Getters and Setters **************************

	/**
	 * Gets the x component as a double.
	 * @return x component
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the x component as an integer.
	 * @return x component
	 */
	public int getIntX() {
		return (int) this.x;
	}
	
	/**
	 * Gets the x component as a double.
	 * @return x component
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Gets the x component as an integer.
	 * @return x component
	 */
	public int getIntY() {
		return (int) this.y;
	}
	
	/**
	 * Sets the x and y components.
	 * @param x
	 * @param y
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//******************************** Methods ********************************

	/**
	 * Returns whether or not the x and y components are equal to the specified values.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean equals(double x, double y) {
		return (this.x == x && this.y == y);
	}
	
	/**
	 * Returns whether or not this vector is equal to the specified vector (compares the x and y components).
	 * @param v
	 * @return
	 */
	public boolean equals(Vector2D v) {
		return (this.x == v.x && this.y == v.y);
	}
	
	/**
	 * Adds the specified vector passed in argument to the vector.
	 * @param v
	 */
	public void add(Vector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 * Increments the x and y components respectively with the specified x and y values.
	 * @param x
	 * @param y
	 */
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Returns a new {@link Vector2D} which is the vector addition of the vector on which this method is
	 * called and the vector passed in argument.
	 * @param v
	 * @return
	 */
	public Vector2D plus(Vector2D v) {
		return new Vector2D(this.x + v.x,this.y + v.y);
	}
	
	/**
	 * Returns a new {@link Vector2D} which is the result of the vector (on which this method is called) to
	 * which the x and y values passed in argument were added to the respective component.
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector2D plus(double x, double y) {
		return new Vector2D(this.x + x,this.y + y);
	}
	
	/**
	 * Subtracts the specified vector passed in argument to the vector.
	 * @param v
	 */
	public void subtract(Vector2D v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	
	/**
	 * Decrements the x and y components respectively with the specified x and y values.
	 * @param x
	 * @param y
	 */
	public void subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	/**
	 * Returns a new {@link Vector2D} which is the vector subtraction of the vector on which this method is
	 * called and the vector passed in argument.
	 * @param v
	 * @return
	 */
	public Vector2D minus(Vector2D v) {
		return new Vector2D(this.x - v.x,this.y - v.y);
	}
	
	/**
	 * Returns a new {@link Vector2D} which is the result of the vector (on which this method is called) to
	 * which the x and y values passed in argument were subtracted from the respective component.
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector2D minus(double x, double y) {
		return new Vector2D(this.x - x,this.y - y);
	}
	
	/**
	 * Returns whether or not both the x and y components are positives.
	 * @return
	 */
	public boolean isPositive() {
		return x >= 0 && y >=0;
	}
	
	/**
	 * Returns whether or not both the x and y components are positives after decrementing the x and y
	 * components respectively with the specified x and y values.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPositiveAfterSubstract(double x, double y) {
		subtract(x, y);
		return isPositive();
	}
	
	@Override
	public String toString() {
		return "(" + this.x + " , " + this.y + ")";
	}
	
	
}
