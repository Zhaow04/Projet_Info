package game.utilities;

public abstract class ImageDB {
	
	//****************************** Player ******************************
	
	public static ViewSettings getPlayerView() {
		return new ViewSettings("game/model/images/blackmage_m.png", 0, 0, 32, 48, 4, 4,new int[2]);
	}
	
	//****************************** Obstacle ******************************
	
	public static ViewSettings getBushView() {
		return new ViewSettings("game/model/images/bush.PNG", 0, 0, 88, 82, new int[2]);
	}
	
	public static ViewSettings getRockView() {
		return new ViewSettings("game/model/images/rock.png", 0, 0, 62, 62, new int[2]);
	}
	
	public static ViewSettings getTreeView() {
		return new ViewSettings("game/model/images/tree.PNG", 0, 0, 64, 64, new int[2]);
	}
	
		
	//****************************** Monster ******************************
	
	public static ViewSettings getBlueDragonView() {
		return new ViewSettings("game/model/images/Dragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	public static ViewSettings getRedDragonView() {
		return new ViewSettings("game/model/images/RedDragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	public static ViewSettings getGiantRatView() {
		return new ViewSettings("game/model/images/GiantRat.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	public static ViewSettings getOrangeBatView() {
		return new ViewSettings("game/model/images/batman.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	//****************************** Item ******************************
	
	public static ViewSettings getHpPotionView() {
		return new ViewSettings("game/model/images/HpPotion.png", 10, 5, 45, 55, new int[2]);
	}
	
	//****************************** Skill ******************************
	
	public static ViewSettings getBasicMonsterAttackView() {
		return new ViewSettings("game/model/images/redattack.png", 0, 0, 144, 100, 3, 3, new int[2]);
	}
	
	public static ViewSettings getLightningView() {
		return new ViewSettings("game/model/images/LightningAnim.png", 0, 0, 700, 700, 4, 2, new int[2]);
	}
	
	public static ViewSettings getFireView() {
		return new ViewSettings("game/model/images/fire_001.png", 0, 0, 192, 192, 20, 5, new int[2], 3, 3);
	}
	
	public static ViewSettings getPoisonView() {
		return new ViewSettings("game/model/images/poison.png", 0, 0, 50, 50, 3, 3, new int[2]);
	}
}
