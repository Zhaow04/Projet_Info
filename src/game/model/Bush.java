package game.model;

/**
 * Extends from {@code Obstacle} <br/>
 * Basic obstacle.
 * 
 * @author ZhaoWen
 * @see {@link Obstacle}
 *
 */
public class Bush extends Obstacle{

	//****************************** Attributes ******************************


	//****************************** Constructor ******************************

	/**
	 * Creates a bush and sets the map on which it is and its position.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Obstacle#Obstacle(Map)}
	 */
	public Bush (Map map, int x, int y){
		super(map);
		setPosition(x,y);

		setImageURL("game/utilities/treecomp.PNG");
		setOffsetX(290);
		setOffsetY(130);
		setWidth(90);
		setHeight(90);
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************

}
