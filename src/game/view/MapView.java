package game.view;

import java.util.ArrayList;

import game.controller.GameController;
import game.model.Map;
import game.model.MapComponent;
import game.model.Monster;
import game.model.Observable;
import game.model.Player;
import game.model.item.Item;
import game.utilities.ObjectTranslate;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Implements {@code Observer}. <br/>
 * Extends from {@code Group}. <br/>
 * View of the map. Only a portion of the map is visible on the screen.
 * 
 * @author ZhaoWen
 *
 */
public class MapView extends Group implements Observer {
	
	//****************************** Attributes ******************************
	
	public GameView gameView;
	private Map mapModel;
	private Rectangle clip;
	
	private Group allLayers;
	private AnchorPane bottomLayer;
	private AnchorPane stepOnLayer;
	private AnchorPane interactiveLayer;
		
	//****************************** Constructor ******************************
	
	/**
	 * Creates the view of the map including everything on it (monster, item,...) except the player.
	 * @param mapModel
	 * @param gameView
	 * @param gameController
	 */
	public MapView(Map mapModel, GameView gameView, GameController gameController) {
		super();
		this.mapModel = mapModel;
		mapModel.addObserver(this);
		this.gameView = gameView;
		
		initBottomLayer(mapModel.getMapCompos());
		initStepOnLayer(mapModel.getItems());
		initInteractiveLayer(mapModel.getMonsters());
		allLayers = new Group(bottomLayer,stepOnLayer,interactiveLayer);
		clip = new Rectangle(960, 540);
		allLayers.setClip(clip);
		this.getChildren().add(allLayers);
	}
	
	//************************** Getters and Setters **************************
	
	
	//******************************** Methods ********************************
	
	/**
	 * Adds multiple {@code MonsterView} to this {@code MapView}. This method is to be used after all
	 * the monsters present on the map have been defeated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "addMonsters") {
			Platform.runLater(() -> {
				for(Monster monster : mapModel.getMonsters()) {
					add(new MonsterView(monster, this));
					//new Thread(monster).start();
				}
				mapModel.startThreads();
			});
		}
	}
	
	/**
	 * Creates an {@code AnchorPane}. The preferred size is given by the size of one cell (defined in
	 * {@link #cellSize()}) multiplied by the size of the map (e.g. one cell is 60x60 pixels and the size
	 * of the map is 10 so the preferred size would be 600x600)
	 * @return empty layer
	 */
	private AnchorPane initLayer() {
		AnchorPane layer = new AnchorPane();
		double size = mapModel.getSize()*cellSize();
		layer.setPrefSize(size, size);
		return layer;
	}

	/**
	 * Creates the bottom layer. This layer is used for all the components that do not require any later
	 * change. Contains all the {@code MapComponent}.
	 * @param mapComponents
	 */
	private void initBottomLayer(ArrayList<MapComponent> mapComponents){
		bottomLayer = initLayer();
		for(MapComponent o : mapComponents) {
			add(new MapComponentView(o,cellSize()));
		}
		Image image = new Image(mapModel.getViewSettings().getImageURL());
		BackgroundImage backimage = new BackgroundImage(image, null, null, null, null);
		bottomLayer.setBackground(new Background(backimage));
	}
	
	/**
	 * Creates the intermediate layer. This layer is used for all the components that require later
	 * changes and need to situated at an intermediate level. Contains all the {@code Item}.
	 * @param items
	 */
	private void initStepOnLayer(ArrayList<Item> items) {
		stepOnLayer = initLayer();
		for(Item item : items) {
			add(new ItemView(item,this));
		}
	}
	
	/**
	 * Creates the top layer. This layer is used for all the components that require later
	 * changes and need to situated at the top level. Contains all the {@code Monster} and the
	 * {@code Player}.
	 * @param monsters
	 */
	private void initInteractiveLayer(ArrayList<Monster> monsters) {
		interactiveLayer = initLayer();
		for(Monster monster : monsters) {
			add(new MonsterView(monster, this));
		}
	}
	
	/**
	 * Adds a {@code MapComponentView} to the bottom layer ({@code bottomLayer}).
	 * @param child
	 * @param column
	 * @param row
	 */
	private void add(MapComponentView child) {
		bottomLayer.getChildren().add(child);
	}
	
	/**
	 * Adds a {@code SkillView} to the {@code Group} containing all the layers.
	 * @param child
	 * @param column
	 * @param row
	 */
	public void add(SkillView child) {
		allLayers.getChildren().add(child);
	}
	
	/**
	 * Removes the specified {@code SkillView} from the {@code Group} containing all the layers.
	 * @param skillView
	 */
	public void remove(SkillView skillView) {
		allLayers.getChildren().remove(skillView);
	}
	
	/**
	 * Adds a {@code ItemView} to the intermediate layer ({@code stepOnLayer}).
	 * @param child
	 * @param column
	 * @param row
	 */
	private void add(ItemView child) {
		stepOnLayer.getChildren().add(child);
	}
	
	/**
	 * Removes the specified {@code ItemView} from the intermediate layer ({@code stepOnLayer}).
	 * @param itemView
	 */
	public void remove(ItemView itemView) {
		stepOnLayer.getChildren().remove(itemView);
	}
	
	/**
	 * Adds a {@code MonsterView} to the top layer ({@code interactiveLayer}).
	 * @param child
	 * @param column
	 * @param row
	 */
	private void add(MonsterView child) {
		interactiveLayer.getChildren().add(child);
	}
	
	/**
	 * Removes the specified {@code MonsterView} from the top layer ({@code interactiveLayer}).
	 * @param itemView
	 */
	public void remove(MonsterView itemView) {
		interactiveLayer.getChildren().remove(itemView);
	}
	
	/**
	 * Adds a {@code PlayerView} to the top layer ({@code interactiveLayer}).
	 * @param child
	 * @param column
	 * @param row
	 */
	public void add(PlayerView child) {
		interactiveLayer.getChildren().add(child);
	}
	/**
	 * Removes the specified {@code PlayerView} from the top layer ({@code interactiveLayer}).
	 * @param itemView
	 */
	public void remove(PlayerView itemView) {
		interactiveLayer.getChildren().remove(itemView);
	}
	
	/**
	 * Updates the view of the map, i.e. displays a portion of the map centered on the player.
	 * @param player
	 */
	public void updateWindowView(Player player) {
		double translateX = getTranslateX(player);
		double translateY = getTranslateY(player);
		new ObjectTranslate(Duration.millis(300), allLayers.getClip(),
				translateX, translateY);
		/*
		int[] pos = player.getViewSettings().getPosition();
		if(canTranslateX(player) && !canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), allLayers.getClip(),
					(5-pos[0])*cellSize(), "x");
		}
		else if(!canTranslateX(player) && canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), allLayers.getClip(),
					(5-pos[1])*cellSize(), "y");
		}
		else if(canTranslateX(player) && canTranslateY(player)) {
			new ObjectTranslate(Duration.millis(300), allLayers.getClip(),
					(5-pos[0])*cellSize(), (5-pos[1])*cellSize());
		}*/
	}
	
	/**
	 * Gets the x coordinate for translation.
	 * @param player
	 * @return x coordinate
	 */
	private double getTranslateX(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		double translateX;
		if((pos[0]+0.5)*cellSize() - clip.getWidth()/2 < 0)
			translateX = 0;
		else if ((pos[0]+0.5)*cellSize() + clip.getWidth()/2 > bottomLayer.getPrefWidth())
			translateX = bottomLayer.getPrefWidth() - clip.getWidth();
		else
			translateX = (pos[0]+0.5)*cellSize() - clip.getWidth()/2;
		return translateX;
	}
	
	/**
	 * Gets the y coordinate for translation.
	 * @param player
	 * @return y coordinate
	 */
	private double getTranslateY(Player player) {
		int[] pos = player.getViewSettings().getPosition();
		double translateY;
		if((pos[1]+0.5)*cellSize() - clip.getHeight()/2 < 0)
			translateY = 0;
		else if ((pos[1]+0.5)*cellSize() + clip.getHeight()/2 > bottomLayer.getPrefHeight())
			translateY = bottomLayer.getPrefHeight() - clip.getHeight();
		else
			translateY = (pos[1]+0.5)*cellSize() - clip.getHeight()/2;
		return translateY;
	}
	/*
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
	}*/
	
	/**
	 * Returns the size of one cell of the map (currently 55.0).
	 * @return size of one cell
	 */
	public double cellSize() {
		return 60.0;
	}
	/*
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
	}*/

}
