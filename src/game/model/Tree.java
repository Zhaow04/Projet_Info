package game.model;

import game.utilities.ImageSettings;

public class Tree extends Obstacle{

	//****************************** Attributes ******************************
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/treecomp.PNG", 0, 65, 63, 63);
	
	//****************************** Constructor ******************************

	/**
	 * Creates a rock and sets the map on which it is and its position. Creates the view.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Obstacle#Obstacle(Map)}
	 */
	public Tree (Map map, int x, int y){
		super(map);
		setPosition(x,y);
	}

	//************************** Getters and Setters **************************

	//******************************** Methods ********************************
	
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}
	
}
