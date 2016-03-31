package game.view;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Obstacle;
import game.utilities.ViewUtils;
import javafx.scene.layout.StackPane;


public class ObstacleView {

	//****************************** Attributes ******************************

	private MapView mapView;
	private ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();

	//****************************** Constructor ******************************
	
	public ObstacleView(Obstacle obstacle) {
		mapView = MapView.getMapView(obstacle.getCurrentMap());
		initView(obstacle);
	}
	
	public ObstacleView(GameModel model, MapView mapView){
		ArrayList<Obstacle> obstacleList = model.getObstacleList();
		setObstacleList(obstacleList);
		setMapView(mapView);
	}

	//************************** Getters and Setters **************************

	public MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public ArrayList<Obstacle> getObstacleList() {
		return obstacleList;
	}

	private void setObstacleList(ArrayList<Obstacle> obstacleList) {
		this.obstacleList = obstacleList;
	}

	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}


	//******************************** Methods ********************************


	public void initAllObjectView(){
		for(Obstacle obstacle : getObstacleList()){
			StackPane container = ViewUtils.initContainer(obstacle.getImageSettings());
			int[] pos = obstacle.getPosition();
			mapView.addToMap(container, pos[0], pos[1]);
		}
	}
	
	public void initView(Obstacle obstacle) {
		StackPane container = ViewUtils.initContainer(obstacle.getImageSettings());
		getContainerList().add(container);
		int[] pos = obstacle.getPosition();
		mapView.addToMap(container, pos[0], pos[1]);
	}

}
