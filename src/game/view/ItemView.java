package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.controller.ItemController;
import game.model.GameModel;
import game.model.Item;
import game.utilities.ViewUtils;
import javafx.scene.layout.StackPane;

public class ItemView implements Observer {

	//****************************** Attributes ******************************
	
	private ItemController itemController;
	
	private MapView mapView;
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	
	//****************************** Constructor ******************************
	
	public ItemView(Item item) {
		item.addObserver(this);
		itemList.add(item);
		mapView = MapView.getMapView(item.getCurrentMap());
		initView(item);
	}
	
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
		if(arg == "removed") {
			removeContainerView((Item) o);
		}
		else if(arg == "inventory") {
			
		}
	}
	
	public void initAllItemView(){
		for(Item item : getItemList()){
			StackPane container = ViewUtils.initContainer(item.getImageSettings());
			int[] pos = item.getPosition();
			mapView.addToMap(container, pos[0], pos[1]);
			container.toBack();
			//itemController.getInventoryController().addItemView((ImageView) container.getChildren().get(0));
		}
	}
	
	public void initView(Item item) {
		StackPane container = ViewUtils.initContainer(item.getImageSettings(),0.5);
		getContainerList().add(container);
		int[] pos = item.getPosition();
		mapView.addToMap(container, pos[0], pos[1]);
		container.toBack();
	}
	
	public static StackPane getContainer(Item item){
		int index = itemList.indexOf(item);
		return containerList.get(index);
	}
	
	public static void removeContainer(Item item){
		int index = itemList.indexOf(item);
		containerList.remove(index);
		itemList.remove(item);
	}
	
	private void removeContainerView(Item item) {
		StackPane itemContainer = getContainer(item);
		mapView.getMapContainer().getChildren().remove(itemContainer);
	}

}
