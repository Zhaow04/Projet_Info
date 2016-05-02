package game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.controller.GameController;
import game.model.GameModel;
import game.view.GameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	//************************ main and start methods ************************

	public static void main(String[] args){
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("RPG");
		primaryStage.setOnCloseRequest((value) -> {
			Platform.exit();
			System.exit(0);
		});
		GameModel model = new GameModel();
		GameController controller = new GameController(model);
		new GameView(primaryStage, model, controller);
	}
	
	/**
	 * Executes the {@link Runnable} in a thread.
	 * @param r
	 */
	public static void execute(Runnable r) {
		threadPool.execute(r);
	}

}
