package game.utilities;

public abstract class ImageDB {
	
	//****************************** Player ******************************
	
	public static ViewSettings getPlayerView() {
		return new ViewSettings("game/utilities/blackmage_m.png", 0, 0, 32, 48, 4, 4,new int[2]);
	}
	
	//****************************** Obstacle ******************************
	
	public static ViewSettings getBushView() {
		return new ViewSettings("game/utilities/treecomp.PNG", 290, 130, 90, 90, new int[2]);
	}
	
	public static ViewSettings getRockView() {
		return new ViewSettings("game/utilities/forest.png", 0, 0, 60, 60, new int[2]);
	}
	
	public static ViewSettings getTreeView() {
		return new ViewSettings("game/utilities/treecomp.PNG", 0, 65, 63, 63, new int[2]);
	}
	
	//****************************** SafeHouse ******************************
	
	public static ViewSettings getSafeHouseView() {
		return new ViewSettings("game/utilities/safehouse.png", 250, 180, 170, 170, new int[2]);
	}
	
	//****************************** Monster ******************************
	
	public static ViewSettings getBlueDragonView() {
		return new ViewSettings("game/utilities/Dragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	public static ViewSettings getRedDragonView() {
		return new ViewSettings("game/utilities/RedDragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	public static ViewSettings getGiantRatView() {
		return new ViewSettings("game/utilities/GiantRat.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	public static ViewSettings getOrangeBatView() {
		return new ViewSettings("game/utilities/batman.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	//****************************** Item ******************************
	
	public static ViewSettings getHpPotionView() {
		return new ViewSettings("game/utilities/HpPotion.png", 10, 5, 45, 55, new int[2]);
	}
	
	//****************************** Skill ******************************
	
	public static ViewSettings getBasicMonsterAttackView() {
		return new ViewSettings("game/utilities/redattack.png", 0, 0, 144, 100, 3, 3, new int[2]);
	}
	
	public static ViewSettings getLightningView() {
		return new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, 4, 2, new int[2]);
	}
	
	public static ViewSettings getFireView() {
		return new ViewSettings("game/utilities/fire_001.png", 0, 0, 192, 192, 20, 5, new int[2], 3, 3);
	}
	
	public static ViewSettings getPoisonView() {
		return new ViewSettings("game/utilities/poison.png", 0, 0, 50, 50, 3, 3, new int[2]);
	}
}
