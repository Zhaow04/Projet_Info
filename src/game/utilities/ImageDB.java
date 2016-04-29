package game.utilities;

/**
 * Database of all the images of the game. Contains all the settings for the different available images.
 * 
 * @author ZhaoWen
 *
 */
public abstract class ImageDB {
	
	//****************************** Player ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a player.
	 * @return {@code ViewSettings} of a player
	 */
	public static ViewSettings getPlayerView() {
		return new ViewSettings("game/utilities/blackmage_m.png", 0, 0, 32, 48, 4, 4,new int[2]);
	}
	
	//****************************** Obstacle ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a bush.
	 * @return {@code ViewSettings} of a bush
	 */
	public static ViewSettings getBushView() {
		return new ViewSettings("game/utilities/treecomp.PNG", 290, 130, 90, 90, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a rock.
	 * @return {@code ViewSettings} of a rock
	 */
	public static ViewSettings getRockView() {
		return new ViewSettings("game/utilities/forest.png", 0, 0, 60, 60, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a tree.
	 * @return {@code ViewSettings} of a tree
	 */
	public static ViewSettings getTreeView() {
		return new ViewSettings("game/utilities/treecomp.PNG", 0, 65, 63, 63, new int[2]);
	}
	
	//****************************** SafeHouse ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a safe house.
	 * @return {@code ViewSettings} of a safe house
	 */
	public static ViewSettings getSafeHouseView() {
		return new ViewSettings("game/utilities/safehouse.png", 250, 180, 170, 170, new int[2]);
	}
	
	//****************************** Monster ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a blue dragon.
	 * @return {@code ViewSettings} of a blue dragon
	 */
	public static ViewSettings getBlueDragonView() {
		return new ViewSettings("game/utilities/Dragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a red dragon.
	 * @return {@code ViewSettings} of a red dragon
	 */
	public static ViewSettings getRedDragonView() {
		return new ViewSettings("game/utilities/RedDragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a giant rat.
	 * @return {@code ViewSettings} of a giant rat
	 */
	public static ViewSettings getGiantRatView() {
		return new ViewSettings("game/utilities/GiantRat.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of an orange bat.
	 * @return {@code ViewSettings} of an orange bat
	 */
	public static ViewSettings getOrangeBatView() {
		return new ViewSettings("game/utilities/batman.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	//****************************** Item ******************************
	
	/**
	 * Gets the {@code ViewSettings} of an hp potion.
	 * @return {@code ViewSettings} of an hp potion
	 */
	public static ViewSettings getHpPotionView() {
		return new ViewSettings("game/utilities/HpPotion.png", 10, 5, 45, 55, new int[2]);
	}
	
	//****************************** Skill ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a basic attack.
	 * @return {@code ViewSettings} of a basic attack
	 */
	public static ViewSettings getBasicMonsterAttackView() {
		return new ViewSettings("game/utilities/redattack.png", 0, 0, 144, 100, 3, 3, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a lightning attack.
	 * @return {@code ViewSettings} of a lightning attack
	 */
	public static ViewSettings getLightningView() {
		return new ViewSettings("game/utilities/LightningAnim.png", 0, 0, 700, 700, 4, 2, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a fire attack.
	 * @return {@code ViewSettings} of a fire attack
	 */
	public static ViewSettings getFireView() {
		return new ViewSettings("game/utilities/fire_001.png", 0, 0, 192, 192, 20, 5, new int[2], 3, 3);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a poison attack.
	 * @return {@code ViewSettings} of a poison attack
	 */
	public static ViewSettings getPoisonView() {
		return new ViewSettings("game/utilities/poison.png", 0, 0, 50, 50, 3, 3, new int[2]);
	}
}
