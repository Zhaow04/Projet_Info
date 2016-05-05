package game.model.skill;

import game.utilities.ImageDB;

/**
 * Extends {@code DirectAttack}. <br/>
 * Basic attack that can be used by all the monsters.
 * 
 * @see {@link DirectAttack}
 */
public class BasicMonsterAttack extends DirectAttack {
	
	private static final long serialVersionUID = 1L;

	//****************************** Constructor ******************************
	
	/**
	 * Creates a basic monster attack that deals 50 damage.
	 */
	public BasicMonsterAttack() {
		super(50, ImageDB.getBasicMonsterAttackView(), 800);
	}
		
}

