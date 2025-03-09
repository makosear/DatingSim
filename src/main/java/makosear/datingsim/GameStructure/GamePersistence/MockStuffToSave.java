package makosear.datingsim.GameStructure.GamePersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import makosear.datingsim.DatingSim;
import makosear.datingsim.LocationToCharacters;
import makosear.datingsim.Personagem.NonRomanceable.NonRomanceable;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.User.User;

public class MockStuffToSave implements LoadedState {
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
    private Map<String, Romanceable> romanceableCharacters = new HashMap<>();
    private Map<String, NonRomanceable> nonRomanceableCharacters = new HashMap<>();

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

