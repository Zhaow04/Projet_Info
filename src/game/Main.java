package game;

import game.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	//************************ main and start methods ************************
	
	public static void main(String[] args){
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) {
		new GameView(primaryStage);
	}

}
