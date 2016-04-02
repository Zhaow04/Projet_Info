package game.utilities;

import game.model.GameModel;
import game.view.GameView;
import game.view.StartSceneController;


public class SaveData implements java.io.Serializable {
	

    private static final long serialVersionUID = 1L;

    public GameModel model;
    
       
    
    public GameModel getGameModel() {
		return model;
	}

	public void setGameModel(GameModel model) {
		this.model = model;
	}
	/*
	public GameView getGameView() {
		return view;
	}

	public void setGameView(GameView view) {
		this.view = view;
	}*/
}
