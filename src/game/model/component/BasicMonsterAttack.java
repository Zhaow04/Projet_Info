package game.model.component;

import game.utilities.ViewSettings;

public class BasicMonsterAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public BasicMonsterAttack() {
		//super(50, new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, new int[2]));
		//super(50, new ViewSettings("game/utilities/purpleattack.png", 0, 342, 96, 114, new int[2]));
		super(50, new ViewSettings("game/utilities/redattack.png", 0, 0, 144, 100, new int[2]));
	}
	
	
	
	
	

	@Override
	public void notifyAnimationEnd() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void preUse(SkillUser user) {
		// TODO Auto-generated method stub
		
	}
	
}

