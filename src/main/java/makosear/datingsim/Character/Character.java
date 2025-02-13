/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Character;

import java.util.*;

/**
 *
 * @author ice
 */
public class Character {
    String nome;
    List<String> presentesAma;
    List<String> presentesOdeia;
    List<String> lugaresEncontro;
    
    public Character(String nome, List<String> presentesAma, List<String> presentesOdeia, List<String> lugaresEncontro){
        this.nome = nome;
        this.presentesAma = presentesAma;
        this.presentesOdeia = presentesOdeia;
        this.lugaresEncontro = lugaresEncontro;
    }
    
}
