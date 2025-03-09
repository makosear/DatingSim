package makosear.datingsim.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import makosear.datingsim.Excecao.InvalidInputException;

public class ValidatorTest {
    @Test
    void validatorAceitaInputValido() {
        assertDoesNotThrow(() -> 
            Validator.validarCampo("validUser", "Username", 3, 20)
        );
    }

    @Test
    void validatorRejeitaNomePequeno() {
        Exception ex = assertThrows(InvalidInputException.class, () -> 
            Validator.validarCampo("ab", "Username", 3, 20)
        );
        assertTrue(ex.getMessage().contains("3 e 20 caracteres"));
    }
}
