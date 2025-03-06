/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem.NonRomanceable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import makosear.datingsim.Personagem.Personagem;

/**
 *
 * @author ice
 */
/* affection requirement
all cutscenes are viewed
gifted specific item
visited specific location
correct answers counter */
public class PlayerCharacter extends Personagem{

    public Map<String, Integer> locationVisitCounter = new HashMap<>();
    
    @JsonCreator
    public PlayerCharacter(
        @JsonProperty("nome") String nome,
        @JsonProperty("presentesAma") List<String> presentesAma,
        @JsonProperty("presentesOdeia") List<String> presentesOdeia,
        @JsonProperty("lugaresEncontro") Map<String, Double> lugaresEncontro) {
        super(nome, presentesAma, presentesOdeia, lugaresEncontro);
    }

    // Add no-arg constructor for Jackson
    public PlayerCharacter() {
        super("", new ArrayList<>(), new ArrayList<>(), new HashMap<>());
    }

    public void setName(String name) {
        this.nome = name;
    }
    
}
