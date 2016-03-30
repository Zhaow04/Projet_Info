package game.model;

import java.util.ArrayList;

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
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a player with initial stats and position and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Player(Map map){
		super(map);
		setPosition(0,0);
		setDirectionFacing('S');
		setHp(1500);
		setMana(500);
		inventory = new Inventory();
		FirstAttack firstAttack = new FirstAttack();
		addSkill(firstAttack);
		
		setImageURL("game/utilities/blackmage_m.png");
		setOffsetX(0);
		setOffsetY(0);
		setWidth(32);
		setHeight(48);
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
	 * Sets the amount of mana.
	 * 
	 * @param mana
	 */
	private void setMana(int mana) {
		this.mana = mana;
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
	
	/**
	 * Sets the skills list of the player.
	 * 
	 * @param skillList
	 */
	public void setSkillList(ArrayList<Skill> skillList) {
		this.skillList = skillList;
	}
	
	//******************************** Methods ********************************
	
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
	
	private boolean canAttack(){
		return (isLivingInFront() && getLivingInFront() instanceof Monster);
	}
	
	public void useAttack(int skillNumber){
		if(canAttack()){
			LivingBeing target = getLivingInFront();
			Skill skill = getSkillList().get(skillNumber);
			skill.use(target);
		}
		loseHp(100);
	}

}
