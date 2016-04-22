package game.model.component;

import game.utilities.ImageDB;

public class BasicMonsterAttack extends DirectAttack {
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the first attack that deals 100 damage.
	 */
	public BasicMonsterAttack() {
		//super(50, new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, new int[2]));
		//super(50, new ViewSettings("game/utilities/purpleattack.png", 0, 342, 96, 114, new int[2]));
		super(50, ImageDB.getBasicMonsterAttackView());
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

