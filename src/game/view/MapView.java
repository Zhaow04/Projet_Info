package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.controller.MapController;
import game.model.Map;
import game.model.Movable;
import game.model.Player;
import game.utilities.ObjectTranslate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class MapView extends AnchorPane implements Observer {
	
	//****************************** Attributes ******************************
	
	private static ArrayList<MapView> rootList = new ArrayList<MapView>();
	public static MapView currentRoot;
	
	private AnchorPane mapContainer;
	private Map mapModel;
	private MapController mapController;
	
	//****************************** Constructor ******************************
	
	public MapView(Map mapModel) {
		super();
		
		this.mapModel = mapModel;
		mapModel.addObserver(this);
		
		mapController = new MapController();
		mapController.setMapModel(mapModel);
		mapController.setMapView(this);
		
		this.setPrefSize(600, 600);
		
		initMapView();
		rootList.add(this);
		if(currentRoot == null)
			currentRoot = this;
	}
	/*
	public MapView(GameModel model, MapController mapController){
		Map mapModel = model.getMap();
		setMapModel(mapModel);
		mapModel.addObserver(this);
		setMapController(mapController);
		mapController.setMapView(this);
		initMapView();
	}*/
	
	//************************** Getters and Setters **************************
	
	public static ArrayList<MapView> getRootList() {
		return rootList;
	}

	public AnchorPane getMapContainer(){
		return mapContainer;
	}
	
	public Map getMapModel() {
		return mapModel;
	}

	public MapController getMapController() {
		return mapController;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Player) {
			int[] pos = ((Movable) o).getPosition();
			if(canTranslateX((Movable) o) && !canTranslateY((Movable) o)) {
				/*double x = pos[0]*cellSize();
			double y = pos[1]*cellSize();
			mapContainer.setTranslateX(300-x);
			mapContainer.setTranslateY(300-y);*/
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[0])*currentMapCellSize(), "x");
			}
			else if(!canTranslateX((Movable) o) && canTranslateY((Movable) o)) {
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[1])*currentMapCellSize(), "y");
			}
			else if(canTranslateX((Movable) o) && canTranslateY((Movable) o)) {
				new ObjectTranslate(Duration.millis(300), mapContainer,
						(5-pos[0])*currentMapCellSize(), (5-pos[1])*currentMapCellSize());
			}
		}
	}
	/*
	private void initRoot(){
		AnchorPane root = new AnchorPane();
		setRoot(root);
		root.setPrefSize(600, 600);
	}*/
	
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
	
	public static void addToCurrentMap(Node child, int column, int row) {
		currentRoot.getMapContainer().getChildren().add(child);
		double x = column*currentMapCellSize();
		double y = row*currentMapCellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void addToMap(Node child, int column, int row) {
		mapContainer.getChildren().add(child);
		double x = column*currentMapCellSize();
		double y = row*currentMapCellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void centerOnTarget(Movable target) {
		int[] pos = target.getPosition();
		//System.out.println(canTranslateX(target));
		//System.out.println(canTranslateY(target));
		if(canTranslateX(target) && !canTranslateY(target)) {
			System.out.println("ok");
			double x = (5-pos[0])*currentMapCellSize();
			mapContainer.setTranslateX(x);
		}
		else if(!canTranslateX(target) && canTranslateY(target)) {
			System.out.println("ok");
			double y = (5-pos[1])*currentMapCellSize();
			mapContainer.setTranslateY(y);
		}
		else if(canTranslateX(target) && canTranslateY(target)) {
			System.out.println("ok");
			double x = (5-pos[0])*currentMapCellSize();
			double y = (5-pos[1])*currentMapCellSize();
			mapContainer.setTranslateX(x);
			mapContainer.setTranslateY(y);
		}
	}
	
	public static double currentMapCellSize() {
		//return rootList.get(0).getPrefHeight()/(double) currentRoot.mapModel.getSize();
		return 55.0;
	}
	
	public double cellSize() {
		//return this.getPrefHeight()/(double) mapModel.getSize();
		return 55.0;
	}
	
	public static MapView getMapView(Map mapModel) {
		int i = 0;
		MapView mapView = null;
		while(mapView == null && i < rootList.size()) {
			if(rootList.get(i).getMapModel() == mapModel)
				mapView = rootList.get(i);
		}
		return mapView;
	}
	
	private boolean canTranslateX(Movable o) {
		int[] pos = o.getPosition();
		double maxX = mapContainer.getPrefWidth();
		//System.out.println(mapContainer.getPrefWidth());
		return (pos[0]*currentMapCellSize() >= 5*currentMapCellSize() &&
				pos[0]*currentMapCellSize() <= maxX - 6*currentMapCellSize());
	}
	
	private boolean canTranslateY(Movable o) {
		int[] pos = o.getPosition();
		double maxY = mapContainer.getPrefHeight();
		return (pos[1]*currentMapCellSize() >= 5*currentMapCellSize() &&
				pos[1]*currentMapCellSize() <= maxY- 6*currentMapCellSize());
	}

}
