package game.model;

import game.model.movement.FaceThePlayer;
import game.model.movement.MoveInX;
import game.model.movement.TrackPlayer;
import game.model.skill.BasicMonsterAttack;
import game.model.skill.Skill;
import game.model.skill.SkillTarget;
import game.model.skill.SkillUser;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;

/**
 * Extends from {@code LivingBeing} <br/>
 * Abstract class that serves as a super class for all the different monsters.<br/>
 * Implements {@code Runnable}
 * 
 * @see {@link LivingBeing}
 *
 */
public class Monster extends LivingBeing implements Runnable, SkillTarget, SkillUser {
	
	//****************************** Attributes ******************************

	private static final long serialVersionUID = 1L;
	
	private int state;
	private int scope;
	private Skill skill;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster.
	 * 
	 * @param viewSettings
	 */
	public Monster(ViewSettings viewSettings, Stats stats, int scope) {
		super(viewSettings, new MoveInX(), stats);
		this.scope = scope;
		state = 1;
		this.skill = new BasicMonsterAttack();
	}
	
	//************************** Getters and Setters **************************
		
	/**
	 * Gets the scope of the monster (the range of its vision).
	 * 
	 * @return scope
	 */
	public int getScope() {
		return scope;
	}
	
	/**
	 * Sets the the scope of the monster (the range of its vision).
	 * 
	 * @param scope
	 */
	protected void setScope(int scope) {
		this.scope = scope;
	}
	
	/**
	 * Gets the skill of the monster.
	 * @return skill
	 */
	protected Skill getSkill() {
		return this.skill ;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void run() {
		while(state != 0 && getCurrentMap().isActive() && GameModel.isRunning()) {
			Movable m = this;
			if (isPlayerInView() && !isPlayerNearby()){
				new TrackPlayer().move(m);
				getMovement().setBaseX(this.getX());
				getMovement().setBaseY(this.getY());
			}
			else if (isPlayerNearby()){
				new FaceThePlayer().move(m);
				getSkill().use(this);
			}
			else {
				getMovement().move(m);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void gainXp(int xp) {
		getStats().gainXp(xp);
	}

	@Override
	public boolean isDead() {
		return state == 0;
	}

	@Override
	public int getXp() {
		return getStats().getXp();
	}

	/**
	 * Returns whether or not the player is in view (depending on the scope).
	 * 
	 * @return boolean
	 */
	protected boolean isPlayerInView() {
		Player player = getCurrentMap().getPlayer();
		int x = this.getX();
		int y = this.getY();
		Vector2D directionFacing = this.getDirectionFacing();
		boolean playerInView = false;
		
		if(directionFacing.getIntX() == 0) {
			for(int i = 0; i <= scope; i++) {
				for(int j = -2; j <= 2; j++) {
					int row = y + i*directionFacing.getIntY();
					int column = x + j;
					if (column==player.getX() && row==player.getY())
						playerInView = true;
				}
			}
		}
		
		else {
			for(int i = -2; i <= 2; i++) {
				for(int j = 0; j <= scope; j++) {
					int row = y + i;
					int column = x + j*directionFacing.getIntX();
					if (column==player.getX() && row==player.getY())
						playerInView = true;
				}
			}
		}
		return playerInView;
	}
	
	/**
	 * Returns whether or not a Player is Nearby (right on the left/right/behind/facing).
	 * @return boolean
	 */
	public boolean isPlayerNearby(){
		Player player=this.getCurrentMap().getPlayer();
		int x0 = this.getX();
		int y0 = this.getY();
		int x1 = x0 + 1;
		int y1 = y0 + 1;
		int x_1 = x0 - 1;
		int y_1 = y0 - 1;
		boolean isPlayerNearby = false;
		if (   x0==player.getX()&& (y1==player.getY() || y_1==player.getY() )
				  || (y0==player.getY()&& (x1==player.getX() || x_1==player.getX()))  ){
			isPlayerNearby = true;
		}
		return isPlayerNearby;
	}
	
	@Override
	public void loseHp(int hp) {
		getStats().loseHp(hp);
		if(getStats().getHp() <= 0) {
			state = 0;
			notifyObservers("dead");
			getCurrentMap().notifyDead(this);
		}
	}
	
}
