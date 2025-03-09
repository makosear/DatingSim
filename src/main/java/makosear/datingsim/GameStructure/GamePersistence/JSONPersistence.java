//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.GameStructure.GamePersistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JSONPersistence implements GamePersistence {
    private final Path userDirectory = Paths.get("users");
    private StuffToSave stuffToSave;
    public final ObjectMapper mapper = new ObjectMapper();

    public JSONPersistence(StuffToSave stuffToSave) {
        this.stuffToSave = stuffToSave;
        createUserDirectory();
    }

    private void createUserDirectory() {
        try {
            Files.createDirectories(userDirectory);
        } catch (IOException e) {
            System.err.println("Couldn't create users directory: " + e.getMessage());
        }
    }
    
    @Override
    public void saveUserData(User user) throws GameSaveException {
        saveUserData(user, "users.json");
    }


    @Override
    public void saveUserData(User user, String filename) throws GameSaveException {
        Path filePath = userDirectory.resolve(filename);
        try {
            List<User> users = Files.exists(filePath) ? 
                mapper.readValue(filePath.toFile(), new TypeReference<List<User>>(){}) : 
                new ArrayList<>();
            users.removeIf(u -> u.getUsername().equals(user.getUsername()));
            users.add(user);
            mapper.writeValue(filePath.toFile(), users);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar usuario", e);
        }
    }

    @Override
    public List<User> loadUserData(String filename) throws GameLoadException {
        Path filePath = userDirectory.resolve(filename);
        try {
            if(Files.notExists(filePath)) return new ArrayList<>();
            return mapper.readValue(filePath.toFile(), new TypeReference<List<User>>(){});
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuarios", e);
        }
    }

    
    @Override
    public User loadUserData(String username, String filename) throws GameLoadException {
        try {
            File file = new File(filename);
            if(!file.exists()) return null;
            
            List<User> users = mapper.readValue(file, new TypeReference<List<User>>(){});
            return users.stream()
                       .filter(u -> u.getUsername().equals(username))
                       .findFirst()
                       .orElse(null);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuario", e);
        }
    }

    @Override
    public void saveGameState(StuffToSave stuffToSave, String filename) throws GameSaveException {
        try {
            File file = new File(filename);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, stuffToSave);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar estado do jogo", e);
        }
    }

    @Override
    public void loadGameState(String filename) throws GameLoadException {
        try {
            StuffToSave loadedState = mapper.readValue(new File(filename), StuffToSave.class);

            stuffToSave.setDiaAtual(loadedState.getDiaAtual());
            stuffToSave.setPeriodoAtual(loadedState.getPeriodoAtual());
            stuffToSave.setPlayer(loadedState.getPlayer());
            stuffToSave.setDayToLocationCharacters(loadedState.getDayToLocationCharacters());
            stuffToSave.setCurrentUser(loadedState.getCurrentUser());
            stuffToSave.setCurrentDialogue(loadedState.getCurrentDialogue());
            stuffToSave.setCurrentCharacter(loadedState.getCurrentCharacter());
            stuffToSave.setDialogueBoxCounter(loadedState.getDialogueBoxCounter());
            stuffToSave.setIsWaitingOption(loadedState.getIsWaitingOption());
            stuffToSave.setCurrentLocation(loadedState.getCurrentLocation());
            stuffToSave.setMessageText(loadedState.getMessageText());
            stuffToSave.setRomanceableCharacters(loadedState.getRomanceableCharacters());
            stuffToSave.setNonRomanceableCharacters(loadedState.getNonRomanceableCharacters());

            stuffToSave.loadInformation(); 

        } catch (IOException e) {
            System.err.println("Deserialization error:");
            e.printStackTrace();
            throw new GameLoadException("Falha ao carregar estado do jogo", e);
        }
    }
}
