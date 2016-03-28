package game.model;

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
	
	public int[] getBasePos() {
		return basePos;
	}

	protected void setBasePos(int x, int y) {
		basePos = new int[2];
		basePos[0] = x; basePos[1] = y;
	}
	
	//******************************** Methods ********************************

	protected boolean isPlayerInView() {
		LivingBeing[][] livingOnMap = getCurrentMap().getLivingOnMap();
		int x = getPosition()[0];
		int y = getPosition()[1];
		boolean playerInView = false;
		
		if(getDirectionFacing() == 'N') {
			for(int i = 0; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if (livingOnMap[y-i][x+j] instanceof Player)
						playerInView = true;
				}
			}
		}
		
		else if(getDirectionFacing() == 'S') {
			for(int i = 0; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if (livingOnMap[y+i][x+j] instanceof Player)
						playerInView = true;
				}
			}
		}
		
		else if(getDirectionFacing() == 'W') {
			for(int i = -1; i <= 1; i++) {
				for(int j = 0; j <= 1; j++) {
					if (livingOnMap[y+i][x-j] instanceof Player)
						playerInView = true;
				}
			}
		}
		
		else if(getDirectionFacing() == 'E') {
			for(int i = -1; i <= 1; i++) {
				for(int j = 0; j <= 1; j++) {
					if (livingOnMap[y+i][x+j] instanceof Player)
						playerInView = true;
				}
			}
		}
		return playerInView;
	}
	
	public void moveInPattern() {
		int[] basePos = getBasePos();
		int[] pos = getPosition();
		//System.out.println(canMove('S') && pos[1] == basePos[1]);
		if(canMove('S') && pos[1] == basePos[1] && pos[0] == basePos[0])
			move('S');
		else if(canMove('E') && pos[1] == basePos[1] + 1 && pos[0] == basePos[0])
			move('E');
		else if(canMove('N') && pos[1] == basePos[1] + 1 && pos[0] == basePos[0] + 1)
			move('N');
		else if(canMove('W') && pos[1] == basePos[1] && pos[0] == basePos[0] + 1)
			move('W');
	}

}
