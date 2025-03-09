//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.User;

import makosear.datingsim.Personagem.Romanceable.Romanceable;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
        this.profileType = "ADMIN";
    }

    public Admin() {
        super("", "");
        this.profileType = "ADMIN";
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
    public void editCharacter(Romanceable character) {
        // Lógica de edição
    }
}
