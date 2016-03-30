package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.controller.ItemController;
import game.model.GameModel;
import game.model.Item;
import game.model.LivingBeing;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ItemView implements Observer {

	//****************************** Attributes ******************************
	
	private ItemController itemController;
	
	private MapView mapView;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	
	//****************************** Constructor ******************************

	public ItemView(GameModel model, ItemController itemController, MapView mapView) {
		this.itemController = itemController;
		itemController.setItemView(this);
		ArrayList<Item> itemList = model.getItemList();
		setItemList(itemList);
		for(Item item : itemList){
			item.addObserver(this);
		}
		setMapView(mapView);
	}
	
	//************************** Getters and Setters **************************

	public MapView getMapView() {
		return mapView;
	}

	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}

	public void setContainerList(ArrayList<StackPane> containerList) {
		this.containerList = containerList;
	}
	
	//******************************** Methods ********************************

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void initAllItemView(){
		for(Item item : getItemList()){
			StackPane container = initContainer(item);
			int[] pos = item.getPosition();
			mapView.addToMap(container, pos[0], pos[1]);
			container.toBack();
			//itemController.getInventoryController().addItemView((ImageView) container.getChildren().get(0));
		}
	}
	
	private StackPane initContainer(Item item){
		String imageURL = item.getImageURL();
		double offsetX = item.getOffsetX();
		double offsetY = item.getOffsetY();
		double width = item.getWidth();
		double height = item.getHeight();
		
		StackPane container = new StackPane();
		container.setPrefWidth(mapView.cellSize());
		container.setPrefHeight(mapView.cellSize());
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(mapView.cellSize()*0.5);
		imageContainer.setFitHeight(getMapView().cellSize()*0.5);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
		getContainerList().add(container);
		return container;
	}

}
