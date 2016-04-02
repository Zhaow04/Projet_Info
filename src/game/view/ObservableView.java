package game.view;

import java.util.ArrayList;

import game.model.Item;
import game.model.Observable;
import game.model.Player;
import game.utilities.ObjectTranslate;
import game.utilities.SpriteAnimation;
import game.utilities.ViewUtils;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ObservableView implements Observer {
	
	//****************************** Attributes ******************************
	
	private InventoryViewController inventoryViewController;
	
	private MapView mapView;
	private Observable[][] observableMatrix;
	private Item[][] itemOnMap;
	
	private ArrayList<Observable> observableList = new ArrayList<Observable>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	
	//****************************** Constructor ******************************
	
	public ObservableView(MapView mapView, Observable[][] observableMatrix, Item[][] itemOnMap) {
		this.mapView = mapView;
		this.observableMatrix = observableMatrix;
		this.itemOnMap = itemOnMap;
		
		initAllObservableOnMap();
	}
	/*
	public ObservableView(Observable entity) {
		entity.addObserver(this);
		if(entity instanceof Player) {
			mapView = MapView.getMapView(entity.getCurrentMap());
			entity.addObserver(mapView);
		}
		observableList.add(entity);
		
		mapView = MapView.getMapView(entity.getCurrentMap());
		initView(entity);
	}*/
	
	//************************** Getters and Setters **************************
	
	public void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}
	
	public MapView getMapView() {
		return mapView;
	}

	public ArrayList<Observable> getObservableList() {
		return observableList;
	}
	
	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg == null){
			StackPane objectContainer = getContainer(o);
			updateObservablePosition(o, objectContainer);
			if(o instanceof Player) {
				mapView.updateWindowView(o);
			}
		}
		else if(arg == "dead") {
			removeContainer(o);
		}
		else if(o instanceof Item) {
			transferContainer(o, (int) arg);
		}
	}
	
	private void initObservableView(Observable observable, double imageSize) {
		observable.addObserver(this);
		observableList.add(observable);
		
		StackPane container =
				ViewUtils.initContainer(observable.getImageSettings(),mapView.cellSize(), imageSize);
		containerList.add(container);
		int[] pos = observable.getPosition();
		mapView.addToMap(container, pos[0], pos[1]);
		if(observable instanceof Player) {
			mapView.centerOnTarget(observable);
		}
		else if(observable instanceof Item) {
			container.toBack();
		}
	}
	
	private void initObservableView(Observable observable) {
		this.initObservableView(observable, 0.8);
	}
	
	private void initAllObservableOnMap() {
		for(int i = 0; i < observableMatrix.length; i++) {
			for(int j = 0; j < observableMatrix.length; j++) {
				Observable o = observableMatrix[i][j];
				if(o != null) {
					initObservableView(o);
				}
				Observable o2 = itemOnMap[i][j];
				if(o2 != null) {
					initObservableView(o2,0.5);
				}
			}
		}
	}
	
	private void updateObservablePosition(Observable o, StackPane container) {
		int[] pos = o.getPosition();
		new ObjectTranslate(Duration.millis(300), container,
				pos[0]*mapView.cellSize(), pos[1]*mapView.cellSize());
		new SpriteAnimation(o,
				(ImageView) container.getChildren().get(0), Duration.millis(300), 5, 4);
	}
	
	public StackPane getContainer(Observable entity){
		int index = observableList.indexOf(entity);
		return containerList.get(index);
	}

	private void removeContainer(Observable observable) {
		StackPane objectContainer = getContainer(observable);
		mapView.getMapContainer().getChildren().remove(objectContainer);
		containerList.remove(objectContainer);
		observableList.remove(observable);
	}
	
	private void transferContainer(Observable o, int index) {
		StackPane observableContainer = getContainer(o);
		FadeTransition t = new FadeTransition(Duration.millis(300), observableContainer);
		t.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ImageView imageView = (ImageView) observableContainer.getChildren().get(0);
				inventoryViewController.addItemView(imageView,index);
				removeContainer(o);
			}
		});
		t.setToValue(0.9);
		t.setCycleCount(1);
		t.setDelay(Duration.millis(100));
		t.play();
	}

	public boolean isViewUpToDate() {
		boolean upToDate = true;
		int i = 0;
		while(upToDate && i < containerList.size()){
			Observable observable = observableList.get(i);
			int[] pos = observable.getPosition();
			StackPane container = containerList.get(i);
			if(pos[0]-0.2 > containerPosX(container) ||
					pos[0]+0.2 < containerPosX(container) ||
					pos[1]-0.2 > containerPosY(container) ||
					pos[1]+0.2 < containerPosY(container)) {
				upToDate = false;
			}
			i++;
		}
		return upToDate;
	}

	public double containerPosX(StackPane container) {
		return container.getTranslateX() / mapView.cellSize();
	}
	
	public double containerPosY(StackPane container) {
		return container.getTranslateY() / mapView.cellSize();
	}
	
}
