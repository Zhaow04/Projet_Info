package game.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Implements {@link Serializable}. <br/>
 * Public class that gathers all the characteristics and statistics of a living being.
 *
 */
public class Stats implements Serializable {
	
	//****************************** Attributes ******************************

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int baseHp;
	private int hp;
	private int xp;
	private int killXp;
	private int maxHp;
	private int xpToLevelUp;
	private static int level;
	
	// Static: when the player will level up, all the other living beings will at the same time.
	//private static int commonLevel;
	
	//****************************** Constructor ******************************

	/**
	 * Creates the basic "stats" of a player.
	 * @param baseHp
	 */
	public Stats(int baseHp) {
		this.baseHp = baseHp;
		hp = baseHp;
		xp = 0;
		level = 1;
		maxHp = baseHp;
		updateXpToLevelUp();
	}
	
	/**
	 * Creates the basic "stats" of a monster.
	 * @param baseHp
	 * @param killxp
	 */
	public Stats(int baseHp, int killxp) {
		this.baseHp = baseHp;
		updateMaxHp();
		hp = maxHp;
		this.killXp = killxp;
	}

	//************************** Getters and Setters **************************

	/**
	 * Gets the HP.
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Gets the XP.
	 * @return xp
	 */
	public int getXp() {
		return xp;
	}
	
	/**
	 * Gets the killXp (of a monster).
	 * @return killXp
	 */
	public int getKillXp() {
		return killXp;
	}

	/**
	 * Gets the level.
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the max of HP possible.
	 * @return maxHp
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * Gets the needed Xp to level up.
	 * @return xpToLevelUp
	 */
	public int getXpToLevelUp() {
		return xpToLevelUp;
	}
	
	//******************************** Methods ********************************

	/**
	 * Updates the maximum of HP a living being can have (depending on his level).
	 */
	private void updateMaxHp() {
		maxHp = baseHp + getLevel()*100;
	}

	/**
	 * Updates the XP needed to level up.
	 */
	private void updateXpToLevelUp() {
		this.xpToLevelUp = (getLevel()*getLevel()*50);
	}

	/**
	 * Adds HP to the living being.
	 * @param hp
	 */
	public synchronized void addHp(int hp) {
		if((hp > 0 && getHp() != getMaxHp()) || (hp < 0 && getHp() != 0)) {
			if(getHp() + hp <= 0)
				this.hp = 0;
			else if(getHp() + hp >= getMaxHp())
				this.hp = maxHp;
			else
				this.hp += hp;
			//System.out.println(this.hp);
		}
	}
	
	/**
	 * Makes the living being lose HP.
	 * @param hp
	 */
	public synchronized void loseHp(int hp) {
		addHp(-hp);
	}
	
	/**
	 * Adds XP to the living being.
	 * @param xp
	 */
	private void addXp(int xp) {
		this.xp += xp;
	}
	
	/**
	 * Makes the player gain XP and then level up if he can.
	 * @param xp
	 */
	public synchronized void gainXp(int xp) {
		if(xp >= 0) {
			addXp(xp);
			if(canLevelUp())
				levelUp();
		}
	}
	
	/**
	 * Resets the XP to 0.
	 */
	private void resetXp() {
		xp = 0;
	}
	
	/**
	 * Returns whether or not the player can level up (depending on his XP).
	 * @return boolean
	 */
	private boolean canLevelUp() {
		return getXp() >= getXpToLevelUp();
	}
	
	/**
	 * Makes the living being level up.
	 * 
	 * return maxHp
	 */
	private void levelUp() {
		level ++;
		updateMaxHp();
		int extraXp = getXpToLevelUp() - getXp();
		resetXp();
		updateXpToLevelUp();
		if(extraXp > 0)
			gainXp(extraXp);
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(new Integer(level));
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		level = (Integer) ois.readObject();
	}

}
