package game.model;

/**
 * Extends from {@code Potion}. <br/>
 * Hp potion that heals the user.
 * 
 * @author ZhaoWen
 *
 */
public class HpPotion extends Potion {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a hp potion with a value defined by {@code value}.
	 * 
	 * @param value
	 */
	public HpPotion(int value, int x, int y){
		super(value);
		setPosition(x, y);
		
		setImageURL("game/utilities/HpPotion.png");
		setOffsetX(10);
		setOffsetY(5);
		setWidth(45);
		setHeight(55);
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void use(LivingBeing target) {
		useOnce();
		target.addHp(getValue());
	}
	
	

}
