package makosear.datingsim.GameStructure.GamePersistence;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.*;

public class XMLPersistence implements GamePersistence {
    private String XMLfilename;
    private final JAXBContext context;
    private StuffToSave stuffToSave;

    public XMLPersistence(StuffToSave stuffToSave) throws JAXBException {
        this.stuffToSave = stuffToSave;
        try {
            context = JAXBContext.newInstance(User.class, Admin.class, Default.class, Guest.class);
        } catch (JAXBException e) {
            throw new JAXBException("Erro ao criar JAXBContext", e);
        }
    }

    @Override
    public void saveUserData(User user) throws GameSaveException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, new File(XMLfilename));
        } catch (JAXBException e) {
            throw new GameSaveException("Falha ao salvar usuário em XML", e);
        }
    }

    public void saveUserData(User user, String filename) throws GameSaveException {
        XMLfilename = filename;
        saveUserData(user);
    }

    @Override
    public List<User> loadUserData(String filename) throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (List<User>) unmarshaller.unmarshal(new File(XMLfilename));
        } catch (JAXBException e) {
            throw new GameLoadException("Falha ao carregar usuário em XML", e);
        }
    }


    @Override
   public User loadUserData(String username, String filename) throws GameLoadException {
        XMLfilename = filename;
        return loadUserData(username);

    }

    @Override
    public void saveGameState(StuffToSave stuffToSave, String filename) throws GameSaveException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(stuffToSave, new File("gamestate.xml"));
        } catch (JAXBException e) {
            throw new GameSaveException("Falha ao salvar estado do jogo em XML", e);
        }
    }

    @Override
    public void loadGameState(String filename) throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StuffToSave loadedState = (StuffToSave) unmarshaller.unmarshal(new File(filename));

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

            


        } catch (JAXBException e) {
            throw new GameLoadException("Falha ao carregar estado do jogo em XML", e);
        }
    }

    // Implementar outros métodos da interface
}