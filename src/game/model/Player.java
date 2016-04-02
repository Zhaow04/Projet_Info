package game.model;

import java.util.ArrayList;

import game.utilities.ImageSettings;

/**
 * Extends from {@code LivingBeing} <br/>
 * Player of the game.
 * 
 * @author ZhaoWen
 * @see {@link LivingBeing}
 *
 */
public class Player extends LivingBeing {
	
	//****************************** Attributes ******************************
	
	private int mana;
	//private int regenMana;
	//private int stamina;
	//private int regenStamina;
	private Inventory inventory;
	private ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	//private ImageSettings imageSettings =
		//	new ImageSettings("game/utilities/blackmage_m.png", 0, 0, 32, 48);
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a player with initial stats and position, sets the map on which it is and creates the view.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Player(Map map, int x, int y){
		super(map,x,y,1500,1);
		setImageSettings(new ImageSettings("game/utilities/blackmage_m.png", 0, 0, 32, 48));
		setDirectionFacing(0,1);
		//map.addToViewable(this);
		mana = 500;
		inventory = new Inventory();
		FirstAttack firstAttack = new FirstAttack();
		addSkill(firstAttack);
	}
	
	/**
	 * Creates a player with initial stats and position and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Player(Map map){
		super(map);
		setPosition(0,0);
		setDirectionFacing(0,-1);
		setHp(1500);
		mana = 500;
		setXp(0);
		setLevel(1);
		inventory = new Inventory();
		FirstAttack firstAttack = new FirstAttack();
		addSkill(firstAttack);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the amount of mana.
	 * 
	 * @return amount of mana
	 */
	protected int getMana() {
		return mana;
	}
	
	/**
	 * Gets the inventory.
	 * 
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Gets the skills list of the player.
	 * 
	 * @return skills list
	 */
	public ArrayList<Skill> getSkillList() {
		return skillList;
	}
	
	//******************************** Methods ********************************
	/*
	@Override
	public ImageSettings getImageSettings() {
		return imageSettings;
	}*/
	
	/**
	 * Makes the living being move if possible.
	 * 
	 * @param direction
	 */
	@Override
	public void move(int x, int y) {
		super.move(x,y);
		if(isItemAtFeet()) {
			takeItem();
		}
	}
	
	/**
	 * Use an item located in the inventory at the slot given by {@code itemNumber}.
	 * 
	 * @param itemNumber
	 */
	public void useItem(int itemNumber){
		getInventory().getItem(itemNumber).use(this);
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
	private boolean canAttack(){
		return (isLivingInFront() && getLivingInFront() instanceof Monster);
	}
	
	/**
	 * Makes the character use one of its attacks if he can.
	 * 
	 * @param skillNumber
	 */
	public void useDirectAttack(Integer skillNumber){	// keep it Integer for the arg instanceof Integer in update observableView
		if(canAttack()){
			Monster target =(Monster) getLivingInFront();
			System.out.println(target);
			Skill skill = getSkillList().get(skillNumber);
			skill.use(target);
			if(target.getHp() <= 0) {
				target.notifyObservers(skill);
				gainKillXp(target);
				upgradeLevel();
				System.out.println(getXp());
				System.out.println(getLevel());
			}
			else {
				notifyObservers(skillNumber);
			}
		}
	}
	/**
	 * Defines the maximum of Hp a player can have (depending on his level).
	 * 
	 * return maxHp
	 */
	private int maxHp(){
		return 1500 + getLevel()*200;
	}
	
	/**
	 * Upgrades the level of the player
	 * 
	 * return maxHp
	 */
	private void upgradeLevel(){
		setLevel(getLevel() + getXp() / ((getLevel()*getLevel()*50))); // peut etre une meilleure formule Xp/Level 
	}
	
	private void gainKillXp(Monster target){
		addXp(target.getKillXp());
	}
	
	public boolean isItemAtFeet() {
		int[] pos = getPosition();
		return getCurrentMap().getItem(pos[0], pos[1]) != null;
	}
	
	public void takeItem() {
		int[] pos = getPosition();
		inventory.addItem(getCurrentMap().getAndRemoveItem(pos[0], pos[1]));
	}
}
