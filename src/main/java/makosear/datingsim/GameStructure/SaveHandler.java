package makosear.datingsim.GameStructure;

import java.io.*;

import makosear.datingsim.DatingSim;

public class SaveHandler {
    public static void salvarJogo(String arquivo, DatingSim estado) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(arquivo))) {
            oos.writeObject(estado);
        }
    }

    public static DatingSim carregarJogo(String arquivo) 
        throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(arquivo))) {
            return (DatingSim) ois.readObject();
        }
    }
}

