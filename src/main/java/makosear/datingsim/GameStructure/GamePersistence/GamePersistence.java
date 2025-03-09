//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.GameStructure.GamePersistence;


import java.util.List;

import makosear.datingsim.Excecao.*;
import makosear.datingsim.User.User;

public interface GamePersistence { 
    void saveUserData(User user) throws GameSaveException;
    void saveUserData(User user, String filename) throws GameSaveException;
    List<User> loadUserData(String filename) throws GameLoadException;
    User loadUserData(String username, String filename) throws GameLoadException;
    void saveGameState(LoadedState stuffToSave, String filename) throws GameSaveException;
    void loadGameState(String filename) throws GameLoadException;
}
