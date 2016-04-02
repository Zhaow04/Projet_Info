package game.model;

import game.utilities.ImageSettings;

/**
 * Extends from {@code Potion}. <br/>
 * Hp potion that heals the user.
 * 
 * @author ZhaoWen
 *
 */
public class HpPotion extends Potion {
	
	private ImageSettings imageSettings =
			new ImageSettings("game/utilities/HpPotion.png", 10, 5, 45, 55);
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a hp potion with a value defined by {@code value}. Sets the map on which it is.
	 * 
	 * @param value
	 */
	public HpPotion(Map map, int value, int x, int y) {
		super(map,value);
		setPosition(x, y);
	}
	
	/**
	 * Creates a hp potion with a value defined by {@code value}.
	 * 
	 * @param value
	 */
	public HpPotion(int value, int x, int y){
		super(value);
		setPosition(x, y);
	}
	
	//******************************** Methods ********************************
	
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}

	@Override
	public void use(LivingBeing target) {
		useOnce();
		target.addHp(getValue());
	}
	
	

}
