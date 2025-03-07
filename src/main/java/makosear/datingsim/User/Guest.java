//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.User;

public class Guest extends User {
    
    public Guest(String username, String password) {
        super(username, password);
        this.profileType = "GUEST";
    }

    @Override
    public boolean authenticate(String password) {
        return true;
    }
    
}
