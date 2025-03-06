/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Personagem.Romanceable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import makosear.datingsim.GameStructure.WinConditions;
import makosear.datingsim.Gift.Gift;
import makosear.datingsim.Personagem.Personagem;
import makosear.datingsim.Personagem.NonRomanceable.*;
import makosear.datingsim.Scene.*;

/**
 
 * @author ice
 */
public class Romanceable extends Personagem{ 

    /* affection requirement x
all cutscenes are viewed x
gifted specific item 
visited specific location
correct answers counter */
    public int nivelDeAfeicao;
    public int numeroRepostasBoas;

    public int cenasVistas;
    private String falasManha[];
    private String falasTarde[];
    private String falasNoite[];
    private String spriteFilePath;
    private List<Scene> scenes;
    private List<Gift> giftsReceived = new java.util.ArrayList<>();
    private WinConditions winConditions;
    private String description;



    public Romanceable(String nome, List<String> presentesAma, List<String> presentesOdeia, Map<String, Double> lugaresEncontro, String spriteFilePath, String[] falasManha, String[] falasTarde, String[] falasNoite, List<Scene> scenes, WinConditions winConditions, String description) {
        super(nome, presentesAma, presentesOdeia, lugaresEncontro);
        this.spriteFilePath = spriteFilePath;
        this.falasManha = falasManha;
        this.falasTarde = falasTarde;
        this.falasNoite = falasNoite;
        this.scenes = scenes;
        nivelDeAfeicao = 0;
        cenasVistas = 0;
        this.winConditions = winConditions;
        this.description = description;
    }

    public Romanceable(){
        super("", null, null, null);
        
    }

    public String getSpriteFilePath() {
        return spriteFilePath;
    }

    public void receiveGift(Gift gift) {
        giftsReceived.add(gift);
    }

    public List<Gift> getGiftsReceived() {
        return giftsReceived;
    }


    public String interact(String periodoAtual){
        if(periodoAtual == "Manha") return falasManha[(int)(Math.random() * falasManha.length)];
        else if(periodoAtual == "Tarde") return falasTarde[(int)(Math.random() * falasTarde.length)];
        else if (periodoAtual == "Noite") return falasNoite[(int)(Math.random() * falasNoite.length)];

        return "Hi."; 
    }

    @JsonIgnore
    public Scene getCena() {
        if (scenes.size() == 0) return null;
        if (cenasVistas >= scenes.size()) return new Scene(interact("Manha"),"", new String[] {""}); 
        return scenes.get(cenasVistas);
    }

    @JsonIgnore
    public boolean isAllScenesViewed() {
        return cenasVistas >= scenes.size();
    }

    public void shiftAffection(int amount) {
        nivelDeAfeicao += amount;
    }

    public boolean isWinConditionsMet(PlayerCharacter player) {
        if (winConditions.necessaryAffectionLevel > nivelDeAfeicao) return false;
        if (winConditions.necessaryCutscenes > cenasVistas) return false;

        if (winConditions.necessaryGifts != null) {
            for (Gift gift : winConditions.necessaryGifts) {
                if (!giftsReceived.contains(gift)) return false;
            }
        }   
        
        if (winConditions.necessaryGoodAnswers > numeroRepostasBoas) return false;
        if (winConditions.necessaryLocationVisits != null) {
            for (String location : winConditions.necessaryLocationVisits.keySet()) {
                if (player.locationVisitCounter.get(location) < winConditions.necessaryLocationVisits.get(location)) return false;
            }
        }
        return true;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
    
}
