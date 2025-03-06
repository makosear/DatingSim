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

    public UserService(GamePersistence persistence) {
        this.persistence = persistence;
        carregarUsuarios();
    }

    public void carregarUsuarios() {
        try {
            users.add((Admin) persistence.loadUserData("admin_users.xml"));
            users.add(persistence.loadUserData("users.json"));
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