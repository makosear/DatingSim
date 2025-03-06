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

        bgToLocations.put("Cafe1", 1);
        bgToFilePath.put("Cafe1", "backgrounds/Cafe_Interior_750x300.jpg");

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

        for (Map.Entry<String, Integer> entry : bgToLocations.entrySet()) {
            if (entry.getKey().equals(location)) {
                gm.ui.bgPanel[entry.getValue()].setVisible(true);
            } else {
                gm.ui.bgPanel[entry.getValue()].setVisible(false);
            }
        }
        if (!location.equals ("Map")) {
            for (LocationToCharacters locationCharacters : gm.dayToLocationCharacters.get(gm.diaAtual))
            {
                if (locationCharacters.location.equals(location))
                {
                    notFound = false;
                    message += " ";
                        for (String character : locationCharacters.characters)
                        {
                            CharacterPosition position = characterPositions.get(new Random().nextInt    (characterPositions.size()));

                            if (!characterPositions.isEmpty()) {
                                characterPositions.remove(position);
                            }

                            message += character + ", ";

                            gm.ui.addCharacterToLocation(location, character, position);
                        }
                }
            }

            if (notFound) {
                System.out.println("Location not found");
                message += " Doggo, ";
                gm.ui.addCharacterToLocation(location, "Doggo", CharacterPosition.CENTER);
            }

        }
        
        String toReplace = ", ";
        String replacement = " is/are here.";
        int index = message.indexOf(toReplace);
        if (index != -1) {
            message = message.substring(0, index) + replacement;
        }

        currentLocation = location;
        gm.ui.messageText.setText(message);

    }



    public void setMap(){
        changeLocation("Map", "Choose a place to go to.");
    }

    public void setCafe1() {
        changeLocation("Cafe1", "You walk into the Cafe.");
    }

    public void setCafe2() {
        changeLocation("Cafe2", "You walk into the Cafe.");
    }
    
}
