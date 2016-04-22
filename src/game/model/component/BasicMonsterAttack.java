package game.model.component;

import game.utilities.ViewSettings;

public class BasicMonsterAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public BasicMonsterAttack() {
		super(50, new ViewSettings("game/utilities/redattack.png", 0, 0, 144, 100, new int[2]));
	}
		
}

