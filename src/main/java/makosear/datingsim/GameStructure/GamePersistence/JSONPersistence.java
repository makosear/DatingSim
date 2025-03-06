package makosear.datingsim.GameStructure.GamePersistence;

import com.fasterxml.jackson.databind.ObjectMapper;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.User;

import java.io.File;
import java.io.IOException;

public class JSONPersistence implements GamePersistence {
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public void saveUserData(User user) throws GameSaveException {
        try {
            mapper.writeValue(new File("users/" + user.getUsername() + ".json"), user);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar usuario", e);
        }
    }


    @Override
    public void saveUserData(User user, String filename) throws GameSaveException {
        try {
            mapper.writeValue(new File(filename), user);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar usuario", e);
        }
    }

    @Override
    public User loadUserData(String username) throws GameLoadException {
        File userFile = new File("users/" + username + ".json");
        if (!userFile.exists()) {
            throw new GameLoadException("Dados do usuario nao encontrados", null);
        }

        try {
            return mapper.readValue(new File("users/" + username + ".json"), User.class);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuario", e);
        }
    }

    
    @Override
    public User loadUserData(String username, String filename) throws GameLoadException {
        try {
            return mapper.readValue(new File(filename), User.class);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuario", e);
        }
    }

    @Override
    public void saveGameState(DatingSim game, String filename) throws GameSaveException {
        try {
            mapper.writeValue(new File("gamestate.json"), game);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar estado do jogo", e);
        }
    }

    @Override
    public DatingSim loadGameState(String filename) throws GameLoadException {
        try {
            return mapper.readValue(new File("gamestate.json"), DatingSim.class);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar estado do jogo", e);
        }
    }
}
