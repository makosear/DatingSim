package makosear.datingsim.GameStructure;

import java.util.ArrayList;


import java.util.List;


public class LocationCharacters {
    public String location;
    public List<String> characters = new ArrayList<>();

    public LocationCharacters() {
        this.location = "";
        this.characters = new ArrayList<>();
    }

    public LocationCharacters(String location, List<String> characters) {
        this.location = location;
        this.characters = characters;
    }
}
