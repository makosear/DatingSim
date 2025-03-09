package makosear.datingsim.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.GameStructure.GamePersistence.JSONPersistence;

public class UserServiceTest {
    @Test
    void userAuthenticationWorksWithValidCredentials() throws InvalidInputException {
        
        UserService service = new UserService(new JSONPersistence(null));
        service.removerUsuario("testUser");
        service.criarUsuario(new Default("testUser", "securePass123"));
        
        User user = service.authenticateUser("testUser", "securePass123");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        service.removerUsuario("testUser");
    }

    @Test
    void userAuthenticationFailsWithWrongPassword() throws InvalidInputException {
        UserService service = new UserService(new JSONPersistence(null));
        service.removerUsuario("testUser");
        service.criarUsuario(new Default("testUser", "securePass123"));
        
        User user = service.authenticateUser("testUser", "wrongPass");
        assertNull(user);
        service.removerUsuario("testUser");
    }
}
