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
    private String nome;
    public List<String> presentesAma;
    public List<String> presentesOdeia;
    public Map<String, Double> lugaresEncontro;
    
    public Personagem(String nome, List<String> presentesAma, List<String> presentesOdeia, Map<String, Double> lugaresEncontro){
        this.nome = nome;
        this.presentesAma = presentesAma;
        this.presentesOdeia = presentesOdeia;
        this.lugaresEncontro = lugaresEncontro;
    }

    public String getNome() {
        return nome;
    }
    
}
