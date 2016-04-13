package game.model.monster;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import game.model.LivingBeing;
import game.model.Observable;
import game.model.Player;
import game.model.component.SkillTarget;
import game.model.component.ViewSettings;
import game.utilities.Vector2D;
import game.view.Observer;
import javafx.application.Platform;
import game.model.component.Movable;
import game.model.component.BasicMonsterMove;
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
	
	private Stats stats;
	private int killxp;
	private int scope;
	
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
		setStats(new Stats(400));
		setKillXp(0); // pourquoi 0 et pourquoi ici ? vaut mieu =x dans les differents monstres directement
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Stats getStats() {
		return stats;
	}

	private void setStats(Stats stats) {
		this.stats = stats;
	}
	
	/**
	 * Gets the Xp gained by the player when the monster is killed.
	 * 
	 * @return killxp
	 */
	public int getKillXp() {
		return killxp;
	}
	
	/**
	 * Sets the Xp gained by the player when the monster is killed.
	 * 
	 * @param killxp
	 */
	private void setKillXp(int killxp) {
		this.killxp = killxp;
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
	
	//******************************** Methods ********************************
	
	
	
	@Override
	public void run() {
		//while(true) {
			Movable m = this;
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					if (isPlayerInView() && !isPlayerNearby()){
						//System.out.println("In View !");
						getMovement().TrackPlayer(m);
					}
					else if (isPlayerNearby()){
						getMovement().FaceThePlayer(m);
						//System.out.println("je dois attaquer");
					}
					else {
						getMovement().MoveInX(m);

					}
				}
			}, 5000,2000);
			/*System.out.println(Platform.isFxApplicationThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/*final long startTime = System.currentTimeMillis();
			while(true) {
				if(System.currentTimeMillis() - startTime > 2000) {
					break;
				}
			}*/
		//}
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
