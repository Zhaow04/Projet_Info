package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.controller.BeingController;
import game.model.GameModel;
import game.model.LivingBeing;
import game.model.Monster;
import game.model.Movable;
import game.model.Player;
import game.utilities.ObjectTranslate;
import game.utilities.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BeingView implements Observer {
	
	//****************************** Attributes ******************************
	
	private Player player;
	private MapView mapView;
	private ArrayList<LivingBeing> livingList = new ArrayList<LivingBeing>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	private StackPane playerContainer;
	private BeingController beingController;
	
	//****************************** Constructor ******************************
	
	public BeingView(GameModel model, BeingController beingController, MapView mapView){
		ArrayList<LivingBeing> livingList = model.getLivingList();
		setBeingList(livingList);
		setPlayer((Player) livingList.get(0));
		for(LivingBeing living : livingList){
			living.addObserver(this);
		}
		setBeingController(beingController);
		beingController.setBeingView(this);
		beingController.setBeingList(livingList);
		beingController.setPlayer((Player) livingList.get(0));
		for(LivingBeing living : livingList){
			living.setBeingController(beingController);
		}
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
		return livingList;
	}

	private void setBeingList(ArrayList<LivingBeing> livingList) {
		this.livingList = livingList;
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
				//mapView.getRoot().getChildren().remove(objectContainer);
				StackPane objectContainer = getContainer((Movable) o);
				try {
					updateBeingPosition((LivingBeing) o, objectContainer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
				//System.gc();
				//System.out.println(mapView.getMapContainer().getChildren());
				//System.out.println(getContainerList());
				//System.out.println(mapView.getMapContainer().getChildren());
				//System.out.println("dead2");
			}
		}
	}
	
	private void updateBeingPosition(LivingBeing living, StackPane container) throws InterruptedException {
		//if(container.getTranslateX()%mapView.cellSize() < 0.2 && container.getTranslateY()%mapView.cellSize() < 0.2) {
		int[] pos = living.getPosition();
		//System.out.println(pos[0] + " "+ pos[1]);
		new ObjectTranslate(Duration.millis(400), container,
				pos[0]*mapView.cellSize(), pos[1]*mapView.cellSize());
		//if(living instanceof Player) {
			//updateContainer(living, container);
			new SpriteAnimation(living,
					(ImageView) container.getChildren().get(0), Duration.millis(400), 5, 4);

		//}
		//}
		//System.out.println(pos[0] + "+" + pos[1]);
		//container.setTranslateX(pos[0]*mapView.cellSize());
		//container.setTranslateY(pos[1]*mapView.cellSize());
		//mapView.addToMap(container, pos[0], pos[1]);
	}
	
	public void initAllObjectView(){
		for(LivingBeing living : getBeingList()){
			StackPane container = initContainer(living);
			int[] pos = living.getPosition();
			mapView.addToMap(container, pos[0], pos[1]);
		}
	}

	private StackPane initContainer(LivingBeing living){
		String imageURL = living.getImageURL();
		double offsetX = living.getOffsetX();
		double offsetY = living.getOffsetY();
		double width = living.getWidth();
		double height = living.getHeight();
		
		StackPane container = new StackPane();
		container.setPrefWidth(mapView.cellSize());
		container.setPrefHeight(mapView.cellSize());
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(mapView.cellSize()*0.8);
		imageContainer.setFitHeight(getMapView().cellSize()*0.8);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
		/*Rectangle ok =  new Rectangle(mapView.cellSize()*0.8, getMapView().cellSize()*0.8);
		ok.setFill(Color.BLACK);
		container.getChildren().add(ok);*/
		getContainerList().add(container);
		return container;
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
	
	public StackPane getContainer(Movable movable){
		int index = getBeingList().indexOf(movable);
		//System.out.println(index);
		return getContainerList().get(index);
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
			if(container.getTranslateX() % mapView.cellSize() > mapView.cellSize() ||
					container.getTranslateY()%mapView.cellSize() > mapView.cellSize()) {
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
