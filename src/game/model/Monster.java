package game.model;

import game.utilities.Vector2D;

/**
 * Extends from {@code LivingBeing} <br/>
 * Abstract class that serves as a super class for all the different monsters.
 * 
 * @author ZhaoWen
 * @see {@link LivingBeing}
 *
 */
public abstract class Monster extends LivingBeing {
	
	//****************************** Attributes ******************************
	
	private int[] basePos;
	private int killxp;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Monster(Map map){
		super(map);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the base position of the monster (i.e. where it was first on the map).
	 * 
	 * @return base position
	 */
	public int[] getBasePos() {
		return basePos;
	}
	
	/**
	 * Sets the base position of the monster (i.e. where it was first on the map).
	 * 
	 * @param x
	 * @param y
	 */
	protected void setBasePos(int x, int y) {
		basePos = new int[2];
		basePos[0] = x; basePos[1] = y;
	}
	
	/**
	 * Gets the Xp gained by the player when the monster is killed.
	 * 
	 * @return killxp
	 */
	public int getKillXp() {
		return killxp;
	}
	
	/**
	 * Sets the Xp gained by the player when the monster is killed.
	 * 
	 * @param killxp
	 */
	protected void setKillXp(int killxp) {
		this.killxp = killxp;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Returns whether or not the player is in view.
	 * 
	 * @return player in view or not
	 */
	protected boolean isPlayerInView() {
		Observable[][] entityOnMap = getCurrentMap().getObservableMatrix();
		int x = getPosition()[0];
		int y = getPosition()[1];
		Vector2D directionFacing = getDirectionFacing();
		boolean playerInView = false;
		
		if(directionFacing.getIntX() == 0) {
			for(int i = 0; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					int row = y + i*directionFacing.getIntY();
					int column = x + j;
					if (entityOnMap[row][column] instanceof Player)
						playerInView = true;
				}
			}
		}
		
		else {
			for(int i = -1; i <= 1; i++) {
				for(int j = 0; j <= 1; j++) {
					int row = y + i;
					int column = x + j*directionFacing.getIntX();
					if (entityOnMap[row][column] instanceof Player)
						playerInView = true;
				}
			}
		}
		return playerInView;
	}
	
	/**
	 * Makes the monster move in a certain defined way, if possible (here, drawing a square while moving).
	 * 
	 */
	@Override
	public void move(int x, int y) {
		moveInPattern();
	}
	
	private void moveInPattern() {
		int[] basePos = getBasePos();
		int[] pos = getPosition();
		//System.out.println(canMove('S') && pos[1] == basePos[1]);
		if(canMove(0,1) && pos[1] == basePos[1] && pos[0] == basePos[0])
			super.move(0,1);
		else if(canMove(1,0) && pos[1] == basePos[1] + 1 && pos[0] == basePos[0])
			super.move(1,0);
		else if(canMove(0,-1) && pos[1] == basePos[1] + 1 && pos[0] == basePos[0] + 1)
			super.move(0,-1);
		else if(canMove(-1,0) && pos[1] == basePos[1] && pos[0] == basePos[0] + 1)
			super.move(-1,0);
	}

}
