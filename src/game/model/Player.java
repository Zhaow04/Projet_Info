package game.model;

import java.util.ArrayList;

import game.model.item.Item;
import game.model.movement.BasicMove;
import game.model.skill.FireExplosion;
import game.model.skill.FirstAttack;
import game.model.skill.Poison;
import game.model.skill.Skill;
import game.model.skill.SkillTarget;
import game.model.skill.SkillUser;
import game.utilities.HpRegen;
import game.utilities.ImageDB;

/**
 * Extends from {@code LivingBeing} <br/>
 * Implements {@code SkillTarget, SkillUser}. <br/>
 * Player of the game.
 * 
 * @see {@link LivingBeing}
 *
 */
public class Player extends LivingBeing implements SkillTarget, SkillUser{
	
	//****************************** Attributes ******************************
	
	private Inventory inventory;
	private ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a player with initial stats, an empty inventory, skills and a HP regeneration system.
	 * 
	 * @see {@link LivingBeing}
	 */
	public Player(){
		super(ImageDB.getPlayerView(), new BasicMove());
		setStats(new Stats(1500));
		setInventory(new Inventory(this));
		addSkill(new FirstAttack());
		addSkill(new FireExplosion());
		addSkill(new Poison());
		new HpRegen(this);
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the inventory.
	 * 
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Sets the inventory.
	 * @param inventory
	 */
	private void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Gets the skills list of the player.
	 * 
	 * @return skills list
	 */
	private ArrayList<Skill> getSkillList() {
		return skillList;
	}
	
	//******************************** Methods ********************************
	
	
	@Override
	public void move(int dx, int dy) {
		super.move(dx,dy);
		if(getCurrentMap().isItemAt(getX(), getY()))
			pickUpItem(getCurrentMap().getAndRemoveItem(getX(), getY()));
	}
	
	/**
	 * Gets the skill relative the the skillNumber guven
	 * @param skillNumber
	 * @return skill
	 */
	public Skill getSkill(int skillNumber) {
		return getSkillList().get(skillNumber);
	}
	
	/**
	 * Gets the skill number of the skill given.
	 * @param skill
	 * @return
	 */
	public int getSkillNumber(Skill skill) {
		return getSkillList().indexOf(skill);
	}

	/**
	 * Makes the Player use the skill referenced by its skillNumber.
	 * @param skillNumber
	 */
	public void useSkill(int skillNumber) {
		if (skillNumber+1<=getStats().getLevel()){
			Skill skill = getSkillList().get(skillNumber);
			skill.use(this);
		}
	}
		
	/**
	 * Makes the player pick up the item given and put it in his inventory if possible.
	 * @param item
	 */
	public void pickUpItem(Item item) {
		getInventory().addItem(item);
		item.notifyObservers();
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
	
	/**
	 * Throws an item from the inventory.
	 * @param index of the item
	 */
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
	
	@Override
	public void loseHp(int hp) {
		getStats().loseHp(hp);
	}
	
}
