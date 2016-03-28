package game.view;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Dungeon;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class DungeonView {

	//****************************** Attributes ******************************
	
		private MapView mapView;
		private ArrayList<Dungeon> DungeonList = new ArrayList<Dungeon>();
		private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
		
		//****************************** Constructor ******************************
		
		public DungeonView(GameModel model, MapView mapView){
			ArrayList<Dungeon> DungeonList = model.getDungeonList();
			setDungeonList(DungeonList);
			setMapView(mapView);
		}
		
		//************************** Getters and Setters **************************
		
		public MapView getMapView() {
			return mapView;
		}

		private void setMapView(MapView mapView) {
			this.mapView = mapView;
		}

		public ArrayList<Dungeon> getDungeonList() {
			return DungeonList;
		}

		private void setDungeonList(ArrayList<Dungeon> DungeonList) {
			this.DungeonList = DungeonList;
		}
		
		public ArrayList<StackPane> getContainerList() {
			return containerList;
		}

		
		//******************************** Methods ********************************
		
		
		public void initAllObjectView(){
			for(Dungeon Dungeon : getDungeonList()){
				StackPane container = initContainer(Dungeon);
				int[] pos = Dungeon.getPosition();
				mapView.addToMap(container, pos[0], pos[1]);
			}
		}

		private StackPane initContainer(Dungeon Dungeon){
			String imageURL = Dungeon.getImageURL();
			double offsetX = Dungeon.getOffsetX();
			double offsetY = Dungeon.getOffsetY();
			double width = Dungeon.getWidth();
			double height = Dungeon.getHeight();
			
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
