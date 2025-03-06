/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem.NonRomanceable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public PlayerCharacter(String nome, List<String> presentesAma, List<String> presentesOdeia, Map<String, Double> lugaresEncontro) {
        super(nome, presentesAma, presentesOdeia, lugaresEncontro);
    }

    public PlayerCharacter() {
        super(null, null, null, null);
    }

    public void setName(String name) {
        this.nome = name;
    }
    
}
