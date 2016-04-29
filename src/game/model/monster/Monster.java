package game.model.monster;

import game.model.LivingBeing;
import game.model.Movable;
import game.model.Player;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;
import game.model.movement.FaceThePlayer;
import game.model.movement.MoveInX;
import game.model.movement.Movement;
import game.model.movement.TrackPlayer;
import game.model.skill.BasicMonsterAttack;
import game.model.skill.Skill;
import game.model.skill.SkillTarget;
import game.model.skill.SkillUser;

/**
 * Extends from {@code LivingBeing} <br/>
 * Abstract class that serves as a super class for all the different monsters.<br/>
 * Implements {@code Runnable}
 * 
 * @see {@link LivingBeing}
 *
 */
public abstract class Monster extends LivingBeing implements Runnable, SkillTarget, SkillUser {
	
	//****************************** Attributes ******************************
	
	private int state;
	private int scope;
	private Skill skill;
	private Movement basicMovement;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster.
	 * 
	 * @param viewSettings
	 */
	public Monster(ViewSettings viewSettings) {
		super(viewSettings, new MoveInX());
		this.basicMovement= getMovement();
		state = 1;
		setSkill(new BasicMonsterAttack());
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
	
	/**
	 * Sets the skill of the monster.
	 * @param skill
	 */
	protected void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void run() {
		while(state != 0) {
			Movable m = this;
			if (isPlayerInView() && !isPlayerNearby()){
				setMovement(new TrackPlayer());
				getMovement().move(m);
				basicMovement.setBaseX(this.getX());
				basicMovement.setBaseY(this.getY());
			}
			else if (isPlayerNearby()){
				setMovement(new FaceThePlayer());
				getMovement().move(m);
				getSkill().use(this);
			}
			else {
				setMovement(basicMovement);
				getMovement().move(m);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
