/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem;

import java.util.*;

/**
 *
 * @author ice
 */
public class Personagem {
    String nome;
    List<String> presentesAma;
    List<String> presentesOdeia;
    List<String> lugaresEncontro;
    
    public Personagem(String nome, List<String> presentesAma, List<String> presentesOdeia, List<String> lugaresEncontro){
        this.nome = nome;
        this.presentesAma = presentesAma;
        this.presentesOdeia = presentesOdeia;
        this.lugaresEncontro = lugaresEncontro;
    }

    public String getNome() {
        return nome;
    }
    
}
