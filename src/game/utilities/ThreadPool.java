package game.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private ExecutorService threadPool;
	
	public ThreadPool() {
		 threadPool = Executors.newCachedThreadPool();
	}
	
	public void execute(Runnable r) {
		threadPool.execute(r);
	}
	
	public void stopAll() {
		threadPool.shutdownNow();
	}
}
