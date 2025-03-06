package makosear.datingsim.GameStructure.GamePersistence;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.*;
import makosear.datingsim.User.User;

public interface GamePersistence {
    void saveUserData(User user) throws GameSaveException;
    User loadUserData(String username) throws GameLoadException;
    void saveGameState(DatingSim game) throws GameSaveException;
    DatingSim loadGameState() throws GameLoadException;
}
