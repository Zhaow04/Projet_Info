package game.view;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.SafeHouse;
import game.utilities.ViewUtils;
import javafx.scene.layout.StackPane;


public class SafeHouseView {

	//****************************** Attributes ******************************

	private MapView mapView;
	private ArrayList<SafeHouse> SafeHouseList = new ArrayList<SafeHouse>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();

	//****************************** Constructor ******************************

	public SafeHouseView(SafeHouse safeHouse) {
		mapView = MapView.getMapView(safeHouse.getCurrentMap());
		initView(safeHouse);
	}

	public SafeHouseView(GameModel model, MapView mapView){
		ArrayList<SafeHouse> SafeHouseList = model.getSafeHouseList();
		setSafeHouseList(SafeHouseList);
		setMapView(mapView);
	}

	//************************** Getters and Setters **************************

	public MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public ArrayList<SafeHouse> getSafeHouseList() {
		return SafeHouseList;
	}

	private void setSafeHouseList(ArrayList<SafeHouse> SafeHouseList) {
		this.SafeHouseList = SafeHouseList;
	}

	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}


	//******************************** Methods ********************************


	public void initAllObjectView(){
		for(SafeHouse safeHouse : getSafeHouseList()){
			StackPane container = ViewUtils.initContainer(safeHouse.getImageSettings());
			int[] pos = safeHouse.getPosition();
			mapView.addToMap(container, pos[0], pos[1]);
		}
	}

	public void initView(SafeHouse safeHouse) {
		StackPane container = ViewUtils.initContainer(safeHouse.getImageSettings());
		getContainerList().add(container);
		int[] pos = safeHouse.getPosition();
		mapView.addToMap(container, pos[0], pos[1]);
	}

}
