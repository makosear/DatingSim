package makosear.datingsim.GameStructure.GamePersistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.User;
import makosear.datingsim.GameStructure.ui;

import java.io.File;
import java.io.IOException;

public class JSONPersistence implements GamePersistence {
    // In JSONPersistence constructor
@JsonIgnoreType
    private static abstract class UiMixIn {
        @JsonIgnore
        abstract DatingSim getGm();  // Match the getter name from your UI class
        @JsonIgnore
        abstract void setGm(DatingSim gm);
    }

    public final ObjectMapper mapper = new ObjectMapper()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module())
        .registerModule(new ParameterNamesModule())
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .addMixIn(ui.class, UiMixIn.class)  // Now properly resolved
        .enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
    
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
            File file = new File(filename);
            File parentDir = file.getParentFile();
            
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, game);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar estado do jogo: " + e.getMessage(), e);
        }
    }

    @Override
    public void loadGameState(DatingSim game, String filename) throws GameLoadException {
        try {
            // Update the existing game instance with the saved data
            mapper.readerForUpdating(game).readValue(new File(filename));
            game.setLoadingFromSave(true);
            game.postLoadInit();
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar estado do jogo: " + e.getMessage(), e);
        }
    }
}
