//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008
package makosear.datingsim.Personagem.Romanceable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

import makosear.datingsim.DatingSim;
import makosear.datingsim.GameStructure.WinConditions;

public class RomanceableTest {
    @Test
void characterProvidesPeriodAppropriateDialogue() {

    Romanceable chiaki = new Romanceable(
        "Chiaki",
        new ArrayList<>(),
        new ArrayList<>(),
        Map.of(),
        "characters/ch_1.png",
        new String[]{"good morning", "lol its mornin", "chiaki de gozamaisu"}, // Morning lines
        new String[]{"good afternoon", "lol its nonn", "chiaki da"}, // Afternoon lines
        new String[]{"good evening", "lol its night", "chiaki desu"}, // Evening lines
        new ArrayList<>(),
        new WinConditions(),
        "Test description"
    );

    chiaki.shiftAffection(-1);
    assertEquals(-1, chiaki.nivelDeAfeicao);


    String morningDialogue = chiaki.interact("Manha");
    assertTrue(Arrays.asList(chiaki.getFalas("Manha")).contains(morningDialogue));
    

    String nightDialogue = chiaki.interact("Noite");
    assertTrue(Arrays.asList(chiaki.getFalas("Noite")).contains(nightDialogue));
}
}
