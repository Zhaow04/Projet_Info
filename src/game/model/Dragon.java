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
		setHp(300);
		
		setImageURL("game/model/DRAGONS5.png");
		setOffsetX(160);
		setOffsetY(220);
		setWidth(75);
		setHeight(65);
	}
	
	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************

}
