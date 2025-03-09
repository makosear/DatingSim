//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.GameStructure.GamePersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import makosear.datingsim.DatingSim;
import makosear.datingsim.LocationToCharacters;
import makosear.datingsim.Personagem.NonRomanceable.NonRomanceable;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.User.*;

public class StuffToSave implements LoadedState {
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

    public StuffToSave(DatingSim gm) {
        this.gm = gm;
    }

    public StuffToSave() {
        this.gm = null;
    }


    public void setGm(DatingSim gm){
        this.gm = gm;
    }


    public DatingSim getGm() {
        return gm;
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
        currentLocation = gm.mudaLugar.previousLocation;
        System.out.println("Current and previous:" + currentLocation + " " + gm.mudaLugar.currentLocation);
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

    @Override
    public int getDiaAtual() {
        return diaAtual;
    }

    @Override
    public void setDiaAtual(int diaAtual) {
        this.diaAtual = diaAtual;
    }

    @Override
    public String getPeriodoAtual() {
        return periodoAtual;
    }

    @Override
    public void setPeriodoAtual(String periodoAtual) {
        this.periodoAtual = periodoAtual;
    }

    @Override
    public PlayerCharacter getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    @Override
    public Map<Integer, List<LocationToCharacters>> getDayToLocationCharacters() {
        return dayToLocationCharacters;
    }

    @Override
    public void setDayToLocationCharacters(Map<Integer, List<LocationToCharacters>> dayToLocationCharacters) {
        this.dayToLocationCharacters = dayToLocationCharacters;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public List<String> getCurrentDialogue() {
        return currentDialogue;
    }

    @Override
    public void setCurrentDialogue(List<String> currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    @Override
    public String getCurrentCharacter() {
        return currentCharacter;
    }    

    @Override
    public void setCurrentCharacter(String currentCharacter) {      
        this.currentCharacter = currentCharacter;
    }

    @Override
    public int getDialogueBoxCounter() {
        return dialogueBoxCounter;
    }

    @Override
    public void setDialogueBoxCounter(int dialogueBoxCounter) {
        this.dialogueBoxCounter = dialogueBoxCounter;
    }

    @Override
    public boolean getIsWaitingOption() {
        return isWaitingOption;
    }

    @Override
    public void setIsWaitingOption(boolean isWaitingOption) {
        this.isWaitingOption = isWaitingOption;
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }   

    @Override
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String getMessageText() {
        return messageText;
    }

    @Override
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public Map<String, Romanceable> getRomanceableCharacters() {
        return romanceableCharacters;
    }

    @Override
    public void setRomanceableCharacters(Map<String, Romanceable> romanceableCharacters) {
        this.romanceableCharacters = romanceableCharacters;
    }

    @Override
    public Map<String, NonRomanceable> getNonRomanceableCharacters() {
        return nonRomanceableCharacters;
    }

    @Override
    public void setNonRomanceableCharacters(Map<String, NonRomanceable> nonRomanceableCharacters) {
        this.nonRomanceableCharacters = nonRomanceableCharacters;
    }


}
