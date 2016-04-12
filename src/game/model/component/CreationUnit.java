package game.model.component;

import game.model.Map;
import game.model.MapComponent;
import game.model.Player;
import game.model.item.HpPotion;
import game.model.item.Item;
import game.model.mapcomponent.Bush;
import game.model.mapcomponent.SafeHouse;
import game.model.mapcomponent.Tree;
import game.model.monster.GiantRat;
import game.model.monster.Monster;
import game.model.monster.RedDragon;

public class CreationUnit {
	
	private Map map;
	
	public CreationUnit(Map map) {
		setMap(map);
	}
	
	private Map getMap() {
		return map;
	}

	private void setMap(Map map) {
		this.map = map;
	}
/*
	private void createAllComponents() {
		RedDragon a = new RedDragon();
		addToMap(a,3,3);
		//new Thread(a).start();
		GiantRat b = new GiantRat();
		addToMap(b,7,3);
		//new Thread(b).start();
		addToMap(new SafeHouse(),7,7);
		for (int i=0 ;i < getSize(); i++) {
			addToMap(new Tree(),0,i);
			addToMap(new Tree(),i,0);
			addToMap(new Tree(),getSize()-1,i);
			addToMap(new Tree(),i,getSize()-1);
		}
		addToMap(new Tree(),6,5);
		addToMap(new Bush(),8,8);
		addToMap(new HpPotion(100), 2, 8);
		addToMap(new HpPotion(100), 8, 2);
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
