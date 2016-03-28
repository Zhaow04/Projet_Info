package game.view;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Obstacle;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class ObstacleView {

	//****************************** Attributes ******************************
	
		private MapView mapView;
		private ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
		private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
		
		//****************************** Constructor ******************************
		
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
				StackPane container = initContainer(obstacle);
				int[] pos = obstacle.getPosition();
				mapView.addToMap(container, pos[0], pos[1]);
			}
		}

		private StackPane initContainer(Obstacle obstacle){
			String imageURL = obstacle.getImageURL();
			double offsetX = obstacle.getOffsetX();
			double offsetY = obstacle.getOffsetY();
			double width = obstacle.getWidth();
			double height = obstacle.getHeight();
			
			StackPane container = new StackPane();
			Image image = new Image(imageURL);
			ImageView imageContainer = new ImageView(image);
			imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
			imageContainer.setFitWidth(mapView.cellSize());
			imageContainer.setFitHeight(getMapView().cellSize()*2);
			imageContainer.setPreserveRatio(true);
			container.getChildren().add(imageContainer);
			getContainerList().add(container);
			return container;
		}
		
}
