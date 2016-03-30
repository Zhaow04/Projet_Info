package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.model.GameModel;
import game.model.LivingBeing;
import game.model.Monster;
import game.model.Movable;
import game.model.Player;
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
				updateBeingPosition((LivingBeing) o, objectContainer);
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
	
	private void updateBeingPosition(LivingBeing living, StackPane container) {
		//if(living instanceof Player) {
			updateContainer(living, container);
			int[] pos = living.getPosition();
			new Thread(){
				public void run(){
					TranslateTransition tt = new TranslateTransition(Duration.millis(500),container);
					tt.setToX(pos[0]*mapView.cellSize());
					tt.setToY(pos[1]*mapView.cellSize());
					tt.setCycleCount(1);
					tt.play();
				}
			}.start();

			if(living.getDirectionFacing() == 'N'){
				new Thread(){
					public void run(){
						final Animation animation = new SpriteAnimation(
								(ImageView) container.getChildren().get(0),
								Duration.millis(500),
								5, 4,
								0, 3*(int) (living.getHeight()),
								(int) (living.getWidth()),(int) (living.getHeight())
								) ;
						animation.setCycleCount(1);
						animation.play();
					}
				}.start();
			}
			else if(living.getDirectionFacing() == 'S'){
				new Thread(){
					public void run(){
						final Animation animation = new SpriteAnimation(
								(ImageView) container.getChildren().get(0),
								Duration.millis(500),
								5, 4,
								0, 0,
								(int) (living.getWidth()), (int) (living.getHeight())
								);
						animation.setCycleCount(1);
						animation.play();
					}
				}.start();
			}
			else if(living.getDirectionFacing() == 'W'){
				new Thread(){
					public void run(){
						final Animation animation = new SpriteAnimation(
								(ImageView) container.getChildren().get(0),
								Duration.millis(500),
								5, 4,
								0, (int) (living.getHeight()),
								(int) (living.getWidth()), (int) (living.getHeight())
								);
						animation.setCycleCount(1);
						animation.play();
					}
				}.start();
			}
			else if(living.getDirectionFacing() == 'E'){
				new Thread(){
					public void run(){
						final Animation animation = new SpriteAnimation(
								(ImageView) container.getChildren().get(0),
								Duration.millis(500),
								5, 4,
								0, 2*(int) (living.getHeight()),
								(int) (living.getWidth()), (int) (living.getHeight())
								);
						animation.setCycleCount(1);
						animation.play();
					}
				}.start();
			}
		//}
		/*
		else if(living instanceof Monster) {
			int[] pos = living.getPosition();
			Monster monster = (Monster) living;
			int[] basePos = monster.getBasePos();
			//System.out.println(container.getTranslateX() + " / " + container.getTranslateY());
			//System.out.println(pos[0] + " " + pos[1]);
			new Thread(){
				public void run(){
					TranslateTransition tt = new TranslateTransition(Duration.millis(500),container);
					System.out.println(living);
					System.out.println("     " + pos[0]*mapView.cellSize());
					System.out.println("     " + pos[1]*mapView.cellSize());
					tt.setToX(pos[0]*mapView.cellSize());
					tt.setToY(pos[1]*mapView.cellSize());
					tt.setCycleCount(1);
					tt.play();
				}
			}.start();
		}
		*/
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
	}
	
	private StackPane getContainer(Movable movable){
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
	
}
