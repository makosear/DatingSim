package makosear.datingsim.GameStructure.GamePersistence;

import com.fasterxml.jackson.databind.ObjectMapper;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.User;

import java.io.File;
import java.io.IOException;

public class JSONPersistence implements GamePersistence {

    private StuffToSave stuffToSave;

    public JSONPersistence(StuffToSave stuffToSave) {
        this.stuffToSave = stuffToSave;
    }

    public final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public void saveUserData(User user) throws GameSaveException {
        try {
            mapper.writeValue(new File("users/" + user.getUsername() + ".json"), user);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar usuário", e);
        }
    }


    @Override
    public void saveUserData(User user, String filename) throws GameSaveException {
        try {
            mapper.writeValue(new File(filename), user);
        } catch (IOException e) {
            throw new GameSaveException("Falha ao salvar usuário", e);
        }
    }

    @Override
    public User loadUserData(String username) throws GameLoadException {
        try {
            return mapper.readValue(new File("users/" + username + ".json"), User.class);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuário", e);
        }
    }

    
    @Override
    public User loadUserData(String username, String filename) throws GameLoadException {
        try {
            return mapper.readValue(new File(filename), User.class);
        } catch (IOException e) {
            throw new GameLoadException("Falha ao carregar usuário", e);
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
