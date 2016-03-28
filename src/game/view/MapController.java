package game.view;

import game.model.Map;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;

public class MapController {
	
	//****************************** Attributes ******************************
	
	private Map mapModel;
	private MapView mapView;
	
	private GridPane mapContainer;
	private StackPane playerContainer;
	
	//****************************** Constructor ******************************
	
	/**
	 * Constructor.
	 * Void constructor.
	 */
	public MapController(){
		
	}
	
	//initGrid();
	/*ImageView imageContainer = new ImageView(image);
	imageContainer.setFitWidth(100);
	imageContainer.setPreserveRatio(true);
	GridPane ok = (GridPane) window.getChildren().get(1);
	ok.add(imageContainer, 0, 0);*/
	//map.getRowConstraints().get(i).setPercentHeight(size);
	//map.getColumnConstraints().get(i).setPercentWidth(size);
	//System.out.println(map.getChildren());
	//map.setAlignment(Pos.CENTER);

	//************************** Getters and Setters **************************

	public Map getMapModel() {
		return mapModel;
	}

	private void setMapModel(Map mapModel){
		this.mapModel = mapModel;
	}
	
	public MapView getMapView() {
		return mapView;
	}

	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public GridPane getMapContainer(){
		return mapContainer;
	}
	
	private void setMapContainer(GridPane mapContainer) {
		this.mapContainer = mapContainer;
	}

	public StackPane getPlayerContainer(){
		return playerContainer;
	}
	
	//******************************** Methods ********************************
	
	
}
