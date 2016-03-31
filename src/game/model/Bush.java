package game.model;

import game.utilities.ImageSettings;
import game.view.ObstacleView;

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
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/treecomp.PNG", 290, 130, 90, 90);

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
		
		new ObstacleView(this);
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}
	
}
