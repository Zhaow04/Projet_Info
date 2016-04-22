package game.model.skill;

import game.utilities.ImageDB;

public class BasicMonsterAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public BasicMonsterAttack() {
		super(50, ImageDB.getBasicMonsterAttackView());
	}
		
}

