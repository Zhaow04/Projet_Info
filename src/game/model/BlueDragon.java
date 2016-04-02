package game.model;

import game.utilities.ImageSettings;

/**
 * Extends from {@code Monster} <br/>
 * Blue Dragon - enemy.
 * 
 * @author ZhaoWen
 * @see {@link Monster}
 *
 */
public class BlueDragon extends Monster {
	
	//****************************** Attributes ******************************
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/Dragon.png", 0, 0, 96, 96);
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a blue dragon and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Monster#Monster(Map)}
	 */
	public BlueDragon(Map map, int x, int y, int level){
		super(map);
		setPosition(x,y);
		setBasePos(x,y);
		setDirectionFacing(0,1);  
		setLevel(level);
		setHp(maxHp());
		setKillXp(50);
	}
	
	//************************** Getters and Setters **************************
	

	//******************************** Methods ********************************
	
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}
	
	/**
	 * Defines the maximum of Hp this living being can have (depending on his level).
	 * 
	 * return maxHp
	 */
	private int maxHp(){
		return 300 + getLevel()*100;  
	}
}
