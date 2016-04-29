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

public abstract class CreationUnit {
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
	
	private Random rand = new Random();
	
	public static void createMap(int size, Map map) {
		int[][] grid = new int[size][size];
		Random rand = new Random();
		for(int i = 0; i < size; i++) {
			grid[0][i] = 1;
			addObstacle(2, map, i, 0);
			grid[i][0] = 1;
			addObstacle(2, map, 0, i);
			grid[size-1][i] = 1;
			addObstacle(2, map, i, size-1);
			grid[i][size-1] = 1;
			addObstacle(2, map, size-1, i);
		}
		for(int i = 1; i <= size-7; i+=5) {
			for(int j = 1; j <= size-7; j+=5) {
				grid[i+rand.nextInt(5)][j+rand.nextInt(5)] = 1;
				addObstacle(rand.nextInt(3), map, j+rand.nextInt(5), i+rand.nextInt(5));
				if(rand.nextInt(2) == 0){
					grid[i+rand.nextInt(5)][j+rand.nextInt(5)] = 2;
					addMonster(rand.nextInt(4), map, j+rand.nextInt(5), i+rand.nextInt(5));
					grid[i+rand.nextInt(5)][j+rand.nextInt(5)] = 3;
					addItem(0, map, j+rand.nextInt(5), i+rand.nextInt(5));
				}
			}
		}
		//constructMap(map, grid);
	}
	
	public static void constructMap(Map map, int[][] grid) {
		int size = grid.length;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				addComponent(map,grid[i][j],i,j);
			}
		}
	}
	
	private static void addComponent(Map map, int type, int i, int j) {
		Random rand = new Random();
		switch (type) {
		case 1:
			addObstacle(rand.nextInt(3), map, j, i);
			break;
		case 2:
			addMonster(rand.nextInt(4), map, j, i);
			break;
		case 3:
			addItem(0, map, j, i);
			break;
		}
	}
	
	private void fillBlock(int[][] map, int row, int column) {
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				map[row+i][column+j] = rand.nextInt(4);
			}
		}
	}
	
	private static void addObstacle(int i, Map map, int x, int y) {
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
	
	private static void addMonster(int i, Map map, int x, int y) {
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
	
	private static void addItem(int i, Map map, int x, int y) {
		switch (i) {
		case 0:
			map.addToMap(new HpPotion(100),x,y);
			break;
		}
	}
}
