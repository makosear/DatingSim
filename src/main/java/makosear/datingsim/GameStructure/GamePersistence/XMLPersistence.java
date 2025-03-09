package makosear.datingsim.GameStructure.GamePersistence;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

import makosear.datingsim.LocationToCharacters;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.*;

public class XMLPersistence implements GamePersistence {
    private final JAXBContext context;
    private final StuffToSave stuffToSave;

    public XMLPersistence(StuffToSave stuffToSave) throws JAXBException {
        this.stuffToSave = stuffToSave;
        this.context = JAXBContext.newInstance(
            StuffToSave.class, 
            User.class, 
            Admin.class, 
            Default.class, 
            Guest.class,
            LocationToCharacters.class
        );
    }

    @Override
    public void saveUserData(User user) throws GameSaveException {
        saveUserData(user, "users.xml");
    }

    @Override
    public void saveUserData(User user, String filename) throws GameSaveException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, new File(filename));
        } catch (JAXBException e) {
            throw new GameSaveException("Failed to save user data", e);
        }
    }

    @Override
    public List<User> loadUserData(String filename) throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserWrapper wrapper = (UserWrapper) unmarshaller.unmarshal(new File(filename));
            return wrapper.getUsers();
        } catch (JAXBException e) {
            throw new GameLoadException("Failed to load user data", e);
        }
    }

    @Override
    public User loadUserData(String username, String filename) throws GameLoadException {
        List<User> users = loadUserData(filename);
        return users.stream()
                   .filter(u -> u.getUsername().equals(username))
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public void saveGameState(LoadedState stuffToSave, String filename) throws GameSaveException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(stuffToSave, new File(filename));
        } catch (JAXBException e) {
            throw new GameSaveException("Failed to save game state", e);
        }
    }

    @Override
    public void loadGameState(String filename) throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StuffToSave loadedState = (StuffToSave) unmarshaller.unmarshal(new File(filename));
            stuffToSave.loadInformation();
        } catch (JAXBException e) {
            throw new GameLoadException("Failed to load game state", e);
        }
    }

    // Wrapper class for user list
    @XmlRootElement(name = "users")
    private static class UserWrapper {
        private List<User> users;

        public UserWrapper() {}

        public UserWrapper(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}