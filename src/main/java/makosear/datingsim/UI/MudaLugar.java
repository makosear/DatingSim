package makosear.datingsim.UI;

import makosear.datingsim.DatingSim;

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

        bgToLocations.put("Cafe2", 2);
        bgToFilePath.put("Cafe2", "backgrounds/Cafe_Interior_750x300.jpg");
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

    public void changeLocation(String location, String message) {

        for (Map.Entry<String, Integer> entry : bgToLocations.entrySet()) {
            if (entry.getKey().equals(location)) {
                gm.ui.bgPanel[entry.getValue()].setVisible(true);
            } else {
                gm.ui.bgPanel[entry.getValue()].setVisible(false);
            }
        }

        currentLocation = location;
        gm.ui.messageText.setText(message);

    }



    public void setMap(){
        changeLocation("Map", "Choose a place to go to.");
    }

    public void setCafe1() {
        changeLocation("Cafe1", "You walk into the Cafe. Shu, Gaku, and Chiaki are present.");
    }

    public void setCafe2() {
        changeLocation("Cafe2", "You walk into the Cafe. Tsumugi, Itsuki, and Yato are present.");
    }
    
}
