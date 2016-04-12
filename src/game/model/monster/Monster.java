package game.model.monster;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import game.model.LivingBeing;
import game.model.Observable;
import game.model.component.SkillTarget;
import game.model.component.ViewSettings;
import game.view.Observer;
import javafx.application.Platform;
import game.model.component.Movable;
import game.model.component.MoveInSquare;
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
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a monster and sets the map on which it is.
	 * 
	 * @param map
	 * @see {@link LivingBeing#LivingBeing(Map)}
	 */
	public Monster(ViewSettings viewSettings) {
		super(viewSettings, new MoveInSquare());
		setStats(new Stats(400));
		setKillXp(0);
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
	
	//******************************** Methods ********************************
	
	@Override
	public void run() {
		//while(true) {
			Movable m = this;
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					getMovement().autoMove(m);
				}
			}, 0,2000);
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
	/*protected boolean isPlayerInView() {
		Viewable[][] entityOnMap = getCurrentMap().getViewableMatrix();
		int x = getPosition()[0];
		int y = getPosition()[1];
		Vector2D directionFacing = getDirectionFacing();
		boolean playerInView = false;
		
		if(directionFacing.getIntX() == 0) {
			for(int i = 0; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					int row = y + i*directionFacing.getIntY();
					int column = x + j;
					if (entityOnMap[row][column] instanceof Player)
						playerInView = true;
				}
			}
		}
		
		else {
			for(int i = -1; i <= 1; i++) {
				for(int j = 0; j <= 1; j++) {
					int row = y + i;
					int column = x + j*directionFacing.getIntX();
					if (entityOnMap[row][column] instanceof Player)
						playerInView = true;
				}
			}
		}
		return playerInView;
	}*/
	
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
