//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.User;

import java.util.ArrayList;
import java.util.List;

import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.GameStructure.GamePersistence.GamePersistence;

public class UserService {
    private List<User> users = new ArrayList<>();
    private GamePersistence persistence;
    public User currentUser;

    public UserService(GamePersistence persistence) {
        this.persistence = persistence;
        carregarUsuarios();
    }

    public User authenticateUser(String username, String password) {
        User user = buscarUsuario(username);
        if (user != null && user.getPassword().equals(password)) { 
            this.currentUser = user;
            System.out.println("Authenticated as: " + user.getClass().getSimpleName());
            return user;
        }
        return null;
    }

    public void carregarUsuarios() {
        try {
            users = persistence.loadUserData("users.json");
            System.out.println("Loaded " + users.size() + " users from storage");
        } catch (GameLoadException e) {
            System.out.println("No existing users found, starting fresh");
            users = new ArrayList<>();
        }
    }

    public void criarUsuario(User user) throws InvalidInputException {
        Validator.validarUsuario(user);
        if (buscarUsuario(user.getUsername()) != null) {
            throw new InvalidInputException("Usuario", "Usuario ja existe");
        }

        else { 
            System.out.println(user.getUsername());
            users.add(user);
            salvarUsuarios();
        }
    }

    public void atualizarUsuario(String username, User newData) throws InvalidInputException {
        Validator.validarUsuario(newData);
        User user = buscarUsuario(username);
        user.setUsername(newData.getUsername());
        user.setPassword(newData.getPassword());
        salvarUsuarios();
    }

    public User buscarUsuario(String username) 
    {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public void removerUsuario(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
        salvarUsuarios();
    }

    public void logoutUsuario() {
        currentUser = null;
    }

    private void salvarUsuarios() {
        try {
            for (User user : users) {
                persistence.saveUserData(user, "users.json");
            }
        } catch (GameSaveException e) {
            //print error stack
            e.printStackTrace();
            System.err.println("Erro: " + e.getMessage());
        }
    }
}