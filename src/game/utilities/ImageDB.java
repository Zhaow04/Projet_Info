package game.utilities;

/**
 * Database of all the images of the game. Contains all the settings for the different available images.
 * 
 */
public abstract class ImageDB {
	
	//****************************** Map ******************************
	
	/**
	 * Gets the {@code ViewSettings} of the map's background.
	 * @return {@code ViewSettings} of the map's background
	 */
	public static ViewSettings getMapView() {
		return new ViewSettings("game/utilities/images/plains.png");
	}
	
	//****************************** Player ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a player.
	 * @return {@code ViewSettings} of a player
	 */
	public static ViewSettings getPlayerView() {
		return new ViewSettings("game/utilities/images/blackmage_m.png", 0, 0, 32, 48, 4, 4,new int[2]);
	}
	
	//****************************** Obstacle ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a bush.
	 * @return {@code ViewSettings} of a bush
	 */
	public static ViewSettings getBushView() {
		return new ViewSettings("game/utilities/images/bush.PNG", 0, 0, 88, 82, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a rock.
	 * @return {@code ViewSettings} of a rock
	 */
	public static ViewSettings getRockView() {
		return new ViewSettings("game/utilities/images/rock.png", 0, 0, 62, 62, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a tree.
	 * @return {@code ViewSettings} of a tree
	 */
	public static ViewSettings getTreeView() {
		return new ViewSettings("game/utilities/images/tree.PNG", 0, 0, 64, 64, new int[2]);
	}
	
	//****************************** Monster ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a blue dragon.
	 * @return {@code ViewSettings} of a blue dragon
	 */
	public static ViewSettings getBlueDragonView() {
		return new ViewSettings("game/utilities/images/Dragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a red dragon.
	 * @return {@code ViewSettings} of a red dragon
	 */
	public static ViewSettings getRedDragonView() {
		return new ViewSettings("game/utilities/images/RedDragon.png", 0, 0, 96, 96, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a giant rat.
	 * @return {@code ViewSettings} of a giant rat
	 */
	public static ViewSettings getGiantRatView() {
		return new ViewSettings("game/utilities/images/GiantRat.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of an orange bat.
	 * @return {@code ViewSettings} of an orange bat
	 */
	public static ViewSettings getOrangeBatView() {
		return new ViewSettings("game/utilities/images/batman.png", 0, 0, 80, 80, 4, 4, new int[2]);
	}
	
	//****************************** Item ******************************
	
	/**
	 * Gets the {@code ViewSettings} of an hp potion.
	 * @return {@code ViewSettings} of an hp potion
	 */
	public static ViewSettings getHpPotionView() {
		return new ViewSettings("game/utilities/images/HpPotion.png", 10, 5, 45, 55, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a parchment.
	 * @return {@code ViewSettings} of a parchment
	 */
	public static ViewSettings getParchmentView() {
		return new ViewSettings("game/utilities/images/parchemin.png", 0, 0, 256, 256, new int[2]);
	}
	//****************************** Skill ******************************
	
	/**
	 * Gets the {@code ViewSettings} of a basic attack.
	 * @return {@code ViewSettings} of a basic attack
	 */
	public static ViewSettings getBasicMonsterAttackView() {
		return new ViewSettings("game/utilities/images/redattack.png", 0, 0, 144, 100, 3, 3, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a lightning attack.
	 * @return {@code ViewSettings} of a lightning attack
	 */
	public static ViewSettings getLightningView() {
		return new ViewSettings("game/utilities/images/LightningAnim.png", 0, 0, 700, 700, 4, 2, new int[2]);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a fire attack.
	 * @return {@code ViewSettings} of a fire attack
	 */
	public static ViewSettings getFireView() {
		return new ViewSettings("game/utilities/images/fire_001.png", 0, 0, 192, 192, 20, 5, new int[2], 3, 3);
	}
	
	/**
	 * Gets the {@code ViewSettings} of a poison attack.
	 * @return {@code ViewSettings} of a poison attack
	 */
	public static ViewSettings getPoisonView() {
		return new ViewSettings("game/utilities/images/poison.png", 0, 0, 50, 50, 3, 3, new int[2]);
	}
}
