package game.model.component;

public class Stats {
	
	private int baseHp;
	private int hp;
	private int xp;
	private int level;
	
	/**
	 * Defines the maximum of Hp a player can have (depending on his level).
	 * 
	 * return maxHp
	 */
	private int maxHp;
	private int xpToLevelUp; // peut etre une meilleure formule Xp/Level
	
	public Stats(int baseHp) {
		setBaseHp(baseHp);
		setHp(baseHp);
		setXp(0);
		setLevel(1);
		setMaxHp(baseHp);
		updateXpToLevelUp();
	}

	public int getBaseHp() {
		return baseHp;
	}

	private void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}

	public int getHp() {
		return hp;
	}

	private void setHp(int hp) {
		this.hp = hp;
	}

	private int getXp() {
		return xp;
	}

	private void setXp(int xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	private void setLevel(int level) {
		this.level = level;
	}
	
	public int getMaxHp() {
		return maxHp;
	}

	private void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	private void updateMaxHp() {
		this.maxHp = getBaseHp() + getLevel()*200;
	}

	private int getXpToLevelUp() {
		return xpToLevelUp;
	}

	private void updateXpToLevelUp() {
		this.xpToLevelUp = getLevel() + getXp()/(getLevel()*getLevel()*50);
	}

	public synchronized void addHp(int hp) {
		setHp(getHp() + hp);
		System.out.println(getHp());
	}
	
	public synchronized void loseHp(int hp) {
		addHp(-hp);
	}
	
	private void addXp(int xp) {
		setXp(getXp() + xp);
	}
	
	public synchronized void gainXp(int xp) {
		if(xp < 0) {
			addXp(xp);
			if(canLevelUp())
				levelUp();
		}
	}
	
	private void resetXp() {
		setXp(0);
	}
	
	private boolean canLevelUp() {
		return getXp() >= getXpToLevelUp();
	}
	
	/**
	 * Upgrades the level of the player
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
