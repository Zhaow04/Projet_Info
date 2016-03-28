package game.view;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.SafeHouse;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class SafeHouseView {

	//****************************** Attributes ******************************
	
		private MapView mapView;
		private ArrayList<SafeHouse> SafeHouseList = new ArrayList<SafeHouse>();
		private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
		
		//****************************** Constructor ******************************
		
		public SafeHouseView(GameModel model, MapView mapView){
			ArrayList<SafeHouse> SafeHouseList = model.getSafeHouseList();
			setSafeHouseList(SafeHouseList);
			setMapView(mapView);
		}
		
		//************************** Getters and Setters **************************
		
		public MapView getMapView() {
			return mapView;
		}

		private void setMapView(MapView mapView) {
			this.mapView = mapView;
		}

		public ArrayList<SafeHouse> getSafeHouseList() {
			return SafeHouseList;
		}

		private void setSafeHouseList(ArrayList<SafeHouse> SafeHouseList) {
			this.SafeHouseList = SafeHouseList;
		}
		
		public ArrayList<StackPane> getContainerList() {
			return containerList;
		}

		
		//******************************** Methods ********************************
		
		
		public void initAllObjectView(){
			for(SafeHouse SafeHouse : getSafeHouseList()){
				StackPane container = initContainer(SafeHouse);
				int[] pos = SafeHouse.getPosition();
				mapView.addToMap(container, pos[0], pos[1]);
			}
		}

		private StackPane initContainer(SafeHouse SafeHouse){
			String imageURL = SafeHouse.getImageURL();
			double offsetX = SafeHouse.getOffsetX();
			double offsetY = SafeHouse.getOffsetY();
			double width = SafeHouse.getWidth();
			double height = SafeHouse.getHeight();
			
			StackPane container = new StackPane();
			Image image = new Image(imageURL);
			ImageView imageContainer = new ImageView(image);
			imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
			imageContainer.setFitWidth(mapView.cellSize());
			imageContainer.setFitHeight(getMapView().cellSize());
			imageContainer.setPreserveRatio(true);
			container.getChildren().add(imageContainer);
			getContainerList().add(container);
			return container;
		}
		
}
