package makosear.datingsim.GameStructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.Gift.Gift;

public class WinConditionsTest {

    @Test
    void winConditionsTodosRequerimentos() {
        PlayerCharacter player = new PlayerCharacter();
        player.locationVisitCounter.put("Park", 3);

        Romanceable character = new Romanceable();
        character.nivelDeAfeicao = 5;
        character.cenasVistas = 5;
        Gift gift = new Gift("Notebook", "");
        character.getGiftsReceived().add(gift);

        WinConditions wc = new WinConditions(
            4,
            3, 
            List.of(gift),
            Map.of("Park", 2),
            0
        );

        character.setWinConditions(wc);

        assertTrue(character.isWinConditionsMet(player));
    }

    @Test
    void winConditionsFalhaSemAfeto() {
        PlayerCharacter player = new PlayerCharacter();
        Romanceable character = new Romanceable();

        WinConditions wc = new WinConditions(
            2, 0, 
            List.of(),
            Map.of(), 0
        );

        character.setWinConditions(wc);

        assertFalse(character.isWinConditionsMet(player));
    }
}

