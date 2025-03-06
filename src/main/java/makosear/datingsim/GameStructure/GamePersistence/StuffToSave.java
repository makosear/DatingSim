package makosear.datingsim.GameStructure.GamePersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import com.fasterxml.jackson.annotation.JsonIgnore;

import makosear.datingsim.DatingSim;
import makosear.datingsim.LocationToCharacters;
import makosear.datingsim.Gift.*;
import makosear.datingsim.Personagem.*;
import makosear.datingsim.Personagem.NonRomanceable.NonRomanceable;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.User.*;

public class StuffToSave {
    @JsonIgnore
    private DatingSim gm;
    
    private int diaAtual;
    private String periodoAtual;
    private PlayerCharacter player;
    private Map<Integer, List<LocationToCharacters>> dayToLocationCharacters;
    private User currentUser;
    private List<String> currentDialogue;
    private String currentCharacter;
    private int dialogueBoxCounter;
    private boolean isWaitingOption;
    private String currentLocation;
    private String messageText;
    public Map<String,Romanceable> romanceableCharacters = new HashMap<>();
    public Map<String, NonRomanceable> nonRomanceableCharacters = new HashMap<>();

    public StuffToSave() {
        this.gm = null;
    }

    public void setGm(DatingSim gm){
        this.gm = gm;
    }

    public DatingSim getGm() {
        return gm;
    }

    public StuffToSave(DatingSim gm) {
        this.gm = gm;
    }

    public void updateInformation() {
        diaAtual = gm.diaAtual;
        periodoAtual = gm.periodoAtual;
        player = gm.player;
        dayToLocationCharacters = gm.dayToLocationCharacters;
        currentUser = gm.userService.currentUser;
        currentDialogue = gm.aHandler.currentDialogue;
        currentCharacter = gm.aHandler.currentCharacter;
        dialogueBoxCounter = gm.aHandler.dialogueBoxCounter;
        isWaitingOption = gm.aHandler.isWaitingOption;
        currentLocation = gm.mudaLugar.currentLocation;
        messageText = gm.ui.messageText.getText();
        romanceableCharacters = DatingSim.romanceableCharacters;
        nonRomanceableCharacters = DatingSim.nonRomanceableCharacters;  
        
    }

    public void loadInformation() {
        gm.diaAtual = diaAtual;
        gm.periodoAtual = periodoAtual;
        gm.player = player;
        gm.dayToLocationCharacters = dayToLocationCharacters;
        gm.userService.currentUser = currentUser;
        gm.aHandler.currentDialogue = currentDialogue;
        gm.aHandler.currentCharacter = currentCharacter;
        gm.aHandler.dialogueBoxCounter = dialogueBoxCounter;
        gm.aHandler.isWaitingOption = isWaitingOption;
        gm.mudaLugar.currentLocation = currentLocation;
        DatingSim.romanceableCharacters = romanceableCharacters;
        DatingSim.nonRomanceableCharacters = nonRomanceableCharacters;
        gm.mudaLugar.changeLocation(currentLocation, "");
        gm.ui.messageText.setText(messageText);
    }

    //all getters and setters

    public int getDiaAtual() {
        return diaAtual;
    }

    public void setDiaAtual(int diaAtual) {
        this.diaAtual = diaAtual;
    }

    public String getPeriodoAtual() {
        return periodoAtual;
    }

    public void setPeriodoAtual(String periodoAtual) {
        this.periodoAtual = periodoAtual;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    public Map<Integer, List<LocationToCharacters>> getDayToLocationCharacters() {
        return dayToLocationCharacters;
    }

    public void setDayToLocationCharacters(Map<Integer, List<LocationToCharacters>> dayToLocationCharacters) {
        this.dayToLocationCharacters = dayToLocationCharacters;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<String> getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(List<String> currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public String getCurrentCharacter() {
        return currentCharacter;
    }    

    public void setCurrentCharacter(String currentCharacter) {      
        this.currentCharacter = currentCharacter;
    }

    public int getDialogueBoxCounter() {
        return dialogueBoxCounter;
    }

    public void setDialogueBoxCounter(int dialogueBoxCounter) {
        this.dialogueBoxCounter = dialogueBoxCounter;
    }

    public boolean getIsWaitingOption() {
        return isWaitingOption;
    }

    public void setIsWaitingOption(boolean isWaitingOption) {
        this.isWaitingOption = isWaitingOption;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }   

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Map<String, Romanceable> getRomanceableCharacters() {
        return romanceableCharacters;
    }

    public void setRomanceableCharacters(Map<String, Romanceable> romanceableCharacters) {
        this.romanceableCharacters = romanceableCharacters;
    }

    public Map<String, NonRomanceable> getNonRomanceableCharacters() {
        return nonRomanceableCharacters;
    }

    public void setNonRomanceableCharacters(Map<String, NonRomanceable> nonRomanceableCharacters) {
        this.nonRomanceableCharacters = nonRomanceableCharacters;
    }


}
