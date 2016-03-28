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
	public HpPotion(int value){
		super(value);
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void use(LivingBeing target) {
		useOnce();
		target.addHp(getValue());
	}
	
	

}
