package game.model.monster;

import java.util.ArrayList;

import game.model.LivingBeing;
import game.model.Observable;
import game.model.Player;
import game.model.component.SkillTarget;
import game.utilities.Vector2D;
import game.utilities.ViewSettings;
import game.view.Observer;
import game.model.component.Movable;
import game.model.component.BasicMonsterAttack;
import game.model.component.BasicMonsterMove;
import game.model.component.ISkill;
import game.model.component.SkillUser;
import game.model.component.Stats;

/**
 * Extends from {@code LivingBeing} <br/>
 * Abstract class that serves as a super class for all the different monsters.
 * 
 * @author ZhaoWen
 * @see {@link LivingBeing}
 *
 */
public abstract class Monster extends LivingBeing implements SkillTarget, SkillUser, Runnable, Observable {
	
	//****************************** Attributes ******************************
	
	private int state;
	private Stats stats;
	private int scope;
	private ISkill skill;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Monster(ViewSettings viewSettings) {
		super(viewSettings, new BasicMonsterMove());
		state = 1;
		setSkill(new BasicMonsterAttack());
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Stats getStats() {
		return stats;
	}

	protected void setStats(Stats stats) {
		this.stats = stats;
	}
	
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
	
	protected ISkill getSkill() {
		return this.skill ;
	}
	
	protected void setSkill(ISkill skill) {
		this.skill = skill;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void run() {
		while(state != 0) {
			Movable m = this;
			if (isPlayerInView() && !isPlayerNearby()){
				getMovement().trackPlayer(m);
			}
			else if (isPlayerNearby()){
				getMovement().faceThePlayer(m);
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
	
	/**
	 * Returns whether or not the player is in view.
	 * 
	 * @return player in view or not
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
	public void useSkill(int skillNumber) {
		
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
	/*
	@Override
	public void subjectToSkill(ISkill skill) {
		getStats().subjectToSkill(skill);
		if(getStats().getHp() <= 0) {
			notifyObservers("dead");
			getCurrentMap().notifyRemovedFromMap(this);
		}
		notifyObserversNewView(skill);
	}*/

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
