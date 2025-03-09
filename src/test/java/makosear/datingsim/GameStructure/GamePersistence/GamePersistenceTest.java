package makosear.datingsim.GameStructure.GamePersistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;

public class GamePersistenceTest {
    @Test
    void jsonPersistenceSavesAndLoadsGameState() throws Exception {
        MockStuffToSave originalState = new MockStuffToSave();
        originalState.setPlayer(new PlayerCharacter());
        originalState.getPlayer().locationVisitCounter.put("Library", 3);

        JSONPersistence persistence = new JSONPersistence(originalState);
        
        persistence.saveGameState(originalState, "test_save.json");
        persistence.loadGameState("test_save.json");
        
        assertEquals(3, persistence.getLoadedState().getPlayer().locationVisitCounter.get   ("Library"));
        new File("test_save.json").delete();
    }

    
    @Test
    void jsonPersistenceThrowsExceptionOnBadLoad() {
        JSONPersistence persistence = new JSONPersistence(new MockStuffToSave());
        GameLoadException exception = null;
        try {
            persistence.loadGameState("non_existent_file.json");
        } catch(GameLoadException e) {
            exception = e;
        }
        assertNotNull(exception);
    }
}
