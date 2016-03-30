package game.model;

/**
 * Extends from {@code Monster} <br/>
 * Giant rat - enemy.
 * 
 * @author ZhaoWen
 * @see {@link Monster}
 *
 */
public class GiantRat extends Monster {
	
	//****************************** Attributes ******************************
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a giant rat and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Monster#Monster(Map)}
	 */
	public GiantRat(Map map, int x, int y, int level){
		super(map);
		setPosition(x,y);
		setBasePos(x,y);
		setDirectionFacing('S');  
		setLevel(level);
		setHp(maxHp());
		setKillXp(50);
		
		setImageURL("game/model/GiantRat.png");
		setOffsetX(0);
		setOffsetY(0);
		setWidth(80);
		setHeight(80);
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
