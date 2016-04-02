package game.view;

import game.controller.GameController;
import game.model.Map;
import game.model.Observable;
import game.model.Player;
import game.utilities.ObjectTranslate;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Duration;

/**
 * Implements {@code Observer}. <br/>
 * 
 * 
 * @author ZhaoWen
 *
 */
public class MapView extends AnchorPane {
	
	//****************************** Attributes ******************************
	
	private AnchorPane mapContainer;
	private Map mapModel;
	
	private ObservableView observableView;
	
	//****************************** Constructor ******************************
	
	public MapView(Map mapModel, GameController gameController) {
		super();
		
		this.mapModel = mapModel;
		
		this.setPrefSize(600, 600);
		
		initMapView();
		observableView = new ObservableView(this, mapModel.getObservableMatrix(),
				mapModel.getItemOnMap());
		initEventHandler(gameController);
	}
	/*
	public MapView(GameModel model, GameController gameController){
		Map mapModel = model.getMap();
		setMapModel(mapModel);
		mapModel.addObserver(this);
		setMapController(gameController);
		gameController.setMapView(this);
		initMapView();
	}*/
	
	//************************** Getters and Setters **************************

	public AnchorPane getMapContainer(){
		return mapContainer;
	}
	
	public Map getMapModel() {
		return mapModel;
	}

	public ObservableView getObservableView() {
		return observableView;
	}
	
	//******************************** Methods ********************************
	
	private void initMapView(){
		mapContainer = new AnchorPane();
		int mapSize = mapModel.getSize();
		mapContainer.setPrefSize(cellSize()*mapSize, cellSize()*mapSize);
		Image image = new Image(mapModel.getImageSettings().getImageURL());
		ImageView imageContainer = new ImageView(image);
		imageContainer.setFitHeight(500);
		imageContainer.setFitWidth(500);
		BackgroundImage backimage = new BackgroundImage(image,BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		//imageContainer.fitWidthProperty().bind(mapContainer.widthProperty());
		//imageContainer.fitHeightProperty().bind(mapContainer.heightProperty());
		mapContainer.setBackground(new Background(backimage));
		this.getChildren().add(mapContainer);
	}
	
	private void initEventHandler(GameController controller) {
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke){
				//System.out.println(ke.getCode());
				controller.addKey(ke.getCode().toString().toUpperCase());
				//System.out.println(ke.getCode());
				//System.out.println(ke.getCode());
			}
		});
	}
	
	public void addToMap(Node child, int column, int row) {
		mapContainer.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void updateWindowView(Observable o) {
		if(o instanceof Player) {
			int[] pos = o.getPosition();
			if(canTranslateX(o) && !canTranslateY(o)) {
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[0])*cellSize(), "x");
			}
			else if(!canTranslateX(o) && canTranslateY(o)) {
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[1])*cellSize(), "y");
			}
			else if(canTranslateX(o) && canTranslateY(o)) {
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[0])*cellSize(), (5-pos[1])*cellSize());
			}
		}
	}
	
	public void centerOnTarget(Observable target) {
		int[] pos = target.getPosition();
		//System.out.println(canTranslateX(target));
		//System.out.println(canTranslateY(target));
		if(canTranslateX(target) && !canTranslateY(target)) {
			System.out.println("ok");
			double x = (5-pos[0])*cellSize();
			mapContainer.setTranslateX(x);
		}
		else if(!canTranslateX(target) && canTranslateY(target)) {
			System.out.println("ok");
			double y = (5-pos[1])*cellSize();
			mapContainer.setTranslateY(y);
		}
		else if(canTranslateX(target) && canTranslateY(target)) {
			System.out.println("ok");
			double x = (5-pos[0])*cellSize();
			double y = (5-pos[1])*cellSize();
			mapContainer.setTranslateX(x);
			mapContainer.setTranslateY(y);
		}
	}
	
	public double cellSize() {
		//return this.getPrefHeight()/(double) mapModel.getSize();
		return 55.0;
	}
	/*
	public static MapView getMapView(Map mapModel) {
		int i = 0;
		MapView mapView = null;
		while(mapView == null && i < rootList.size()) {
			if(rootList.get(i).getMapModel() == mapModel)
				mapView = rootList.get(i);
		}
		return mapView;
	}*/
	
	private boolean canTranslateX(Observable o) {
		int[] pos = o.getPosition();
		double maxX = mapContainer.getPrefWidth();
		//System.out.println(mapContainer.getPrefWidth());
		return (pos[0]*cellSize() >= 5*cellSize() &&
				pos[0]*cellSize() <= maxX - 6*cellSize());
	}
	
	private boolean canTranslateY(Observable o) {
		int[] pos = o.getPosition();
		double maxY = mapContainer.getPrefHeight();
		return (pos[1]*cellSize() >= 5*cellSize() &&
				pos[1]*cellSize() <= maxY- 6*cellSize());
	}
	
	public boolean isViewUpToDate() {
		return observableView.isViewUpToDate();
	}

}
