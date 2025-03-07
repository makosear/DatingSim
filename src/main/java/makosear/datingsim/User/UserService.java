package makosear.datingsim.User;

import java.util.ArrayList;
import java.util.List;

import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.GameStructure.GamePersistence.GamePersistence;

// UserService.java - Novo serviço para gerenciamento de usuários
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
            currentUser = user;
        }
        if (username == "Admin") return (Admin) user;
        else return user;
    }

    public void carregarUsuarios() {
        try {
            List<User> loadedAdmins = persistence.loadUserData("admin_users.xml");
            List<User> loadedUsers = persistence.loadUserData("users/users.json");
            for (User user : loadedUsers) {
                users.add(user);
            }

            for (User user : loadedAdmins) {
                users.add(user);
            }
        } catch (GameLoadException e) {
            // Tratar erro
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public void criarUsuario(User user) throws InvalidInputException {
        Validator.validarUsuario(user);
        users.add(user);
        salvarUsuarios();
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
        users.forEach(u -> {
            try {
                if(u instanceof Admin) {
                    persistence.saveUserData(u, "admin_users.xml");
                } else {
                    persistence.saveUserData(u, "users/users.json");
                }
            } catch (GameSaveException e) {
                // Tratar erro
            }
        });
    }
}