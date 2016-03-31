package game.controller;

import game.model.Map;
import game.view.MapView;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;

public class MapController {
	
	//****************************** Attributes ******************************
	
	private Map mapModel;
	private MapView mapView;
	
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

	public void setMapModel(Map mapModel){
		this.mapModel = mapModel;
	}
	
	public MapView getMapView() {
		return mapView;
	}

	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}
	
	//******************************** Methods ********************************
	
	
}
