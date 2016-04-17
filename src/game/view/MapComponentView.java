package game.view;

import game.model.MapComponent;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MapComponentView extends StackPane {
	
	//****************************** Attributes ******************************
	
	//****************************** Constructor ******************************
	/*
	public MapComponentView(Viewable v,MapView mapView, Map mapModel) {
		this.mapView = mapView;
		
		this.setPrefWidth(mapView.cellSize());
		this.setPrefHeight(mapView.cellSize());
		//this.viewableMatrix = mapModel.getViewableMatrix();
		//this.itemList = mapModel.getItemList();
		
		initMapComponentView(v);
		
		//observableList = mapModel.getObservableList();
	}
	*/
	public MapComponentView(MapComponent mapComponent, MapView mapView) {
		this.setPrefWidth(mapView.cellSize());
		this.setPrefHeight(mapView.cellSize());
		ViewSettings viewSettings = mapComponent.getViewSettings();
		ImageView imageContainer =
				ViewUtils.initImageView(viewSettings,mapView.cellSize()*0.8);
		this.getChildren().add(imageContainer);
		mapView.addToMap(this, viewSettings.getX(), viewSettings.getY());
	}
	
	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************
	/*
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "moved"){
			updateObservablePosition(o);
			if(o instanceof Player) {
				mapView.updateWindowView(o);
			}
		}
		else if(arg == "changedDirection") {
			ImageView imageView = (ImageView) this.getChildren().get(0);
			ViewSettings viewSettings = o.getViewSettings();
			imageView.setViewport(new Rectangle2D(viewSettings.getOffsetX(), viewSettings.getOffsetY(),
					viewSettings.getWidth(), viewSettings.getHeight()));
		}
		else if(arg == "dead")
			dead = true;
		
		else if(arg instanceof Integer && o instanceof Player) {
			Player player = (Player) o;
			Skill attack = player.getSkillList().get((Integer)arg);
			int[] targetPosition = player.getLivingInFront().getPosition();
			AttackAnimation(attack, targetPosition);
		}
		
		else if(o instanceof AbstractItem) {
			transferContainer(o, (int) arg);
		}
	}
	
	@Override
	public void update(Observable o, Viewable v) {
		
	}

	@Override
	public void update(Observable o, ISkill v) {
		if(v instanceof ISkill && o instanceof Monster) {
			ISkill attack = (ISkill) v;
			Monster target = (Monster) o;
			AttackAnimation(attack, target);
			//removeLivingObservable(o);
		}
	}

	private void initMapComponentView(Viewable viewable, double imageSize) {		
		ImageView imageContainer =
				ViewUtils.initImageContainer(viewable.getViewSettings(),mapView.cellSize(), imageSize);
		//containerList.add(container);
		this.getChildren().add(imageContainer);
		int[] pos = viewable.getPosition();
		mapView.addToMap(this, pos[0], pos[1]);
		if(viewable instanceof Player) {
			mapView.centerOnTarget(viewable);
		}
		else if(viewable instanceof AbstractItem) {
			this.toBack();
		}
	}
	
	private void initMapComponentView(Viewable viewable) {
		initMapComponentView(viewable, 0.8);
	}
	
	private void initMapComponentView(Observable o, double imageSize) {		
		ImageView imageContainer =
				ViewUtils.initImageContainer(o.getViewSettings(),mapView.cellSize(), imageSize);
		this.getChildren().add(imageContainer);
	}
	
	private void initMapComponentView(Observable o) {		
		initMapComponentView(o, 0.8);
	}
	
	private void initAllViewableOnMap() {
		for(int i = 0; i < viewableMatrix.length; i++) {
			for(int j = 0; j < viewableMatrix.length; j++) {
				Viewable o = viewableMatrix[i][j];
				if(o != null) {
					initViewableView(o);
				}
			}
		}
		for(Viewable o2 : itemList) {
				initViewableView(o2,0.5);
		}
	}

	private void updateObservablePosition(Observable o) {
		int[] pos = o.getViewSettings().getPosition();
		new ObjectTranslate(Duration.millis(300), this,
				pos[0]*mapView.cellSize(), pos[1]*mapView.cellSize());
		new SpriteAnimation(o,
				(ImageView) this.getChildren().get(0), Duration.millis(300), 5, 4);
	}
	
	private void AttackAnimation(ISkill attack, Observable o) {
		ViewSettings imageSettings = attack.getViewSettings();
		ImageView imageView =
				ViewUtils.initImageContainer(imageSettings,mapView.cellSize());  //maybe imageSize to add
		StackPane container = new StackPane(imageView);
		container.setPrefSize(mapView.cellSize(),mapView.cellSize());
		mapView.getChildren().add(container);
		container.relocate(o.getViewSettings().getPosition()[0]*mapView.cellSize(),
				o.getViewSettings().getPosition()[1]*mapView.cellSize());
		MapComponentView view = this;
		SpriteAnimation anim = new SpriteAnimation(imageSettings,
				imageView, Duration.millis(300), 5, 4);
		anim.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent disappear) {
				mapView.getChildren().remove(container);
				if(dead) {
					AnchorPane n = (AnchorPane) view.getParent();
					FadeTransition t = new FadeTransition(Duration.millis(70), view);
					t.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent kill) {
							n.getChildren().remove(view);
						}
					});
					t.setToValue(0.9);
					t.setCycleCount(1);
					t.setDelay(Duration.millis(100));
					t.play();

				}
			}
		});
	}
	*/

	/*
	public StackPane getContainer(Observable entity){
		int index = observableList.indexOf(entity);
		return containerList.get(index);
	}*/
/*
	private void removeContainer(Observable observable) {
		mapView.getMapContainer().getChildren().remove(this);
	}*/
	/*
	private void removeLivingObservable(Observable o) {
		LivingBeing living= (LivingBeing) o;
		StackPane observableContainer = getContainer(o);
		FadeTransition t = new FadeTransition(Duration.millis(300), observableContainer);
		t.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent kill) {
				//living.Kill();
				removeContainer(o);
			}
		});
		t.setToValue(0.9);
		t.setCycleCount(1);
		t.setDelay(Duration.millis(100));
		t.play();
		
	}*/
	/*
	private void transferContainer(Observable o, int index) {
		StackPane container = this;
		FadeTransition t = new FadeTransition(Duration.millis(300), container);
		t.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ImageView imageView = (ImageView) container.getChildren().get(0);
				inventoryViewController.addItemView(imageView,index);
				removeContainer(o);
			}
		});
		t.setToValue(0.9);
		t.setCycleCount(1);
		t.setDelay(Duration.millis(100));
		t.play();
	}
*/
	/*public boolean isViewUpToDate() {
		boolean upToDate = true;
		int[] pos = observable.getViewSettings().getPosition();
		if(pos[0]-0.2 > containerPosX() ||
				pos[0]+0.2 < containerPosX() ||
				pos[1]-0.2 > containerPosY() ||
				pos[1]+0.2 < containerPosY()) {
			upToDate = false;

		}
		return upToDate;
	}

	public double containerPosX() {
		return this.getTranslateX() / mapView.cellSize();
	}
	
	public double containerPosY() {
		return this.getTranslateY() / mapView.cellSize();
	}
	*/
}
