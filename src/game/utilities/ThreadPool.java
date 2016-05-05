package game.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that manages multiple {@link Thread} by using {@link Executors}'
 * {@link Executors#newCachedThreadPool()}.
 * 
 *
 */
public class ThreadPool {
	
	//****************************** Attributes ******************************

	private ExecutorService threadPool;
	
	//****************************** Constructor ******************************

	/**
	 * Creates a new thread pool with {@link Executors#newCachedThreadPool()}.
	 */
	public ThreadPool() {
		 threadPool = Executors.newCachedThreadPool();
	}
	
	//******************************** Methods ********************************

	public void execute(Runnable r) {
		threadPool.execute(r);
	}
	
	public void stopAll() {
		threadPool.shutdownNow();
	}
}
