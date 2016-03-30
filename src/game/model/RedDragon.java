package game.model;

/**
 * Extends from {@code Monster} <br/>
 * Red Dragon - enemy.
 * 
 * @author ZhaoWen
 * @see {@link Monster}
 *
 */
public class RedDragon extends Monster {
	
	//****************************** Attributes ******************************
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a red dragon and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Monster#Monster(Map)}
	 */
	public RedDragon(Map map, int x, int y, int level){
		super(map);
		setPosition(x,y);
		setBasePos(x,y);
		setDirectionFacing('S');  
		setLevel(level);
		setHp(maxHp());
		setKillXp(50);
		
		setImageURL("game/utilities/RedDragon.png");
		setOffsetX(0);
		setOffsetY(0);
		setWidth(96);
		setHeight(96);
	}
	
	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	/**
	 * Defines the maximum of Hp this living being can have (depending on his level).
	 * 
	 * return maxHp
	 */
	private int maxHp(){
		return 300 + getLevel()*100;  
	}
}
