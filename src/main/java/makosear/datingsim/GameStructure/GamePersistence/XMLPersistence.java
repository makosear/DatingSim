package makosear.datingsim.GameStructure.GamePersistence;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.User.*;

public class XMLPersistence implements GamePersistence {
    private String XMLfilename;
    private final JAXBContext context;

    public XMLPersistence() throws JAXBException {
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
    public User loadUserData(String username) throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (User) unmarshaller.unmarshal(new File(XMLfilename));
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
    public void saveGameState(DatingSim game) throws GameSaveException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(game, new File("gamestate.xml"));
        } catch (JAXBException e) {
            throw new GameSaveException("Falha ao salvar estado do jogo em XML", e);
        }
    }

    @Override
    public DatingSim loadGameState() throws GameLoadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (DatingSim) unmarshaller.unmarshal(new File("gamestate.xml"));
        } catch (JAXBException e) {
            throw new GameLoadException("Falha ao carregar estado do jogo em XML", e);
        }
    }

    // Implementar outros métodos da interface
}