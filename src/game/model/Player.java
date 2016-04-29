package game.model;

import java.util.ArrayList;

import game.model.component.HpRegen;
import game.model.component.Inventory;
import game.model.component.Stats;
import game.model.item.IItem;
import game.model.movement.BasicMove;
import game.model.skill.FireExplosion;
import game.model.skill.FirstAttack;
import game.model.skill.ISkill;
import game.model.skill.Poison;
import game.model.skill.Skill;
import game.model.skill.SkillTarget;
import game.model.skill.SkillUser;
import game.utilities.ImageDB;
import game.view.Observer;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Extends from {@code LivingBeing} <br/>
 * Player of the game.
 * 
 * @author ZhaoWen
 * @see {@link LivingBeing}
 *
 */
public class Player extends LivingBeing implements SkillTarget, SkillUser, Observable {
	
	//****************************** Attributes ******************************
	
	//private int mana;
	//private int regenMana;
	//private int stamina;
	//private int regenStamina;
	private boolean alive;
	private Stats stats;
	private Inventory inventory;
	private ArrayList<ISkill> skillList = new ArrayList<ISkill>();
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a player with initial stats and position, sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Player(){
		super(ImageDB.getPlayerView(), new BasicMove());
		setStats(new Stats(1500));
		setInventory(new Inventory(this));
		addSkill(new FirstAttack());
		addSkill(new FireExplosion());
		addSkill(new Poison());
		new HpRegen(this);
		alive = true;
	}
	
	//************************** Getters and Setters **************************
	
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public Stats getStats() {
		return stats;
	}

	private void setStats(Stats stats) {
		this.stats = stats;
	}
	
	/**
	 * Gets the inventory.
	 * 
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	private void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Gets the skills list of the player.
	 * 
	 * @return skills list
	 */
	private ArrayList<ISkill> getSkillList() {
		return skillList;
	}
	
	//******************************** Methods ********************************
	
	/**
	 * Makes the living being move if possible.
	 * 
	 * @param direction
	 */
	@Override
	public void move(int x, int y) {
		super.move(x,y);
		if(getCurrentMap().isItemAt(getX(), getY()))
			pickUpItem(getCurrentMap().getAndRemoveItem(getX(), getY()));
	}
	
	public ISkill getSkill(int skillNumber) {
		return getSkillList().get(skillNumber);
	}
	
	public int getSkillNumber(Object skill) {
		return getSkillList().indexOf(skill);
	}

	@Override
	public void useSkill(int skillNumber) {
		ISkill skill = getSkillList().get(skillNumber);
		skill.use(this);
	}
	
	public void pickUpItem(IItem item) {
		getInventory().addItem(item);
		item.notifyObservers();
		//getCurrentMap().notifyRemovedFromMap(this);
		//notifyObservers("removedFromMap");
	}

	/**
	 * Use an item located in the inventory at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 */
	public void useItem(int itemNumber){
		getInventory().getItem(itemNumber).use(this);
		getInventory().removeItem(itemNumber);
	}
	
	public void throwItem(int index) {
		getInventory().removeItem(index);
	}
	
	/**
	 * Adds a skill to the list of skills of the player.
	 * 
	 * @param skill
	 */
	public void addSkill(Skill skill){
		getSkillList().add(skill);
	}
	
	/**
	 * Tells if the character can attack or not (need to be in front of a monster)
	 * 
	 */
	/*private boolean canAttack(){
		return (isLivingInFront() && getLivingInFront() instanceof Monster);
	}*/
	
	/**
	 * Makes the character use one of its attacks if he can.
	 * 
	 * @param skillNumber
	 */
	/*public void useDirectAttack(Integer skillNumber){	// keep it Integer for the arg instanceof Integer in update observableView
		if(canAttack()){
			Monster target =(Monster) getLivingInFront();
			System.out.println(target);
			Skill Skill = getSkillList().get(skillNumber);
			Skill.use(target);
			if(target.getHp() <= 0) {
				target.notifyObservers(Skill);
				gainKillXp(target);
				upgradeLevel();
				System.out.println(getXp());
				System.out.println(getLevel());
			}
			else {
				notifyObservers(skillNumber);
			}
		}
	}*/
	
	public void addHp(int hp) {
		getStats().addHp(hp);
		notifyObservers();
	}

	@Override
	public void loseHp(int hp) {
		getStats().loseHp(hp);
		if(getStats().getHp() <= 0) {
			alive = false;
			Map m = (Map) getCurrentMap();
			m.stopThreads();
			notifyObservers("dead");
			m.notifyDead(this);
		}
		else
			notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}

	
}
