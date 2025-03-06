package makosear.datingsim.SaveLoad;

import java.io.*;

public class Saving {

    public void saveGameState(Object gameState, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadGameState(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

