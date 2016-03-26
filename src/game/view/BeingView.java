package game.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import game.model.GameModel;
import game.model.LivingBeing;
import game.model.Movable;
import game.model.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
			StackPane objectContainer = getContainer((Movable) o);
			mapView.getMapContainer().getChildren().remove(objectContainer);
			updateBeingView((LivingBeing) o, objectContainer);
			//int[] pos = ((Movable) o).getPosition();
			//mapView.getMapContainer().add(objectContainer, pos[0], pos[1]);
			//System.out.println("updateView");
		}
	}
	
	private void updateBeingView(LivingBeing living, StackPane container){
		updateContainer(living, container);
		int[] pos = living.getPosition();
		mapView.getMapContainer().add(container, pos[0], pos[1]);
	}
	
	public void initAllObjectView(){
		for(LivingBeing living : getBeingList()){
			StackPane container = initContainer(living);
			int[] pos = living.getPosition();
			mapView.getMapContainer().add(container, pos[0], pos[1]);
		}
	}

	private StackPane initContainer(LivingBeing living){
		String imageURL = living.getImageURL();
		double offsetX = living.getOffsetX();
		double offsetY = living.getOffsetY();
		double width = living.getWidth();
		double height = living.getHeight();
		
		StackPane container = new StackPane();
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(mapView.cellSize());
		imageContainer.setFitHeight(getMapView().cellSize()*0.8);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
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
		imageContainer.setFitWidth(mapView.cellSize());
		imageContainer.setFitHeight(getMapView().cellSize()*0.8);
		imageContainer.setPreserveRatio(true);
		container.getChildren().remove(0);
		container.getChildren().add(imageContainer);
	}
	
	private StackPane getContainer(Movable movable){
		int index = getBeingList().indexOf(movable);
		return getContainerList().get(index);
	}
	
}
