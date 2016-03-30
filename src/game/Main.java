package game;

import game.controller.Controller;
import game.model.GameModel;
import game.view.GameView;
import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application {
	
	//****************************** Attributes ******************************
	
	
	
	//************************ main and start methods ************************
	
	public static void main(String[] args){
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage){
		GameModel model = new GameModel();
		Controller controller = new Controller();
		GameView view = new GameView(model,controller);
		//initEventHandler();
		primaryStage.setTitle("RPG");
		primaryStage.setScene(view.getStartScene());
		primaryStage.show();
	}
	
	//******************************** Methods ********************************
	/*
	private void initGameModel(){
		mapModel = new Map(10);
		player = new Player(mapModel);
		Dragon monster1 = new Dragon(mapModel,1,0);
		Dragon monster2 = new Dragon(mapModel,7,3);
		objectList.add(player);
		objectList.add(monster1);
		objectList.add(monster2);
		for(LivingBeing living : objectList){
			mapModel.addLivingOnMap(living);
		}
	}
	
	private void initGameView(){
		mapController = new MapController();
		//MapView mapView = new MapView(mapModel, mapController);
		//setMainScene(new Scene(mapView.getRoot()));
		beingController = new BeingController(objectList);
		//BeingView beingView = new BeingView(objectList,beingController, mapView);
		//beingView.initAllObjectView();
		/*try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/MapView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			mapViewController = loader.getController();
			mapViewController.initMapView(this, mapModel);
			setMainScene(new Scene(root));//, 600, 600));
			root.setPrefHeight(mainScene.getHeight());
			root.setPrefWidth(mainScene.getWidth());
			objectViewController = new ObjectViewController(objectList, mapViewController);
			objectViewController.initObjectView();
	        } catch (IOException e) {
				e.printStackTrace();
			}*/
	/*}

	private void initEventHandler() {
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke){
				if(ke.getText().toUpperCase().matches("[Z]|[Q]|[S]|[D]")){
					beingController.movePlayer(ke.getText().toUpperCase());
					//System.out.println("mainScene.setOnKeyPressed");
				}
			}
		});
	}*/
	

}
