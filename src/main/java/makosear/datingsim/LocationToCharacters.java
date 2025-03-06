package makosear.datingsim;

import java.util.ArrayList;


import java.util.List;


public class LocationToCharacters {
    public String location;
    public List<String> characters = new ArrayList<>();

    public LocationToCharacters() {
        this.location = "";
        this.characters = new ArrayList<>();
    }

    public LocationToCharacters(String location, List<String> characters) {
        this.location = location;
        this.characters = characters;
    }
}
