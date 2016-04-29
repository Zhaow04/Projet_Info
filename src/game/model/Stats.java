package game.model;

/**
 * Public class that gathers all the characteristics and statistics of a living being.
 *
 */
public class Stats {
	
	//****************************** Attributes ******************************

	private int baseHp;
	private int hp;
	private int xp;
	private int killXp;
	private int maxHp;
	private int xpToLevelUp;
	
	private static int level;    // Static : when the player will level up, all the other living beings will at the same time.
	
	//****************************** Constructor ******************************

	/**
	 * Creates the basic "stats" of a player.
	 * @param baseHp
	 */
	public Stats(int baseHp) {
		setBaseHp(baseHp);
		setHp(baseHp);
		setXp(0);
		setLevel(1);
		setMaxHp(baseHp);
		updateXpToLevelUp();
	}
	
	/**
	 * Creates the basic "stats" of a monster.
	 * @param baseHp
	 * @param killxp
	 */
	public Stats(int baseHp, int killxp) {
		setBaseHp(baseHp);
		updateMaxHp();
		setHp(maxHp);
		setKillXp(killxp);
	}

	//************************** Getters and Setters **************************

	/**
	 * Gets the base HP.
	 * @return baseHP
	 */
	public int getBaseHp() {
		return baseHp;
	}

	/**
	 * Sets the base HP.
	 * @param baseHp
	 */
	private void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}

	/**
	 * Gets the HP.
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Sets the HP.
	 * @param hp
	 */
	private void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Gets the XP.
	 * @return xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * Sets the XP
	 * @param xp
	 */
	private void setXp(int xp) {
		this.xp = xp;
	}
	
	/**
	 * Gets the killXp (of a monster).
	 * @return killXp
	 */
	public int getKillXp() {
		return killXp;
	}

	/**
	 * Sets the killXp (of a monster).
	 * @param killxp
	 */
	private void setKillXp(int killxp) {
		this.killXp = killxp;
	}

	/**
	 * Gets the level.
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 * @param level
	 */
	private void setLevel(int level) {
		Stats.level = level;
	}
	
	/**
	 * Gets the max of HP possible.
	 * @return maxHp
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * Sets the max of HP possible.
	 * @param maxHp
	 */
	private void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * Gets the needed Xp to level up.
	 * @return xpToLevelUp
	 */
	private int getXpToLevelUp() {
		return xpToLevelUp;
	}
	
	//******************************** Methods ********************************

	/**
	 * Updates the maximum of HP a living being can have (depending on his level).
	 */
	private void updateMaxHp() {
		this.maxHp = getBaseHp() + getLevel()*100;
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
				setHp(0);
			else if(getHp() + hp >= getMaxHp())
				setHp(getMaxHp());
			else
				setHp(getHp() + hp);
			System.out.println(getHp());
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
		setXp(this.getXp() + xp);
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
		setXp(0);
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
		setLevel(getLevel() + 1);
		updateMaxHp();
		int extraXp = getXpToLevelUp() - getXp();
		resetXp();
		updateXpToLevelUp();
		if(extraXp > 0)
			gainXp(extraXp);
	}

}
