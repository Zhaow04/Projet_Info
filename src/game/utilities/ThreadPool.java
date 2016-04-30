package game.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that manages multiple {@link Thread} by using {@link Executors}'
 * {@link Executors#newCachedThreadPool()}.
 * 
 * @author ZhaoWen
 *
 */
public class ThreadPool {
	
	private ExecutorService threadPool;
	
	/**
	 * Creates a new thread pool with {@link Executors#newCachedThreadPool()}.
	 */
	public ThreadPool() {
		 threadPool = Executors.newCachedThreadPool();
	}
	
	/**
	 * 
	 * @param r
	 */
	public void execute(Runnable r) {
		threadPool.execute(r);
	}
	
	public void stopAll() {
		threadPool.shutdownNow();
	}
}
