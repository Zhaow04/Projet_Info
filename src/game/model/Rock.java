package game.model;

import game.utilities.ImageSettings;

public class Rock extends Obstacle{

	//****************************** Attributes ******************************
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/forest.png", 0, 0, 60, 60);
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a rock and sets the map on which it is and its position. Creates the view.
	 * 
	 * @param map
	 * @param x
	 * @param y
	 * @see {@link Obstacle#Obstacle(Map)}
	 */
	public Rock(Map map, int x, int y){
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
