package game.view;

import java.util.Observable;
import java.util.Observer;

import game.model.GameModel;
import game.model.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Implements {@code Observer}. <br/>
 * 
 * 
 * @author ZhaoWen
 *
 */
public class MapView implements Observer {
	
	//****************************** Attributes ******************************
	
	private AnchorPane root;
	private GridPane mapContainer;
	private Map mapModel;
	private MapController mapController;
	
	//****************************** Constructor ******************************
	
	public MapView(GameModel model, MapController mapController){
		Map mapModel = model.getMap();
		setMapModel(mapModel);
		mapModel.addObserver(this);
		setMapController(mapController);
		mapController.setMapView(this);
		initMapView();
	}
	
	//************************** Getters and Setters **************************
	
	public AnchorPane getRoot() {
		return root;
	}
	
	private void setRoot(AnchorPane root) {
		this.root = root;
	}

	public GridPane getMapContainer(){
		return mapContainer;
	}
	
	private void setMapContainer(GridPane mapContainer) {
		this.mapContainer = mapContainer;
	}
	
	public Map getMapModel() {
		return mapModel;
	}

	private void setMapModel(Map mapModel) {
		this.mapModel = mapModel;
	}

	public MapController getMapController() {
		return mapController;
	}

	private void setMapController(MapController mapController) {
		this.mapController = mapController;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	private void initRoot(){
		AnchorPane root = new AnchorPane();
		setRoot(root);
		root.setPrefSize(600, 600);
	}
	
	private void initGrid(){
		GridPane mapContainer = new GridPane();
		setMapContainer(mapContainer);
		AnchorPane root = getRoot();
		root.getChildren().add(mapContainer);
		AnchorPane.setTopAnchor(mapContainer, 0.0);
		AnchorPane.setBottomAnchor(mapContainer, 0.0);
		AnchorPane.setLeftAnchor(mapContainer, 0.0);
		AnchorPane.setRightAnchor(mapContainer, 0.0);
	}
	
	private void initMapView(){
		initRoot();
		initGrid();
		GridPane mapContainer = getMapContainer();
		int size = getMapModel().getSize();
		Image image = new Image(getMapModel().getImageURL());
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				ImageView imageContainer = new ImageView(image);
				imageContainer.setFitWidth(cellSize());
				imageContainer.setFitHeight(cellSize());
				//imageContainer.setPreserveRatio(true);
				mapContainer.add(imageContainer, i, j);
			}
		}
	}
	
	public double cellSize(){
		return getRoot().getPrefHeight()/(double) getMapModel().getSize();
	}

}
