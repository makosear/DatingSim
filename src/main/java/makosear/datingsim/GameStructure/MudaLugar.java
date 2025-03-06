package makosear.datingsim.GameStructure;

import makosear.datingsim.DatingSim;
import makosear.datingsim.LocationToCharacters;

import java.util.*;


public class MudaLugar {
    DatingSim gm;

    Map<String, Integer> bgToLocations = new HashMap<>();
    Map<String, String> bgToFilePath = new HashMap<>();
    String currentLocation = "Map";


    public MudaLugar(DatingSim gm) {
        this.gm = gm;

        bgToLocations.put("Map", 0);
        bgToFilePath.put("Map", "");

        bgToLocations.put("Cafe", 1);
        bgToFilePath.put("Cafe", "backgrounds/Cafe_Interior_750x300.jpg");

        bgToLocations.put("Library", 2);
        bgToFilePath.put("Library", "backgrounds/Library.png");

        bgToLocations.put("Gym", 3);
        bgToFilePath.put("Gym", "backgrounds/Gym.png");

        bgToLocations.put("Mall", 4);
        bgToFilePath.put("Mall", "backgrounds/Mall.png");

        bgToLocations.put("Office", 5);
        bgToFilePath.put("Office", "backgrounds/Office.png");

        bgToLocations.put("Park", 6);
        bgToFilePath.put("Park", "backgrounds/Park.png");

        bgToLocations.put("MainMenu", 7);
        bgToFilePath.put("MainMenu", ""); 
    }

    public void addNewLocation (String location, int bgNum, String bgFilePath) {
        if (bgToLocations.containsKey(location)) removeLocation(location);

        bgToLocations.put(location, bgNum);
        bgToFilePath.put(location, bgFilePath);
        
    }
    
    public void removeLocation (String location) {
        bgToLocations.remove(location);
        bgToFilePath.remove(location);
    }

    public void changeLocation(String location)
    {
        String message = "You walk into " + location + ".";
        changeLocation(location, message);
    }

    public void changeLocation(String location, String message) {
        boolean notFound = true;
        List<CharacterPosition> characterPositions = new ArrayList<>();
        characterPositions.add(CharacterPosition.CENTER);
        characterPositions.add(CharacterPosition.RIGHT);
        characterPositions.add(CharacterPosition.LEFT);

        LocationToCharacters exportedLocationCharacters = null;

        for (Map.Entry<String, Integer> entry : bgToLocations.entrySet()) {
            //System.out.println(entry.getKey());
            if (entry.getKey().equals(location)) {
                gm.ui.bgPanel[entry.getValue()].setVisible(true);
            } else {
                gm.ui.bgPanel[entry.getValue()].setVisible(false);
            }
        }
        if (!location.equals ("Map") && !location.equals("characterScreen") && !location.equals("MainMenu")) {
            gm.ui.removeCharactersFromLocation(location);
            for (LocationToCharacters locationCharacters : gm.dayToLocationCharacters.get(gm.diaAtual))
            {
                if (locationCharacters.location.equals(location))
                {
                    exportedLocationCharacters = locationCharacters;
                    notFound = false;
                    message += " ";
                        for (String character : locationCharacters.characters)
                        {
                            CharacterPosition position = characterPositions.get(new Random().nextInt    (characterPositions.size()));

                            if (!characterPositions.isEmpty()) {
                                characterPositions.remove(position);
                            }

                            gm.ui.addCharacterToLocation(location, character, position);
                        }
                }
            }

            if (notFound) {
                //System.out.println("Location not found");
                message += " No one is here. A little dog walks in!";
                gm.ui.addCharacterToLocation(location, "Doggo", CharacterPosition.CENTER);
            }

        }
        
        if (!notFound) {
            if (exportedLocationCharacters.characters.size() == 1) message += exportedLocationCharacters.characters.get(0) + " is here.";
            else {
                message += "The following people are here: ";
                for (String character : exportedLocationCharacters.characters) {
                    message += character;
                    if (exportedLocationCharacters.characters.indexOf(character) == exportedLocationCharacters.characters.size() - 1) {
                        message += ".";
                    } else {
                        if (exportedLocationCharacters.characters.indexOf(character) == exportedLocationCharacters.characters.size() - 2)
                            message += " and ";
                        else message += ", ";
                    }
                }
            }
        }

        currentLocation = location;
        gm.ui.messageText.setText(message);

        if (location.equals("MainMenu")) {
            gm.ui.dayAndPeriodCounter.setVisible(false);
            gm.ui.messageText.setVisible(false);
        }

        else if (location.equals("Map")) {
            gm.ui.dayAndPeriodCounter.setVisible(true);
            gm.ui.messageText.setVisible(true);
        }

    }



    public void setMap(){
        changeLocation("Map", "Choose a place to go to.");
    }

    public void setCafe() {
        changeLocation("Cafe", "You walk into the Cafe.");
    }
    
}
