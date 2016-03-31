package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.controller.BeingController;
import game.model.GameModel;
import game.model.LivingBeing;
import game.model.Movable;
import game.model.Player;
import game.utilities.ObjectTranslate;
import game.utilities.SpriteAnimation;
import game.utilities.ViewUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BeingView implements Observer {
	
	//****************************** Attributes ******************************
	
	private Player player;
	private MapView mapView;
	private static ArrayList<LivingBeing> beingList = new ArrayList<LivingBeing>();
	private static ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	private StackPane playerContainer;
	public static BeingController beingController = new BeingController();
	
	//****************************** Constructor ******************************
	
	public BeingView(LivingBeing living) {
		living.addObserver(this);
		if(living instanceof Player) {
			mapView = MapView.getMapView(living.getCurrentMap());
			living.addObserver(mapView);
		}
		beingList.add(living);
		beingController.setBeingView(this);
		beingController.setBeingList(beingList);
		//System.out.println(beingList);
		if(living instanceof Player) {
			beingController.setPlayer((Player) living);
		}
		mapView = MapView.getMapView(living.getCurrentMap());
		initView(living);
	}
	
	public BeingView(GameModel model, BeingController beingController, MapView mapView){
		beingList = model.getLivingList();
		player = (Player) beingList.get(0);
		for(LivingBeing living : beingList){
			living.addObserver(this);
		}
		setBeingController(beingController);
		beingController.setBeingView(this);
		beingController.setBeingList(beingList);
		beingController.setPlayer((Player) beingList.get(0));
		setMapView(mapView);
	}
	
	//************************** Getters and Setters **************************
	
	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}
	
	public MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public ArrayList<LivingBeing> getBeingList() {
		return beingList;
	}

	private void setBeingList(ArrayList<LivingBeing> livingList) {
		this.beingList = livingList;
	}
	
	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}

	public StackPane getPlayerContainer(){
		return playerContainer;
	}

	private void setPlayerContainer(StackPane playerContainer) {
		this.playerContainer = playerContainer;
	}
	
	public BeingController getBeingController() {
		return beingController;
	}

	public void setBeingController(BeingController beingController) {
		this.beingController = beingController;
	}
	
	//******************************** Methods ********************************
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Movable){
			if(arg == null){
				StackPane objectContainer = getContainer((Movable) o);
				try {
					updateBeingPosition((LivingBeing) o, objectContainer);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//int[] pos = ((Movable) o).getPosition();
				//mapView.getMapContainer().add(objectContainer, pos[0], pos[1]);
				//System.out.println(o);
			}
			else if(arg == "dead") {
				//System.out.println(mapView.getMapContainer().getChildren());
				//System.out.println(o);
				removeContainer((Movable) o);
				//mapView.getRoot().getChildren().remove(objectContainer);
				//System.out.println(getContainerList());
				//System.out.println(mapView.getMapContainer().getChildren());
				//System.out.println("dead2");
			}
		}
	}

	private void updateBeingPosition(LivingBeing living, StackPane container) throws InterruptedException {
		//if(container.getTranslateX()%mapView.cellSize() < 0.2 && container.getTranslateY()%mapView.cellSize() < 0.2) {
		//if(living instanceof Monster) {
			int[] pos = living.getPosition();
			//System.out.println(pos[0] + " "+ pos[1]);
			new ObjectTranslate(Duration.millis(300), container,
					pos[0]*mapView.currentMapCellSize(), pos[1]*mapView.currentMapCellSize());
		//}
		//if(living instanceof Player) {
		//updateContainer(living, container);
		//System.out.println("ok");
		new SpriteAnimation(living,
				(ImageView) container.getChildren().get(0), Duration.millis(300), 5, 4);

		//}
		//}
		//System.out.println(pos[0] + "+" + pos[1]);
		//container.setTranslateX(pos[0]*mapView.cellSize());
		//container.setTranslateY(pos[1]*mapView.cellSize());
		//mapView.addToMap(container, pos[0], pos[1]);
	}
	
	public void initAllObjectView(){
		for(LivingBeing living : getBeingList()){
			StackPane container = ViewUtils.initContainer(living.getImageSettings());
			int[] pos = living.getPosition();
			mapView.addToCurrentMap(container, pos[0], pos[1]);
		}
	}
	
	private void initView(LivingBeing living) {
		StackPane container = ViewUtils.initContainer(living.getImageSettings());
		getContainerList().add(container);
		int[] pos = living.getPosition();
		mapView.addToMap(container, pos[0], pos[1]);
		//System.out.println("ok");
		if(living instanceof Player) {
			mapView.centerOnTarget(living);
		}
	}
	
	/*
	private void updateContainer(LivingBeing living, StackPane container){
		String imageURL = living.getImageURL();
		double offsetX = living.getOffsetX();
		double offsetY = living.getOffsetY();
		double width = living.getWidth();
		double height = living.getHeight();
		
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(mapView.cellSize()*0.8);
		imageContainer.setFitHeight(getMapView().cellSize()*0.8);
		imageContainer.setPreserveRatio(true);
	//	imageContainer
		container.getChildren().remove(0);
		container.getChildren().add(imageContainer);
	}*/
	
	public static StackPane getContainer(Movable movable){
		int index = beingList.indexOf(movable);
		//System.out.println(index);
		return containerList.get(index);
	}
	
	// When only player left, doesn't disappear after death
	private void removeContainer(Movable movable) {
		StackPane objectContainer = getContainer(movable);
		//objectContainer.getChildren().clear();
		mapView.getMapContainer().getChildren().remove(objectContainer);
		getContainerList().remove(objectContainer);
		getBeingList().remove(movable);
		//System.out.println(getBeingList());
		//System.out.println(objectContainer.getChildren());
		//System.gc();
	}
	
	public boolean isViewUpToDate() {
		boolean upToDate = true;
		int i = 0;
		while(upToDate && i < getContainerList().size()){
			StackPane container = getContainerList().get(i);
			if(container.getTranslateX() % mapView.currentMapCellSize() > mapView.currentMapCellSize() ||
					container.getTranslateY()%mapView.currentMapCellSize() > mapView.currentMapCellSize()) {
				upToDate = false;
			}
			i++;
		}
		return upToDate;
	}
	
	public static double containerPosX(StackPane container) {
		return container.getTranslateX() / MapView.currentMapCellSize();
	}
	
	public static double containerPosY(StackPane container) {
		return container.getTranslateY() / MapView.currentMapCellSize();
	}
	
}
