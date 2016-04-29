package game.model;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Public class the serves the purpose of assuring a HP regeneration system .
 *
 */
public class HpRegen{
	
	//****************************** Constructor ******************************

	/**
	 * Creates the HP regeneration system of the living being.
	 * @param living
	 */
	public HpRegen(LivingBeing livingBeing) {
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				livingBeing.getStats().addHp(10);
			}
		};
		t.scheduleAtFixedRate(tt, 0, 10000);
	}
	
}
