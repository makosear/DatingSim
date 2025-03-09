package makosear.datingsim.GameStructure.GamePersistence;

import java.util.List;
import java.util.Map;

import makosear.datingsim.LocationToCharacters;
import makosear.datingsim.Personagem.NonRomanceable.NonRomanceable;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.User.User;

public interface LoadedState {

    public int getDiaAtual();
    public void setDiaAtual(int diaAtual);
    public String getPeriodoAtual();
    public void setPeriodoAtual(String periodoAtual);
    public PlayerCharacter getPlayer();
    public void setPlayer(PlayerCharacter player);
    public Map<Integer, List<LocationToCharacters>> getDayToLocationCharacters();
    public void setDayToLocationCharacters(Map<Integer, List<LocationToCharacters>>     dayToLocationCharacters);
    public User getCurrentUser();
    public void setCurrentUser(User currentUser);
    public List<String> getCurrentDialogue();
    public void setCurrentDialogue(List<String> currentDialogue);
    public String getCurrentCharacter();
    public void setCurrentCharacter(String currentCharacter);
    public int getDialogueBoxCounter();
    public void setDialogueBoxCounter(int dialogueBoxCounter);
    public boolean getIsWaitingOption();
    public void setIsWaitingOption(boolean isWaitingOption);
    public String getCurrentLocation();
    public void setCurrentLocation(String currentLocation);
    public String getMessageText();
    public void setMessageText(String messageText);
    public Map<String, Romanceable> getRomanceableCharacters();
    public void setRomanceableCharacters(Map<String, Romanceable> romanceableCharacters);
    public Map<String, NonRomanceable> getNonRomanceableCharacters();
    public void setNonRomanceableCharacters(Map<String, NonRomanceable> nonRomanceableCharacters);
}

