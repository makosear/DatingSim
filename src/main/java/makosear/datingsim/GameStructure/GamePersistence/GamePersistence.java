package makosear.datingsim.GameStructure.GamePersistence;

import makosear.datingsim.Excecao.*;
import makosear.datingsim.User.User;

public interface GamePersistence { 
    void saveUserData(User user) throws GameSaveException;
    void saveUserData(User user, String filename) throws GameSaveException;
    User loadUserData(String username) throws GameLoadException;
    User loadUserData(String username, String filename) throws GameLoadException;
    void saveGameState(StuffToSave stuffToSave, String filename) throws GameSaveException;
    void loadGameState(String filename) throws GameLoadException;
}
