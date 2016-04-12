package game.model.component;

import java.util.ArrayList;

import game.model.item.Item;
import game.model.mapcomponent.Dungeon;
import game.model.mapcomponent.Obstacle;
import game.model.monster.Monster;

public class MapDataBase {
	
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
	
}
