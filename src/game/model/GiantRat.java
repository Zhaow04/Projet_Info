package game.model;

import game.utilities.ImageSettings;
import game.view.BeingView;

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
	
	//private ImageSettings imageSettings =
		//	new ImageSettings("game/utilities/GiantRat.png", 0, 0, 80, 80);
	
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
		setImageSettings(new ImageSettings("game/utilities/GiantRat.png", 0, 0, 80, 80));
		setNewPosition(x,y);
		setBasePos(x,y);
		setDirectionFacing('S');  
		setLevel(level);
		setHp(maxHp());
		setKillXp(50);
		
		new BeingView(this);
	}
	
	//************************** Getters and Setters **************************
	
	

	//******************************** Methods ********************************
	/*
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}*/
	
	/**
	 * Defines the maximum of Hp this living being can have (depending on his level).
	 * 
	 * return maxHp
	 */
	private int maxHp(){
		return 300 + getLevel()*100;  
	}
}
