package game.model.component;

import java.util.Timer;
import java.util.TimerTask;

import game.model.Player;

public class HpRegen {
	
	public HpRegen(Player player) {
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				if(player.isAlive())
					player.addHp(10);
				else
					t.cancel();
			}
		};
		t.scheduleAtFixedRate(tt, 0, 10000);
	}
	
}
