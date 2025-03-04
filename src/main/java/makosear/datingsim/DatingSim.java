/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package makosear.datingsim;

import makosear.datingsim.UI.ActionHandler;
import makosear.datingsim.UI.MudaLugar;
import makosear.datingsim.UI.SceneHandler;
import makosear.datingsim.UI.ui;
import makosear.datingsim.Character.Romanceable.*;
import makosear.datingsim.UI.BGMHandler;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author ice
 */
public class DatingSim {
    public int diaAtual = 1;
    public String periodoAtual = "Manha";

    public void comecaJogo() {

    }

    public void terminaJogo() {

    }

    public static SceneHandler sceneHandler = new SceneHandler();

    public static Map<String,Romanceable> romanceableCharacters = new HashMap<>();

    public ActionHandler aHandler = new ActionHandler(this);

    public BGMHandler bgmHandler = new BGMHandler();

    public MudaLugar mudaLugar = new MudaLugar(this);

    public ui ui = new ui(this);

   

    // initialize characters

    
    public static void main (String[] args) {
        inicializaPersonagens();
        new DatingSim();
    }

    public DatingSim() {
        diaAtual = 1;
        periodoAtual = "Manha";
        mudaLugar.setMap();
        bgmHandler.playMusic("src/main/resources/audio/MusMus-BGM-154.wav");
        
    }

    public static void inicializaPersonagens() {
        romanceableCharacters.put("Chiaki", new Romanceable(
            "Chiaki", 
            null, 
            null, 
            null,
            "characters/ch_1.png",
            new String[]{"good morning", "lol its mornin,", "chiaki de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "chiaki da"},
            new String[]{"good evening", "lol its night", "chiaki desu"},
            sceneHandler.getCenas("Chiaki")
        ));

        romanceableCharacters.put("Gaku", new Romanceable(
            "Gaku",
            null,
            null,
            null,
            "characters/ch_2.png",
            new String[]{"good morning", "lol its mornin,", "gaku de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "gaku da"},
            new String[]{"good evening", "lol its night", "gaku desu"},
            sceneHandler.getCenas("Gaku")
        ));

        romanceableCharacters.put("Shu", new Romanceable(
            "Shu",
            null,
            null,
            null,
            "characters/ch_3.png",
            new String[]{"good morning", "lol its mornin,", "shu de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "shu da"},
            new String[]{"good evening", "lol its night", "shu desu"},
            sceneHandler.getCenas("Shu")
        ));

        romanceableCharacters.put("Yato", new Romanceable(
            "Yato",
            null,
            null,
            null,
            "characters/ch_4.png",
            new String[]{"good morning", "lol its mornin,", "yato de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "yato da"},
            new String[]{"good evening", "lol its night", "yato desu"},
            sceneHandler.getCenas("Yato")
        ));

        romanceableCharacters.put("Itsuki", new Romanceable(
            "Itsuki", 
            null, 
            null, 
            null,
            "characters/ch_5.png",
            new String[]{"good morning", "lol its mornin,", "itsuki de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "itsuki da"},
            new String[]{"good evening", "lol its night", "itsuki desu"},
            sceneHandler.getCenas("Itsuki")
        ));

        romanceableCharacters.put("Tsumugi", new Romanceable(
            "Tsumugi", 
            null, 
            null, 
            null,
            "characters/ch_6.png",
            new String[]{"good morning", "lol its mornin,", "tsumugi de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "tsumugi da"},
            new String[]{"good evening", "lol its night", "tsumugi desu"},
            sceneHandler.getCenas("Tsumugi")
        ));

        
        
    }

    public void inicializaPeriodoNovo() {
        mudaLugar.setMap();
    }

    public void passaPeriodo(){
        if (periodoAtual.equals("Manha")) periodoAtual = "Tarde";
        else if (periodoAtual.equals("Tarde")) periodoAtual = "Noite";
        else if (periodoAtual.equals("Noite")) {
            periodoAtual = "Manha";
            diaAtual++;
        }

        ui.updateDayAndPeriodCounter();
        inicializaPeriodoNovo();
    }
}