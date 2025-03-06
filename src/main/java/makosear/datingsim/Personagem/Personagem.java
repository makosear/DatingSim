/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ice
 */
public class Personagem {
    protected String nome;
    public List<String> presentesAma;
    public List<String> presentesOdeia;
    public Map<String, Double> lugaresEncontro;
    
    @JsonCreator
    public Personagem(
        @JsonProperty("nome") String nome,
        @JsonProperty("presentesAma") List<String> presentesAma,
        @JsonProperty("presentesOdeia") List<String> presentesOdeia,
        @JsonProperty("lugaresEncontro") Map<String, Double> lugaresEncontro) {
        this.nome = nome;
        this.presentesAma = presentesAma;
        this.presentesOdeia = presentesOdeia;
        this.lugaresEncontro = lugaresEncontro;
    }

    // Add no-arg constructor
    public Personagem() {
        this("", new ArrayList<>(), new ArrayList<>(), new HashMap<>());
    }

    public String getNome() {
        return nome;
    }
    
}
