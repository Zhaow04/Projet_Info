package game.view;

import java.util.ArrayList;

import game.controller.GameController;
import game.model.Map;
import game.model.MapComponent;
import game.model.Player;
import game.model.item.Item;
import game.model.monster.Monster;
import game.utilities.ObjectTranslate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Duration;

/**
 * Implements {@code Observer}. <br/>
 * 
 * 
 * @author ZhaoWen
 *
 */
public class MapView extends Group {
	
	//****************************** Attributes ******************************
	
	private AnchorPane bottomLayer;
	private AnchorPane stepOnLayer;
	private AnchorPane interactiveLayer;
	private Map mapModel;
	private GameView gameView;
	
	private ArrayList<MapComponentView> mapComponentViews = new ArrayList<MapComponentView>();
	
	//****************************** Constructor ******************************
	
	public MapView(Map mapModel, GameView gameView, GameController gameController) {
		super();
		
		setMapModel(mapModel);
		setGameView(gameView);
		
		//this.setPrefSize(mapModel.getSize()*cellSize(), mapModel.getSize()*cellSize());
		
		initBottomLayer(mapModel.getMapCompos());
		initStepOnLayer(mapModel.getItems());
		initInteractiveLayer(mapModel.getMonsters());
	}
	
	//************************** Getters and Setters **************************
	
	public Map getMapModel() {
		return mapModel;
	}
	
	private void setMapModel(Map mapModel) {
		this.mapModel = mapModel;
	}

	public GameView getGameView() {
		return gameView;
	}

	private void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public ArrayList<MapComponentView> getMapComponentViews() {
		return mapComponentViews;
	}
	
	//******************************** Methods ********************************
	
	private AnchorPane initLayer() {
		AnchorPane layer = new AnchorPane();
		int mapSize = mapModel.getSize();
		layer.setPrefSize(cellSize()*mapSize, cellSize()*mapSize);
		return layer;
	}
	
	private void initBottomLayer(ArrayList<MapComponent> mapComponents){
		bottomLayer = initLayer();
		for(MapComponent o : mapComponents) {
			new MapComponentView(o,this);
		}
		Image image = new Image(mapModel.getViewSettings().getImageURL());
		BackgroundImage backimage = new BackgroundImage(image, null, null, null, null);
		bottomLayer.setBackground(new Background(backimage));
		this.getChildren().add(bottomLayer);
	}
	
	private void initStepOnLayer(ArrayList<Item> items) {
		stepOnLayer = initLayer();
		for(Item item : items) {
			new ItemView(item,this);
		}
		this.getChildren().add(stepOnLayer);
	}
	
	private void initInteractiveLayer(ArrayList<Monster> monsters) {
		interactiveLayer = initLayer();
		for(Monster monster : monsters) {
			new MonsterView(monster, this);
		}
		this.getChildren().add(interactiveLayer);
	}
	
	public void addToMap(MapComponentView child, int column, int row) {
		bottomLayer.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	/*
	public void remove(MapComponentView itemView) {
		bottomLayer.getChildren().remove(itemView);
	}*/
	
	public void addToMap(SkillView child, int column, int row) {
		this.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void remove(SkillView itemView) {
		this.getChildren().remove(itemView);
	}
	
	public void addToMap(ItemView child, int column, int row) {
		stepOnLayer.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void remove(ItemView itemView) {
		stepOnLayer.getChildren().remove(itemView);
	}
	
	public void addToMap(MonsterView child, int column, int row) {
		interactiveLayer.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void remove(MonsterView itemView) {
		interactiveLayer.getChildren().remove(itemView);
	}
	
	public void addToMap(PlayerView child, int column, int row) {
		interactiveLayer.getChildren().add(child);
		double x = column*cellSize();
		double y = row*cellSize();
		child.setTranslateX(x);
		child.setTranslateY(y);
	}
	
	public void remove(PlayerView itemView) {
		interactiveLayer.getChildren().remove(itemView);
	}
	
	public void updateWindowView(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		if(canTranslateX(player) && !canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), this,
					(5-pos[0])*cellSize(), "x");
		}
		else if(!canTranslateX(player) && canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), this,
					(5-pos[1])*cellSize(), "y");
		}
		else if(canTranslateX(player) && canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), this,
					(5-pos[0])*cellSize(), (5-pos[1])*cellSize());
		}
	}
	
	public void centerOnTarget(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		if(canTranslateX(player) && !canTranslateY(player)) {
			System.out.println("ok");
			double x = (5-pos[0])*cellSize();
			this.setTranslateX(x);
		}
		else if(!canTranslateX(player) && canTranslateY(player)) {
			System.out.println("ok");
			double y = (5-pos[1])*cellSize();
			this.setTranslateY(y);
		}
		else if(canTranslateX(player) && canTranslateY(player)) {
			System.out.println("ok");
			double x = (5-pos[0])*cellSize();
			double y = (5-pos[1])*cellSize();
			this.setTranslateX(x);
			this.setTranslateY(y);
		}
	}
	
	public double cellSize() {
		return 55.0;
	}
	
	private boolean canTranslateX(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		double maxX = bottomLayer.getPrefWidth();
		return (pos[0]*cellSize() >= 5*cellSize() &&
				pos[0]*cellSize() <= maxX - 6*cellSize());
	}
	
	private boolean canTranslateY(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		double maxY = bottomLayer.getPrefHeight();
		return (pos[1]*cellSize() >= 5*cellSize() &&
				pos[1]*cellSize() <= maxY- 6*cellSize());
	}
	
	public boolean isViewUpToDate() {
		return true;// mapComponentView.isViewUpToDate();
	}

}
