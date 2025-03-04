/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Character.Romanceable;
import java.util.List;

import makosear.datingsim.Character.Character;
import makosear.datingsim.Scene.*;

/**
 
 * @author ice
 */
public class Romanceable extends Character{ 


    public int nivelDeAfeicao;
    private String falasManha[];
    private String falasTarde[];
    private String falasNoite[];
    private String spriteFilePath;
    private List<Scene> scenes;

    public Romanceable(String nome, List<String> presentesAma, List<String> presentesOdeia, List<String> lugaresEncontro, String spriteFilePath, String[] falasManha, String[] falasTarde, String[] falasNoite, List<Scene> scenes) {
        super(nome, presentesAma, presentesOdeia, lugaresEncontro);
        this.spriteFilePath = spriteFilePath;
        this.falasManha = falasManha;
        this.falasTarde = falasTarde;
        this.falasNoite = falasNoite;
        this.scenes = scenes;
        nivelDeAfeicao = 0;
    }

    public String getSpriteFilePath() {
        return spriteFilePath;
    }


    public String interact(String periodoAtual){
        if(periodoAtual == "Manha") return falasManha[(int)(Math.random() * falasManha.length)];
        else if(periodoAtual == "Tarde") return falasTarde[(int)(Math.random() * falasTarde.length)];
        else if (periodoAtual == "Noite") return falasNoite[(int)(Math.random() * falasNoite.length)];

        return "Hi.";
    }

    public Scene getCena() {
        if (scenes.size() == 0) return null;
        if (nivelDeAfeicao >= scenes.size()) return new Scene(interact("Manha"),"", new String[] {""}); 
        return scenes.get(nivelDeAfeicao);
    }

    public void shiftAffection(int amount) {
        nivelDeAfeicao += amount;
    }
    
}
