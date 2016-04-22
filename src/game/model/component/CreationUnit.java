package game.model.component;

import java.util.Random;

import game.model.Map;
import game.model.item.HpPotion;
import game.model.mapcomponent.Bush;
import game.model.mapcomponent.Rock;
import game.model.mapcomponent.SafeHouse;
import game.model.mapcomponent.Tree;
import game.model.monster.BlueDragon;
import game.model.monster.GiantRat;
import game.model.monster.OrangeBat;
import game.model.monster.RedDragon;

public class CreationUnit {
	/*
	private static final int bushID = 1;
	private static final int rockID = 2;
	private static final int treeID = 3;
	private static final int safehouseID = 4;
	private static final int blueDragonID = 5;
	private static final int redDragonID = 6;
	private static final int giantRatID = 7;
	private static final int orangeRatID = 8;
	private static final int hpPotionID = 9;*/
	
	private Map map;
	private Random rand = new Random();
	
	public CreationUnit(Map map) {
		setMap(map);
	}
	
	private Map getMap() {
		return map;
	}

	private void setMap(Map map) {
		this.map = map;
	}
	
	public static void createMap(int size, Map map) {
		//int[][] grid = new int[size][size];
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
				addItem(0, map, j+rand.nextInt(5), i+rand.nextInt(5));
				if(rand.nextInt(2) == 0)
					addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
			}
		}
	}
	
	private void fillBlock(int[][] map, int row, int column) {
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				map[row+i][column+j] = rand.nextInt(4);
			}
		}
	}
	
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
	
	private static void addSafeHouse(int i, Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new SafeHouse(),x,y);
			break;
		}
	}
	
	public static void addMonsters(Map map) {
		Random rand = new Random();
		for(int i = 1; i <= map.getSize()-7; i+=5) {
			for(int j = 1; j <= map.getSize()-7; j+=5) {
				if(rand.nextInt(2) == 0)
					addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
			}
		}
	}
	
	private static void addMonster(int i,  Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new BlueDragon(1),x,y);
			break;
		case 1:
			map.addToMap(new RedDragon(1),x,y);
			break;
		case 2:
			map.addToMap(new GiantRat(1),x,y);
			break;
		case 3:
			map.addToMap(new OrangeBat(1),x,y);
			break;
		}
	}
	
	private static void addItem(int i, Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new HpPotion(100),x,y);
			break;
		}
	}

	
	
	/**
	 * Adds {@code Viewable} to the map.
	 * 
	 * @param o
	 */
/*	private void addToMap(MapComponent compo, int x, int y) {
		setGrid(mapCompoID, x, y);
		getMapCompos().add(compo);
		compo.addToMap(this, x, y);
	}
	
	private void addToMap(Monster damageable, int x, int y){
		setGrid(damageableID, x, y);
		getMonsters().add(damageable);
		damageable.addToMap(this, x, y);
	}
	
	private void addToMap(Item item, int x, int y) {
		getItems().add(item);
		item.addToMap(this, x, y);
	}
	
	public void addToMap(Player player, int x, int y) {
		setGrid(damageableID, x, y);
		setPlayer(player);
		player.addToMap(this, x, y);
	}
	*/
}
