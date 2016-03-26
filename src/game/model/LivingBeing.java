package game.model;

import game.view.BeingController;

/**
 * Extends from {@code ViewableObject}. <br/>
 * Abstract class that serves as a super class for all the living beings of the game.
 * 
 * @author ZhaoWen
 * @see {@link ViewableObject}
 *
 */
public abstract class LivingBeing extends ViewableObject implements Movable {
	
	//****************************** Attributes ******************************
	
	private Map currentMap;
	private int[] position; // w/o initialization
	private char directionFacing;
	private int hp; // w/o initialization
	
	private BeingController beingController;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a living being and sets the map on which it is.
	 * 
	 * @param map
	 */
	public LivingBeing(Map map){
		setCurrentMap(map);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the map on which the living being currently is.
	 * 
	 * @return current map
	 */
	protected Map getCurrentMap(){
		return currentMap;
	}
	
	/**
	 * Sets the map on which the living being currently is.
	 * 
	 * @param map
	 */
	private void setCurrentMap(Map map){
		currentMap = map;
	}
	
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
		setOffsetY(48*i);
			
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
	 * Gets the controller of the living being.
	 * 
	 * @return controller of the living being
	 * @see {@link BeingController}
	 */
	protected BeingController getBeingController() {
		return beingController;
	}
	
	/**
	 * Sets the controller of the living being.
	 * 
	 * @param beingController
	 * @see {@link BeingController}
	 */
	public void setBeingController(BeingController beingController) {
		this.beingController = beingController;
	}
	
	//******************************** Methods ********************************
	
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
	public void emptyPosition(int x, int y){
		getCurrentMap().removeLivingOnMap(x, y);
		getCurrentMap().setEmpty(x,y);
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
	 * Gets the size of the current map.
	 * 
	 * @return size of current map
	 */
	private int getCurrentMapSize(){
		return getCurrentMap().getSize();
	}
	
	/**
	 * Returns whether or not the living being can move in the direction.
	 * 
	 * @param direction
	 * @return can move in the direction or not
	 */
	protected boolean canMove(char direction){
		int[] pos = getPosition();
		boolean canMove = false;
		if(direction == 'N' && pos[1]-1 > -1 &&
				getCurrentMap().isEmpty(pos[0], pos[1]-1))
			canMove = true;
		else if(direction == 'S' && pos[1]+1 < getCurrentMapSize() &&
				getCurrentMap().isEmpty(pos[0], pos[1]+1))
			canMove = true;
		else if(direction == 'W' && pos[0]-1 > -1 &&
				getCurrentMap().isEmpty(pos[0]-1, pos[1]))
			canMove = true;
		else if(direction == 'E' && pos[0]+1 < getCurrentMapSize() &&
				getCurrentMap().isEmpty(pos[0]+1, pos[1]))
			canMove = true;
		return canMove;
	}
	
	@Override
	public void move(char direction) {
		int[] pos = getPosition();
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
			//setChanged();
			//notifyObservers();
		}
		setChanged();
		notifyObservers();
		/*for(int i = 0; i < currentMap.grid.length; i++){
			for(int j = 0; j < currentMap.grid[0].length; j++){
				System.out.println(currentMap.grid[i][j]);
			}
		}*/
	}
	
	/**
	 * Returns whether or not another living being is in front.
	 * 
	 * @return living being in front or not
	 */
	private boolean isLivingInFront(){
		Map currentMap = getCurrentMap();
		int[] pos = getPosition();
		char directionFacing = getDirectionFacing();
		boolean isLivingInFront = false;
		if(directionFacing == 'N' && currentMap.isOccupied(pos[0], pos[1]-1))
			isLivingInFront = true;
		else if(directionFacing == 'S' && currentMap.isOccupied(pos[0], pos[1]+1))
			isLivingInFront = true;
		else if(directionFacing == 'W' && currentMap.isOccupied(pos[0]-1, pos[1]))
			isLivingInFront = true;
		else if(directionFacing == 'E' && currentMap.isOccupied(pos[0]+1, pos[1]))
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
			Map currentMap = getCurrentMap();
			int[] pos = getPosition();
			char directionFacing = getDirectionFacing();
			if(directionFacing == 'N')
				livingInFront = currentMap.getLiving(pos[0], pos[1]-1);
			else if(directionFacing == 'S')
				livingInFront = currentMap.getLiving(pos[0], pos[1]+1);
			else if(directionFacing == 'W')
				livingInFront = currentMap.getLiving(pos[0]-1, pos[1]);
			else if(directionFacing == 'E')
				livingInFront = currentMap.getLiving(pos[0]+1, pos[1]);
		}
		return livingInFront;
	}

}
