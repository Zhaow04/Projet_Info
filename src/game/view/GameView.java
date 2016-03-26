package game.view;

import game.Controller;
import game.model.GameModel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class GameView {
	
	//****************************** Attributes ******************************
	
	private Scene mainScene;
	private GameModel model;
	private Controller controller;
	
	//****************************** Constructor ******************************
	
	public GameView(GameModel model, Controller controller){
		setModel(model);
		setController(controller);
		MapController mapController = new MapController();
		MapView mapView = new MapView(model, mapController);
		setMainScene(new Scene(mapView.getRoot()));
		
		BeingController beingController = controller.getBeingController();
		BeingView beingView = new BeingView(model,beingController, mapView);
		beingView.initAllObjectView();
		initEventHandler();
	}
	
	//************************** Getters and Setters **************************
	
		public Scene getMainScene() {
			return mainScene;
		}

		private void setMainScene(Scene mainScene) {
			this.mainScene = mainScene;
		}
		
		public GameModel getModel() {
			return model;
		}

		private void setModel(GameModel model) {
			this.model = model;
		}

		public Controller getController() {
			return controller;
		}

		private void setController(Controller controller) {
			this.controller = controller;
		}

		private void initEventHandler() {
			mainScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent ke){
					if(ke.getText().toUpperCase().matches("[Z]|[Q]|[S]|[D]")){
						controller.getBeingController().movePlayer(ke.getText().toUpperCase());
						//System.out.println("mainScene.setOnKeyPressed");
					}
				}
			});
		}
	
}
