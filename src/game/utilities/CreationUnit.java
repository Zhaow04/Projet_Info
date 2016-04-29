package game.utilities;

import java.util.Random;

import game.model.Map;
import game.model.MapComponent;
import game.model.item.HpPotion;
import game.model.monster.BlueDragon;
import game.model.monster.GiantRat;
import game.model.monster.OrangeBat;
import game.model.monster.RedDragon;
import game.model.obstacle.Bush;
import game.model.obstacle.Rock;
import game.model.obstacle.Tree;

/**
 
 * Public class that serves the purpose of creating all the map components of the game.
 * 
 * @see {@link MapComponent}
 *
 */
public class CreationUnit {
	
	//****************************** Constructor ******************************
	/**
	 * Creates the creation unit. 
	 * @param map
	 */
	public CreationUnit(Map map) {
	}
	
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
				if(rand.nextInt(2) == 0){
					addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
					addItem(0, map, j+rand.nextInt(5), i+rand.nextInt(5));
				}
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
			map.addToMap(new Bush(),x,y);
			break;
		case 1:
			map.addToMap(new Rock(),x,y);
			break;
		case 2:
			map.addToMap(new Tree(),x,y);
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
				if(rand.nextInt(2) == 0)
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
			map.addToMap(new BlueDragon(),x,y);
			break;
		case 1:
			map.addToMap(new RedDragon(),x,y);
			break;
		case 2:
			map.addToMap(new GiantRat(),x,y);
			break;
		case 3:
			map.addToMap(new OrangeBat(),x,y);
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
		}
	}

}
