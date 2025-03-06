/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package makosear.datingsim;

import makosear.datingsim.GameStructure.ActionHandler;
import makosear.datingsim.GameStructure.BGMHandler;
import makosear.datingsim.GameStructure.MudaLugar;
import makosear.datingsim.GameStructure.GamePersistence.*;
import makosear.datingsim.GameStructure.WinConditions;
import makosear.datingsim.GameStructure.ui;
import makosear.datingsim.Gift.Gift;
import makosear.datingsim.Personagem.NonRomanceable.*;
import makosear.datingsim.Personagem.Romanceable.*;
import makosear.datingsim.Scene.SceneHandler;
import makosear.datingsim.LocationToCharacters;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author ice
 */
public class DatingSim {
    public final String SAVE_PATH = "save.json";
    final private int DIA_INICIAL = 1;
    final private String PERIODO_INICIAL = "Manha";
    public int diaAtual = DIA_INICIAL;
    public String periodoAtual = PERIODO_INICIAL;

    public void comecaJogo() {

    }

    public void terminaJogo() {

    }

    public PlayerCharacter player = new PlayerCharacter("Player", new ArrayList<>(), new ArrayList<>(), new HashMap<>());

    public Map<Integer, List<LocationToCharacters>> dayToLocationCharacters  = new HashMap<>();

    public static SceneHandler sceneHandler = new SceneHandler();

    public SaveHandler saveHandler = new SaveHandler();

    public static Map<String,Romanceable> romanceableCharacters = new HashMap<>();

    public static Map<String, NonRomanceable> nonRomanceableCharacters = new HashMap<>();

    public ActionHandler aHandler = new ActionHandler(this);

    public BGMHandler bgmHandler = new BGMHandler();

    public MudaLugar mudaLugar = new MudaLugar(this);

    public ui ui = new ui(this);

   

    // initialize characters

    
    public static void main (String[] args) {
        inicializaPersonagens();
        new DatingSim();
    }

    public DatingSim() {
        calculateLocationsPerDay();
        diaAtual = DIA_INICIAL;
        periodoAtual = PERIODO_INICIAL;
        mudaLugar.changeLocation("MainMenu", "");
        bgmHandler.playMusic("src/main/resources/audio/MusMus-BGM-154.wav");
        
    }

    public static void inicializaPersonagens() {
        /* ENDING GUIDES
         * Chiaki: Very easy
         *                  Requires Affection > 3
         *                           meet him five times
         * Gaku: Very hard
         *                  Requires Affection > 6
         *                           gifted "Notebook"
         *                           meet him five times and only choose right answers
         *                           visited Shopping Center at least 5 times
         * Shu: Average
         *                 Requires Affection > 6
         *                          meet him five times
         *                          visited Park at least 2 times
         * 
         * Yato: Average
         *                  Requires Affection > 6
         *                          meet him five times
         *                          visited Gym at least 2 times
         * 
         * Itsuki: Hard
         *                 Requires Affection > 6
         *                          meet him five times and choose at least 4 correct answers
         *                          visited Office at least 2 times
         * 
         * Tsumugi: Easy
         *                 Requires Affection > 4
         *                          meet him five times
         *                          visited Library at least 2 times
         * 
        */
        romanceableCharacters.put("Chiaki", new Romanceable(
            "Chiaki", 
            null, 
            null, 
            Map.of("Cafe", 0.5, "Gym", 0.05, "Library", 0.1, "Mall", 0.15, "Office", 0.1, "Park", 0.2),
            "characters/ch_1.png",
            new String[]{"good morning", "lol its mornin,", "chiaki de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "chiaki da"},
            new String[]{"good evening", "lol its night", "chiaki desu"},
            sceneHandler.getCenas("Chiaki"), 
            new WinConditions(3, 5, null, null, 0)
        ));

        romanceableCharacters.put("Gaku", new Romanceable(
            "Gaku",
            null,
            null,
            Map.of("Cafe", 0.15, "Gym", 0.15, "Library", 0.25, "Mall", 0.4, "Office", 0.10, "Park", 0.05),
            "characters/ch_2.png",
            new String[]{"good morning", "lol its mornin,", "gaku de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "gaku da"},
            new String[]{"good evening", "lol its night", "gaku desu"},
            sceneHandler.getCenas("Gaku"),
            new WinConditions(6, 5, List.of(new Gift("Notebook", "This is a notebook.")), Map.of("Shopping Center", 5), 0)
            ///int int List<Gift> Map<String, Integer> int 
            //new WinConditions(0, 0, null, null, 0)        
        ));

        romanceableCharacters.put("Shu", new Romanceable(
            "Shu",
            null,
            null,
            Map.of("Cafe", 0.15, "Gym", 0.15, "Library", 0.10, "Mall", 0.05, "Office", 0.25, "Park", 0.4),
            "characters/ch_3.png",
            new String[]{"good morning", "lol its mornin,", "shu de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "shu da"},
            new String[]{"good evening", "lol its night", "shu desu"},
            sceneHandler.getCenas("Shu"),
            new WinConditions(6, 5, null, Map.of("Park", 2), 0)
        ));

        romanceableCharacters.put("Yato", new Romanceable(
            "Yato",
            null,
            null,
            Map.of("Cafe", 0.075, "Gym", 0.4, "Library", 0.05, "Mall", 0.1, "Office", 0.075, "Park", 0.3),            "characters/ch_4.png",
            new String[]{"good morning", "lol its mornin,", "yato de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "yato da"},
            new String[]{"good evening", "lol its night", "yato desu"},
            sceneHandler.getCenas("Yato"),
            new WinConditions(6, 5, null, Map.of("Gym", 2), 0)
        ));

        romanceableCharacters.put("Itsuki", new Romanceable(
            "Itsuki", 
            null, 
            null, 
            Map.of("Cafe", 0.15, "Gym", 0.25, "Library", 0.15, "Mall", 0.10, "Office", 0.4, "Park", 0.05),            "characters/ch_5.png",
            new String[]{"good morning", "lol its mornin,", "itsuki de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "itsuki da"},
            new String[]{"good evening", "lol its night", "itsuki desu"},
            sceneHandler.getCenas("Itsuki"),
            new WinConditions(6, 5, null, Map.of("Office", 2), 4)
        ));

        romanceableCharacters.put("Tsumugi", new Romanceable(
            "Tsumugi", 
            null, 
            null, 
            Map.of("Cafe", 0.2, "Gym", 0.0, "Library", 0.5, "Mall", 0.05, "Office", 0.2, "Park", 0.05),            "characters/ch_6.png",
            new String[]{"good morning", "lol its mornin,", "tsumugi de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "tsumugi da"},
            new String[]{"good evening", "lol its night", "tsumugi desu"},
            sceneHandler.getCenas("Tsumugi"),
            new WinConditions(4, 5, null, Map.of("Library", 2), 0)
        ));

        nonRomanceableCharacters.put("Doggo", new NonRomanceable(
            "Doggo",
            null,
            null,
            "characters/Dog.png",
            new String[]{"Woof woof", "Woof woof", "doggo de gozamaisu"},
            new String[]{"Woof woof", "Woof woof", "doggo da"},
            new String[]{"Woof woof", "Woof woof", "doggo desu"},
            sceneHandler.getCenas("Doggo")
        ));
    }

    public void calculateLocationsPerDay() {
        // Initialize the map for each day
        for (int day = 1; day <= 7; day++) {
            dayToLocationCharacters.put(day, new ArrayList<>());
        }
    
        // Randomize the order of characters to avoid bias in assignment
        List<String> characterNames = new ArrayList<>(romanceableCharacters.keySet());
        Collections.shuffle(characterNames);
    
        // First pass - assign each character to a location based on their preferences
        for (String characterName : characterNames) {
            
            Romanceable character = romanceableCharacters.get(characterName);
            Map<String, Double> locationPercentages = character.lugaresEncontro;
    
            for (int day = 1; day <= 7; day++) {
                // Create a copy of percentages that we can modify
                Map<String, Double> availableLocations = new HashMap<>(locationPercentages);
                boolean assigned = false;
    
                // Try to assign to existing locations first if under capacity
                for (LocationToCharacters locChar : dayToLocationCharacters.get(day)) {
                    if (locChar.characters.size() < 3 && availableLocations.containsKey(locChar.location)) {
                        // Higher chance to join existing location with higher percentage
                        double locationPref = availableLocations.get(locChar.location);
                        double randomValue = Math.random() * 100;
    
                        if (randomValue <= locationPref) {
                            locChar.characters.add(characterName);
                            assigned = true;
                            break;
                        }
                    }
                }
    
                // If not assigned, create a new location
                if (!assigned) {
                    // Remove locations that already have this character's friends
                    Set<String> occupiedLocations = new HashSet<>();
                    for (LocationToCharacters locChar : dayToLocationCharacters.get(day)) {
                        occupiedLocations.add(locChar.location);
                    }
    
                    // Choose from remaining locations based on percentage
                    String chosenLocation = chooseLocationBasedOnPercentage(availableLocations, dayToLocationCharacters.get(day));
    
                    // Check if this location already exists but was full
                    boolean locationExists = false;
                    for (LocationToCharacters locChar : dayToLocationCharacters.get(day)) {
                        if (locChar.location.equals(chosenLocation)) {
                            locationExists = true;
                            // If unexpectedly not full, add the character
                            if (locChar.characters.size() < 3) {
                                locChar.characters.add(characterName);
                            }
                            break;
                        }
                    }
    
                    // If location doesn't exist yet, create it
                    if (!locationExists) {
                        List<String> characters = new ArrayList<>();
                        characters.add(characterName);
                        LocationToCharacters newLocChar = new LocationToCharacters(chosenLocation, characters);
                        dayToLocationCharacters.get(day).add(newLocChar);
                    }
                }
            }
        }
    
        // Second pass - ensure all characters are assigned to a location
        for (int day = 1; day <= 7; day++) {
            List<LocationToCharacters> locationsForDay = dayToLocationCharacters.get(day);
            for (String characterName : characterNames) {
                boolean isAssigned = false;
                for (LocationToCharacters locChar : locationsForDay) {
                    if (locChar.characters.contains(characterName)) {
                        isAssigned = true;
                        break;
                    }
                }
                if (!isAssigned) {
                    // Forcibly assign the character to the first available location
                    if (!locationsForDay.isEmpty()) {
                        locationsForDay.get(0).characters.add(characterName);
                    } else {
                        // If no locations exist, create a default location
                        List<String> characters = new ArrayList<>();
                        characters.add(characterName);
                        LocationToCharacters newLocChar = new LocationToCharacters("Gym", characters);
                        locationsForDay.add(newLocChar);
                    }
                }
            }
        }
    
        // Fix any duplicate locations (merge them)
        for (int day = 1; day <= 7; day++) {
            List<LocationToCharacters> locationsForDay = dayToLocationCharacters.get(day);
            Map<String, LocationToCharacters> locationMap = new HashMap<>();
    
            // Identify duplicates
            for (LocationToCharacters locChar : new ArrayList<>(locationsForDay)) {
                if (locationMap.containsKey(locChar.location)) {
                    // Merge characters from duplicate into existing (up to 3 max)
                    LocationToCharacters existing = locationMap.get(locChar.location);
                    for (String character : locChar.characters) {
                        if (existing.characters.size() < 3 && !existing.characters.contains(character)) {
                            existing.characters.add(character);
                        }
                    }
                    locationsForDay.remove(locChar);
                } else {
                    locationMap.put(locChar.location, locChar);
                }
            }
        }
    
        // Print results for verification
        for (int day = 1; day <= 7; day++) {
            System.out.println("=== Day " + day + " ===");
            for (LocationToCharacters locChar : dayToLocationCharacters.get(day)) {
                System.out.println(locChar.location + ": " + locChar.characters);
            }
        }
    }
    
    private String chooseLocationBasedOnPercentage(Map<String, Double> locationPercentages, List<LocationToCharacters> existingLocations) {
        // Filter out locations that are already full
        Map<String, Double> availableLocations = new HashMap<>(locationPercentages);
        for (LocationToCharacters locChar : existingLocations) {
            if (locChar.characters.size() >= 3) {
                availableLocations.remove(locChar.location);
            }
        }
    
        // If no available locations, return a default location
        if (availableLocations.isEmpty()) {
            return "Gym";
        }
    
        // Use the probabilistic selection logic on the available locations
        return chooseLocationBasedOnPercentage(availableLocations);
    }
    
    private String chooseLocationBasedOnPercentage(Map<String, Double> locationPercentages) {
        // Handle empty map case
        if (locationPercentages == null || locationPercentages.isEmpty()) {
            return "Gym"; // Default location
        }
    
        double randomValue = Math.random() * 100;
        double cumulativeProbability = 0.0;
    
        // Normalize the percentages in case they don't add up to 100
        double total = locationPercentages.values().stream().mapToDouble(Double::doubleValue).sum();
        double normalizationFactor = total > 0 ? 100.0 / total : 1.0;
    
        for (Map.Entry<String, Double> entry : locationPercentages.entrySet()) {
            cumulativeProbability += entry.getValue() * normalizationFactor;
            if (randomValue <= cumulativeProbability) {
                return entry.getKey();
            }
        }
    
        // Fallback to a random location if calculation failed
        List<String> locations = new ArrayList<>(locationPercentages.keySet());
        return locations.get(new Random().nextInt(locations.size()));
    }

    public void inicializaPeriodoNovo() {
        mudaLugar.setMap();
    }

    public String tieBreaker(List<String> winnerList) {
        if (winnerList.size() == 1) {
            return winnerList.get(0);
        }

        return winnerList.stream()
        .max(Comparator
            .comparing((String characterName) -> 
                romanceableCharacters.get(characterName).nivelDeAfeicao)
            .thenComparing(characterName -> 
                romanceableCharacters.get(characterName).giftsReceived.size())
            .thenComparing(characterName -> 
                romanceableCharacters.get(characterName).cenasVistas)
            .thenComparing(characterName -> 
                Math.random()) // random tiebreaker
        )
        .orElseThrow(() -> new IllegalStateException("Winner list is empty"));
    }

    public void endGame() {
        System.out.println("Game Over!");
        List<String> winConditionsMet = new ArrayList<>();
        String winner = "";

        for (Romanceable p : romanceableCharacters.values()) {
            if (p.isWinConditionsMet(this.player)) winConditionsMet.add(p.getNome());
        }

        //DEBUG
        winConditionsMet.add("Chiaki");
        //TODO REMOVE


        if (winConditionsMet.size() == 0) ui.displayLoseScreen();
        else {
            if (winConditionsMet.size() == 1) winner = winConditionsMet.get(0);
            else winner = tieBreaker(winConditionsMet);

            ui.displayWinScreen(winner);

            //how to end close the window
            
            //ui.closeWindow();
        }
    }

    public void passaPeriodo(){
        if (periodoAtual.equals("Manha")) periodoAtual = "Tarde";
        else if (periodoAtual.equals("Tarde")) periodoAtual = "Noite";
        else if (periodoAtual.equals("Noite")) {
            periodoAtual = "Manha";
            diaAtual++;
        }

        ui.updateDayAndPeriodCounter();
        if (diaAtual < 8) inicializaPeriodoNovo();
        else {       
            endGame();
        }
    }
}