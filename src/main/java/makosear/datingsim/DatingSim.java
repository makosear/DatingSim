/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package makosear.datingsim;

import makosear.datingsim.UI.ActionHandler;
import makosear.datingsim.UI.MudaLugar;
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

    public ActionHandler aHandler = new ActionHandler(this);

    public BGMHandler bgmHandler = new BGMHandler();

    public ui ui = new ui(this);

    public MudaLugar mudaLugar = new MudaLugar(this);

    public Map<String,Romanceable> romanceableCharacters = new HashMap<>();

    // initialize characters

    
    public static void main (String[] args) {
        new DatingSim();
    }

    public DatingSim() {
        diaAtual = 1;
        periodoAtual = "Manha";
        inicializaPersonagens();
        mudaLugar.setMap();
        bgmHandler.playMusic("src/main/resources/audio/MusMus-BGM-154.wav");
        
    }

    public void inicializaPersonagens() {
        romanceableCharacters.put("Itsuki", new Romanceable(
            "Itsuki", 
            null, 
            null, 
            null,
            "characters/ch_4.png",
            new String[]{"good morning", "lol its mornin,", "itsuki de gozamaisu"},
            new String[]{"good afternoon", "lol its nonn", "itsuki da"},
            new String[]{"good evening", "lol its night", "itsuki desu"}
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