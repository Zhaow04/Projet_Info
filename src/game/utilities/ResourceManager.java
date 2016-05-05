package game.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import game.model.GameModel;
/**
 * Public abstract class that gathers all the methods regarding the save and load of the game.
 *
 */
public abstract class ResourceManager {

	//******************************** Methods ********************************

	/**
	 * 
	 * @param data {@link Serializable}
	 */
    public static void save(Serializable data) {
        try {
        	FileOutputStream fos = new FileOutputStream("src/game/save.ser");
        	ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(data);
        	oos.flush();
        	oos.close();
        } catch (IOException e) {
			e.printStackTrace();
        }
    }
    
    public static GameModel load() {
    	GameModel model = null;
    	try {
        	FileInputStream fis = new FileInputStream("src/game/save.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			model = (GameModel) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return model;
    }
    
}