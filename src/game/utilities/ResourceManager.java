package game.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import game.model.GameModel;

public abstract class ResourceManager {

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
/*
    public static GameModel load(String fileName) throws Exception {
        try {
        	FileInputStream fis = new FileInputStream(fileName);
        	ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
            return (GameModel) ois.readObject();
        }
    }*/
    
}