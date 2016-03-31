package game;

import java.io.IOException;

import game.controller.Controller;
import game.model.GameModel;
import game.view.GameView;
import game.view.StartSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	//************************ main and start methods ************************
	
	public static void main(String[] args){
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage){
		stage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StartScene.fxml"));
			AnchorPane startRoot = (AnchorPane) loader.load();
			StartSceneController startSceneController = (StartSceneController) loader.getController();
			
			startSceneController.setStage(primaryStage);
			Scene primaryScene = new Scene(startRoot);
			primaryStage.setTitle("RPG");
			primaryStage.setScene(primaryScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*GameModel model = new GameModel();
		Controller controller = new Controller();
		GameView view = new GameView(model,controller);
		primaryStage.setTitle("RPG");
		primaryStage.setScene(view.getStartScene());
		primaryStage.show();*/
	}

}
