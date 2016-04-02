package game.model;

import java.util.ArrayList;

import game.utilities.ImageSettings;
import game.utilities.Vector2D;
import game.view.Observer;

/**
 * Extends from {@code ImageSettings}. <br/>
 * Abstract class that serves as a super class for all the living beings of the game.
 * 
 * @author ZhaoWen
 * @see {@link ImageSettings}
 *
 */
public abstract class LivingBeing implements Observable,Movable {
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private int[] position; // w/o initialization
	private Vector2D directionFacing;
	private int hp; // w/o initialization
	private int xp;
	private int level;
	
	//private BeingController beingController;
	
	private ImageSettings imageSettings;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being and sets the map on which it is.
	 * 
	 * @param map
	 */
	public LivingBeing(Map map, int x, int y, int hp, int level){
		currentMap = map;
		setNewPosition(x, y);
		map.addMovable(this);
		this.hp = hp;
		xp = 0;
		this.level = level;
	}
	
	/**
	 * Creates a living being and sets the map on which it is.
	 * 
	 * @param map
	 */
	public LivingBeing(Map map){
		this.currentMap = map;
		map.addMovable(this);
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Map getCurrentMap(){
		return currentMap;
	}
	
	/**
	 * Sets the map on which the living being currently is.
	 * 
	 * @param map
	 */
	protected void setCurrentMap(Map map){
		currentMap = map;
	}
	
	@Override
	public int[] getPosition(){
		return position;
	}
	
	/**
	 * Sets the position to (x,y).
	 * 
	 * @param x
	 * @param y
	 */
	protected void setPosition(int x, int y){
		position = new int[2];
		position[0] = x; position[1] = y;
		currentMap.addObservableOnMap(this);
	}
	
	/**
	 * Gets the direction the living being is facing to.
	 * 
	 * @return direction facing
	 */
	protected Vector2D getDirectionFacing() {
		return directionFacing;
	}
	
	/**
	 * Sets the direction the living being is facing to.
	 * 
	 * @param directionFacing
	 */
	protected void setDirectionFacing(int x, int y) {
		directionFacing = new Vector2D(x,y);
			int i = 0;
			if(directionFacing.equals(0, -1))
				i = 3;
			else if(directionFacing.equals(-1, 0))
				i = 1;
			else if(directionFacing.equals(1, 0))
				i = 2;
			imageSettings.updateDirection(i);
	}
	
	/**
	 * Gets the hp of the living being.
	 * 
	 * @return hp
	 */
	protected int getHp(){
		return hp;
	}
	
	/**
	 * Sets the hp of the living being.
	 * 
	 * @param hp
	 */
	protected void setHp(int hp){
		this.hp = hp;
	}
	/**
	 * Gets the level of the living being.
	 * 
	 * @return level
	 */
	protected int getLevel(){
		return level;
	}
	
	/**
	 * Sets the level of the living being.
	 * 
	 * @param level
	 */
	protected void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * Gets the xp of the living being.
	 * 
	 * @return xp
	 */
	protected int getXp(){
		return xp;
	}
	
	/**
	 * Sets the Xp of the living being.
	 * 
	 * @param hp
	 */
	protected void setXp(int xp){
		this.xp = xp;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}
	
	protected void setImageSettings(ImageSettings imageSettings) {
		this.imageSettings = imageSettings;
	}

	/**
	 * Sets the position to (x,y). Used when a previous position is defined.
	 * 
	 * @param x
	 * @param y
	 */
	protected void setNewPosition(int x,int y){
		setPosition(x,y);
		currentMap.addObservableOnMap(this);
	}
	
	/**
	 * Sets the position (x,y) to empty (no living being).
	 * 
	 * @param x
	 * @param y
	 */
	protected void emptyPosition(int x, int y){
		getCurrentMap().removeObservableOnMap(x,y);
		//getCurrentMap().setEmpty(x,y);
	}
	
	/**
	 * Adds xp to the living being.
	 * 
	 * @param xp
	 */
	protected void addXp(int xp){
		int x = getXp();
		setXp(x+xp);
	}
	
	/**
	 * Adds hp to the living being.
	 * 
	 * @param hp
	 */
	protected void addHp(int hp){
		int x = getHp();
		setHp(x+hp);
	}
	
	/**
	 * Removes hp to the living being.
	 * 
	 * @param hp
	 */
	protected void loseHp(int hp) {
		addHp(-hp);
		System.out.println(getHp());
		if(getHp() <= 0) {
			int[] pos = getPosition();
			emptyPosition(pos[0],pos[1]);
			currentMap.getMovableList().remove(this);
			//System.out.println(currentMap.getLivingOnMap()[pos[1]][pos[0]]);
			System.out.println(this.toString() + " dead");
			notifyObservers("dead");
		}
	}
	
	/**
	 * Gets the size of the current map.
	 * 
	 * @return size of current map
	 */
	protected int getCurrentMapSize(){
		return getCurrentMap().getSize();
	}
	
	/**
	 * Returns whether or not the living being can move in the direction.
	 * 
	 * @param direction
	 * @return can move in the direction or not
	 */
	protected boolean canMove(int x, int y){
		boolean canMove = false;
		Vector2D direction = new Vector2D(x,y);
		direction.add(position[0], position[1]);
		if(direction.isPositive() &&
				direction.getIntX() < currentMap.getSize() &&
				direction.getIntY() < currentMap.getSize() &&
				currentMap.isEmpty(direction.getIntX(),direction.getIntY())) {
			canMove = true;
		}
		return canMove;
	}
	
	/**
	 * Makes the living being move if possible.
	 * 
	 * @param direction
	 */
	@Override
	public void move(int x, int y) {
		int[] pos = position;
		setDirectionFacing(x,y);
		if(canMove(x,y)){
			setNewPosition(pos[0] + directionFacing.getIntX(), pos[1] + directionFacing.getIntY());
			emptyPosition(pos[0], pos[1]);
		}
		notifyObservers();
	}
	
	/**
	 * Returns whether or not another living being is in front.
	 * 
	 * @return living being in front or not
	 */
	protected boolean isLivingInFront(){
		boolean isLivingInFront = false;
		int x = position[0] + directionFacing.getIntX();
		int y = position[1] + directionFacing.getIntY();
		if(currentMap.getObservableOnMap(x, y) instanceof LivingBeing)
			isLivingInFront = true;
		return isLivingInFront;
	}
	
	/**
	 * Gets the living being in front. First check if a living being is in front or not.
	 * 
	 * @return living in front
	 * @see {@link #isLivingInFront}
	 */
	protected LivingBeing getLivingInFront(){
		LivingBeing livingInFront = null;
		if(isLivingInFront()){
			int x = position[0] + directionFacing.getIntX();
			int y = position[1] + directionFacing.getIntY();
			livingInFront = (LivingBeing) currentMap.getObservableOnMap(x, y);
		}
		return livingInFront;
	}

}
