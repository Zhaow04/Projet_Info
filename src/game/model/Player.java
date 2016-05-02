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
import game.utilities.ImageDB;

/**
 * Implements {@code SkillTarget, SkillUser}. <br/>
 * Extends from {@code LivingBeing}. <br/>
 * Player of the game.
 * 
 * @see {@link LivingBeing}
 *
 */
public class Player extends LivingBeing implements SkillTarget, SkillUser{
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean alive;
	private Inventory inventory;
	private ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a player with initial stats, an empty inventory, skills and a HP regeneration system.
	 * 
	 * @see {@link LivingBeing}
	 */
	public Player(){
		super(ImageDB.getPlayerView(), new BasicMove(), new Stats(1500));
		this.inventory = new Inventory(this);
		addSkill(new FirstAttack());
		addSkill(new FireExplosion());
		addSkill(new Poison());
		alive = true;
	}
	
	//************************** Getters and Setters **************************
	
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Gets the inventory.
	 * 
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void move(int dx, int dy) {
		super.move(dx,dy);
		if(getCurrentMap().isItemAt(getX(), getY()))
			pickUpItem(getCurrentMap().getAndRemoveItem(getX(), getY()));
	}
	
	public void startRegen() {
		new HpRegen(this);
	}
	
	@Override
	public void gainXp(int xp) {
		getStats().gainXp(xp);
	}

	@Override
	public boolean isDead() {
		return !alive;
	}

	@Override
	public int getKillXp() {
		return getStats().getKillXp();
	}

	/**
	 * Gets the skill relative the the skillNumber guven
	 * @param skillNumber
	 * @return skill
	 */
	public Skill getSkill(int skillNumber) {
		return skillList.get(skillNumber);
	}
	
	/**
	 * Gets the skill number of the skill given.
	 * @param skill
	 * @return
	 */
	public int getSkillNumber(Skill skill) {
		return skillList.indexOf(skill);
	}

	/**
	 * Makes the Player use the skill referenced by its skillNumber.
	 * @param skillNumber
	 */
	public void useSkill(int skillNumber) {
		if (skillNumber+1<=getStats().getLevel()){
			Skill skill = skillList.get(skillNumber);
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
		skillList.add(skill);
	}

	@Override
	public void loseHp(int hp) {
		getStats().loseHp(hp);
		if(getStats().getHp() <= 0) {
			alive = false;
			Map m = getCurrentMap();
			notifyObservers("dead");
			m.notifyDead(this);
		}
		else
			notifyObservers();
	}
	
}
