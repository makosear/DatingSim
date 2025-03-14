package makosear.datingsim.User;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.GameStructure.GamePersistence.GamePersistence;

// UserService.java - Novo servico para gerenciamento de usuarios
public class UserService {
    public User currentUser;
    private List<User> users = new ArrayList<>();
    @JsonIgnore
    private GamePersistence persistence;

    public UserService(GamePersistence persistence) {
        this.persistence = persistence;
        carregarUsuarios();
    }

    public UserService() {
        this.persistence = null; // Will be reinitialized post-load
    }

    public User authenticateUser(String username, String password) {
        User user = buscarUsuario(username);
        if (user != null && user.authenticate(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public void carregarUsuarios() {
        try {
            users.add((Admin) persistence.loadUserData("admin_users.xml"));
            users.add(persistence.loadUserData("users.json"));
        } catch (GameLoadException e) {
            // Tratar erro
            System.err.println("Erro ao carregar usuarios: " + e.getMessage());
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

    private void salvarUsuarios() {
        users.forEach(u -> {
            try {
                if(u instanceof Admin) {
                    persistence.saveUserData(u, "admin_users.xml");
                } else {
                    persistence.saveUserData(u, "users.json");
                }
            } catch (GameSaveException e) {
                // Tratar erro
            }
        });
    }
}