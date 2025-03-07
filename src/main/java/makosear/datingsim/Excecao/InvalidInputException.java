//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.Excecao;

public class InvalidInputException extends Exception {
    public InvalidInputException(String field, String message) {
        super("Erro em " + field + ": " + message);
    }
}
