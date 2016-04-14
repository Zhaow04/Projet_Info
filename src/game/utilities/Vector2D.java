package game.utilities;

public class Vector2D {
	
	private double x,y;
	
	public Vector2D() {
		
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public int getIntX() {
		return (int) this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getIntY() {
		return (int) this.y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(double x, double y) {
		return (this.x == x && this.y == y);
	}
	
	public boolean equals(Vector2D v) {
		return (this.x == v.x && this.y == v.y);
	}
	
	public void add(Vector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public Vector2D plus(Vector2D v) {
		return new Vector2D(this.x + v.x,this.y + v.y);
	}
	
	public Vector2D plus(double x, double y) {
		return new Vector2D(this.x + x,this.y + y);
	}
	
	public void substract(Vector2D v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public void substract(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void substractY(double y) {
		this.y -= y;
	}
	
	public Vector2D minus(Vector2D v) {
		return new Vector2D(this.x - v.x,this.y - v.y);
	}
	
	public Vector2D minus(double x, double y) {
		return new Vector2D(this.x - x,this.y - y);
	}
	
	public boolean isPositive() {
		return x >= 0 && y >=0;
	}
	
	public boolean isPositiveAfterSubstract(double x, double y) {
		substract(x, y);
		return isPositive();
	}
	
	@Override
	public String toString() {
		return "(" + this.x + " , " + this.y + ")";
	}
	
	
}
