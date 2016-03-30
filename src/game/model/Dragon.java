package game.model;

/**
 * Extends from {@code Monster} <br/>
 * Basic enemy.
 * 
 * @author ZhaoWen
 * @see {@link Monster}
 *
 */
public class Dragon extends Monster {
	
	//****************************** Attributes ******************************
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a dragon and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Monster#Monster(Map)}
	 */
	public Dragon(Map map, int x, int y){
		super(map);
		setPosition(x,y);
		setBasePos(x,y);
		setDirectionFacing('S');
		setHp(300);
		
		setImageURL("game/utilities/DRAGONS5.png");
		setOffsetX(160);
		setOffsetY(227);
		setWidth(70);
		setHeight(55);
	}
	
	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	
}
