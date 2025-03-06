/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem.NonRomanceable;
import java.util.List;
import java.util.Map;

import makosear.datingsim.GameStructure.WinConditions;
import makosear.datingsim.Gift.Gift;
import makosear.datingsim.Personagem.Personagem;
import makosear.datingsim.Personagem.NonRomanceable.*;
import makosear.datingsim.Scene.*;

/**
 
 * @author ice
 */
public class NonRomanceable extends Personagem{ 

    /* affection requirement x
all cutscenes are viewed x
gifted specific item 
visited specific location
correct answers counter */

    public int cenasVistas;
    private String falasManha[];
    private String falasTarde[];
    private String falasNoite[];
    private String spriteFilePath;
    //private List<Scene> scenes;
    //private List<Gift> giftsReceived = new java.util.ArrayList<>();
    


    
    public NonRomanceable(String nome, List<String> presentesAma, List<String> presentesOdeia, String spriteFilePath, String[] falasManha, String[] falasTarde, String[] falasNoite){ //List<Scene> scenes) {

        super(nome, presentesAma, presentesOdeia, null);
        this.spriteFilePath = spriteFilePath;
        this.falasManha = falasManha;
        this.falasTarde = falasTarde;
        this.falasNoite = falasNoite;
        //this.scenes = scenes;
        cenasVistas = 0;
    }

    public NonRomanceable(){
        super("", null, null, null);

    }

    public String getSpriteFilePath() {
        return spriteFilePath;
    }
    /* 
    public void receiveGift(Gift gift) {
        giftsReceived.add(gift);
    }
     
    public List<Gift> getGiftsReceived() {
        return giftsReceived;
    } */


    public String interact(String periodoAtual){
        if(periodoAtual == "Manha") return falasManha[(int)(Math.random() * falasManha.length)];
        else if(periodoAtual == "Tarde") return falasTarde[(int)(Math.random() * falasTarde.length)];
        else if (periodoAtual == "Noite") return falasNoite[(int)(Math.random() * falasNoite.length)];

        return "Hi."; 
    }


    /*
    public Scene getCena() {
        if (scenes.size() == 0) return null;
        if (cenasVistas >= scenes.size()) return new Scene(interact("Manha"),"", new String[] {""}); 
        return scenes.get(cenasVistas);
    } 

    public boolean isAllScenesViewed() {
        return cenasVistas >= scenes.size();
    } */

}
