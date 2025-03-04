package makosear.datingsim.UI;

import makosear.datingsim.DatingSim;

import java.util.*;


public class MudaLugar {
    DatingSim gm;

    Map<String, Integer> bgToLocations = new HashMap<>();
    String currentLocation = "Map";


    public MudaLugar(DatingSim gm) {
        this.gm = gm;
        bgToLocations.put("Map", 0);
        bgToLocations.put("Cafe1", 1);
        bgToLocations.put("Cafe2", 2);
    }

    public void addNewLocation (String location, int bgNum) {
        bgToLocations.put(location, bgNum);
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
