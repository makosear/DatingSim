package makosear.datingsim.User;

import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;

public class Default extends User {

    PlayerCharacter playerCharacter;
    public Default(String username, String password) {
        super(username, password);
        this.profileType = "DEFAULT";
    }

    public Default() {
        super("DEFAULT", "");
        this.profileType = "DEFAULT";
    }
    
    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
