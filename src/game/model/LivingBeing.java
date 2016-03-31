package game.model;

import java.util.Observable;

import game.utilities.ImageSettings;

/**
 * Extends from {@code ImageSettings}. <br/>
 * Abstract class that serves as a super class for all the living beings of the game.
 * 
 * @author ZhaoWen
 * @see {@link ImageSettings}
 *
 */
public abstract class LivingBeing extends Observable implements Viewable,Movable {
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private int[] position; // w/o initialization
	private char directionFacing;
	private int hp; // w/o initialization
	private int xp;
	private int level;
	
	//private BeingController beingController;
	
	private ImageSettings imageSettings;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being and sets the map on which it is.
	 * 
	 * @param map
	 */
	public LivingBeing(Map map, int x, int y, int hp, int level){
		currentMap = map;
		setNewPosition(x, y);
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
		currentMap.setOccupied(x, y);
	}
	
	/**
	 * Gets the direction the living being is facing to.
	 * 
	 * @return direction facing
	 */
	protected char getDirectionFacing() {
		return directionFacing;
	}
	
	/**
	 * Sets the direction the living being is facing to.
	 * 
	 * @param directionFacing
	 */
	protected void setDirectionFacing(char directionFacing) {
		this.directionFacing = directionFacing;
			int i = 0;
			if(directionFacing == 'N')
				i = 3;
			else if(directionFacing == 'W')
				i = 1;
			else if(directionFacing == 'E')
				i = 2;
			imageSettings.setOffsetY(imageSettings.getHeight()*i);
			//System.out.println(imageSettings.getOffsetY());
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
		currentMap.addLivingOnMap(this);
	}
	
	/**
	 * Sets the position (x,y) to empty (no living being).
	 * 
	 * @param x
	 * @param y
	 */
	protected void emptyPosition(int x, int y){
		getCurrentMap().removeLivingOnMap(x, y);
		getCurrentMap().setEmpty(x,y);
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
			emptyPosition(pos[0], pos[1]);
			//System.out.println(currentMap.getLivingOnMap()[pos[1]][pos[0]]);
			System.out.println(this.toString() + " dead");
			setChanged();
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
	protected boolean canMove(char direction){
		boolean canMove = false;
		if(direction == 'N' && position[1]-1 > -1 &&
				currentMap.isEmpty(position[0], position[1]-1))
			canMove = true;
		else if(direction == 'S' && position[1]+1 < getCurrentMapSize() &&
				currentMap.isEmpty(position[0], position[1]+1))
			canMove = true;
		else if(direction == 'W' && position[0]-1 > -1 &&
				currentMap.isEmpty(position[0]-1, position[1]))
			canMove = true;
		else if(direction == 'E' && position[0]+1 < getCurrentMapSize() &&
				currentMap.isEmpty(position[0]+1, position[1]))
			canMove = true;
		return canMove;
	}
	
	/**
	 * Makes the living being move if possible.
	 * 
	 * @param direction
	 */
	@Override
	public void move(char direction) {
		int[] pos = position;
		setDirectionFacing(direction);
		if(canMove(direction)){
			if(direction == 'N')
				setNewPosition(pos[0], pos[1]-1);
			else if(direction == 'S')
				setNewPosition(pos[0], pos[1]+1);
			else if(direction == 'W')
				setNewPosition(pos[0]-1, pos[1]);
			else if(direction == 'E')
				setNewPosition(pos[0]+1, pos[1]);
			emptyPosition(pos[0], pos[1]);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns whether or not another living being is in front.
	 * 
	 * @return living being in front or not
	 */
	protected boolean isLivingInFront(){
		boolean isLivingInFront = false;
		if(directionFacing == 'N' && currentMap.isOccupied(position[0], position[1]-1))
			isLivingInFront = true;
		else if(directionFacing == 'S' && currentMap.isOccupied(position[0], position[1]+1))
			isLivingInFront = true;
		else if(directionFacing == 'W' && currentMap.isOccupied(position[0]-1, position[1]))
			isLivingInFront = true;
		else if(directionFacing == 'E' && currentMap.isOccupied(position[0]+1, position[1]))
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
			if(directionFacing == 'N')
				livingInFront = currentMap.getLiving(position[0], position[1]-1);
			else if(directionFacing == 'S')
				livingInFront = currentMap.getLiving(position[0], position[1]+1);
			else if(directionFacing == 'W')
				livingInFront = currentMap.getLiving(position[0]-1, position[1]);
			else if(directionFacing == 'E')
				livingInFront = currentMap.getLiving(position[0]+1, position[1]);
		}
		return livingInFront;
	}

}
