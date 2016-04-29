package game.utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import game.model.GameModel;

public class ResourceManager {

    public static void save(Serializable data, String fileName) {
        try {
        	FileOutputStream fos = new FileOutputStream(fileName);
        	ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(data);
        	oos.flush();
        	oos.close();
        } catch (IOException e) {
			e.printStackTrace();
        }
    }

    public static GameModel load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            return (GameModel) ois.readObject();
        }
    }
}