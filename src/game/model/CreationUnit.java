package game.model;

import java.util.Random;

import game.model.item.HpPotion;
import game.model.item.XpParchment;
import game.utilities.ImageDB;

/**
 * Public class that serves the purpose of creating all the map components of the game.
 * 
 * @see {@link MapComponent}
 *
 */
public abstract class CreationUnit {
		
	//******************************** Methods ********************************
	
	/**
	 * Creates all the components of the map.
	 * @param size
	 * @param map
	 */
	public static void createMap(int size, Map map) {
		Random rand = new Random();
		for(int i = 0; i < size; i++) {
			addObstacle(2, map, i, 0);
			addObstacle(2, map, 0, i);
			addObstacle(2, map, i, size-1);
			addObstacle(2, map, size-1, i);
		}
		for(int i = 1; i <= size-7; i+=5) {
			for(int j = 1; j <= size-7; j+=5) {
				addObstacle(rand.nextInt(3), map, j+rand.nextInt(5), i+rand.nextInt(5));
				addItem(rand.nextInt(3), map, j+rand.nextInt(5), i+rand.nextInt(5));
				addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
			}
		}
	}
	
	/**
	 * Adds an obstacle to the map depending on the int parameter given.	
	 * @param int i
	 * @param map
	 * @param x
	 * @param y
	 */
	private static void addObstacle(int i,  Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new MapComponent(ImageDB.getBushView()),x,y);
			break;
		case 1:
			map.addToMap(new MapComponent(ImageDB.getRockView()),x,y);
			break;
		case 2:
			map.addToMap(new MapComponent(ImageDB.getTreeView()),x,y);
			break;
		}
	}
	
	/**
	 * Adds a number of monsters to the map depending on the map size.
	 * @param map
	 */
	public static void addMonsters(Map map) {
		Random rand = new Random();
		for(int i = 1; i <= map.getSize()-7; i+=5) {
			for(int j = 1; j <= map.getSize()-7; j+=5) {
					addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
			}
		}
	}
	
	/**
	 * Adds a monster to the map depending on the int parameter given.
	 * @param i
	 * @param map
	 * @param x
	 * @param y
	 */
	private static void addMonster(int i,  Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new Monster(ImageDB.getBlueDragonView(), new Stats(400, 100), 3),x,y);
			break;
		case 1:
			map.addToMap(new Monster(ImageDB.getRedDragonView(), new Stats(450, 150), 3),x,y);
			break;
		case 2:
			map.addToMap(new Monster(ImageDB.getGiantRatView(), new Stats(300, 50), 3),x,y);
			break;
		case 3:
			map.addToMap(new Monster(ImageDB.getOrangeBatView(), new Stats(350, 75), 3),x,y);
			break;
		}
	}
	
	/**
	 * Adds an item to the map depending on the int parameter given.
	 * @param i
	 * @param map
	 * @param x
	 * @param y
	 */
	private static void addItem(int i, Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new HpPotion(100),x,y);
			break;
		case 1:
			map.addToMap(new XpParchment(50),x,y);
			break;
		case 2:
			break;
		}
	}

}
