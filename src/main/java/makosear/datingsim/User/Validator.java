//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.User;

import makosear.datingsim.Excecao.InvalidInputException;

// Validator.java - Classe para validações
public class Validator {
    public static void validarUsuario(User user) throws InvalidInputException {
        validarCampo(user.getUsername(), "Username", 3, 20);
        validarCampo(user.getPassword(), "Password", 6, 30);
    }

    private static void validarCampo(String valor, String nomeCampo, int min, int max) 
        throws InvalidInputException {
        if(valor == null || valor.trim().isEmpty()) {
            throw new InvalidInputException(nomeCampo, "Não pode estar vazio");
        }
        if(valor.length() < min || valor.length() > max) {
            throw new InvalidInputException(nomeCampo, 
                "Deve ter entre " + min + " e " + max + " caracteres");
        }
    }
}
