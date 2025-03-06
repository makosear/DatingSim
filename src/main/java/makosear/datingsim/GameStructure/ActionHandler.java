package makosear.datingsim.GameStructure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import makosear.datingsim.DatingSim;

import java.util.*;

public class ActionHandler implements ActionListener {

    DatingSim gm;

    public ActionHandler(DatingSim gm) {
        this.gm = gm;
    }

    public ActionHandler() {
        this.gm = null;
    }

    private List<String> currentDialogue = new ArrayList<>();
    private String currentCharacter;
    private int dialogueBoxCounter = 0;
    private boolean isWaitingOption = false;

    public boolean isNoOneTalking() {
        return currentDialogue.isEmpty();
    }

    public void startDialogue(String dialogue) {

        String[] splitDialogue = dialogue.split("\n");

        for (String line : splitDialogue) {
                currentDialogue.add(line);
        }

        passDialogue();

    }

    public void chooseOption(int option) {
        if (isWaitingOption) {
            
            String optionText = DatingSim.romanceableCharacters.get(currentCharacter).getCena().getResults(option);
            String[] splitDialogue = optionText.split("\n");
            for (String line : splitDialogue) {
                currentDialogue.add(line);
            }
            dialogueBoxCounter++;
            isWaitingOption = false;
            gm.ui.unpopulateOptions();
            passDialogue();
        }
    }

    public void passDialogue() {
        if (!isWaitingOption) {
            if (isNoOneTalking()) { //send error 
                dialogueBoxCounter = 0;
            }

            else if (dialogueBoxCounter != 0 && dialogueBoxCounter == currentDialogue.size()) { // acabou o dialogo
                currentDialogue.clear();
                dialogueBoxCounter = 0;
                currentCharacter = "";
                gm.passaPeriodo();
            }

            else {
                String text = currentDialogue.get(dialogueBoxCounter); 
                //System.out.println(text);
                if (text.startsWith("$e")) gm.ui.closeWindow();
                if (!text.startsWith("$o")) { // not an option menu text
                    if (text.startsWith("$g")) {
                            text = text.replaceFirst("\\$g", "");
                            DatingSim.romanceableCharacters.get(currentCharacter).shiftAffection(+1);
                            DatingSim.romanceableCharacters.get(currentCharacter).cenasVistas++;
                        }
                    if (text.startsWith("$b")) {
                        text = text.replaceFirst("\\$b", "");
                        DatingSim.romanceableCharacters.get(currentCharacter).shiftAffection(-1);
                        DatingSim.romanceableCharacters.get(currentCharacter).cenasVistas++;
                    }
                    if (text.startsWith("$n")) {
                        text = text.replaceFirst("\\$n", "");
                        DatingSim.romanceableCharacters.get(currentCharacter).cenasVistas++;
                    }
                    gm.ui.messageText.setText(text);
                    dialogueBoxCounter++;
                }

                else {
                    String[] options = text.split("\\|");

                    //System.out.println(options[0]);

                    options[0] = options[0].replaceFirst("\\$o", "");
                    gm.ui.messageText.setText(options[0]);

                    gm.ui.populateOptions(options);

                    isWaitingOption = true;
                }
            }
        }
    }

    public void checkCharacter(String character) 
    {
        currentCharacter = character;
        gm.ui.bringCharacterScreen(character);
        startDialogue(DatingSim.romanceableCharacters.get(character).getCena().getDialogue   () + DatingSim.romanceableCharacters.get(character).getCena().getOptions());
    }

    public void winScene(String character) {
        currentCharacter = character;
        gm.ui.bringCharacterScreen(character);
        startDialogue(DatingSim.sceneHandler.getWinScene(character).getDialogue());
    }

    public void loseScene() {
        startDialogue(DatingSim.sceneHandler.getLoseScene().getDialogue());
    }

    public void talkToCharacter(String character) {
        currentCharacter = character;
                                if (isNoOneTalking()) 
                                    startDialogue(DatingSim.romanceableCharacters.get(currentCharacter).interact(gm.periodoAtual));
                                else passDialogue(); 
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        switch(yourChoice) {
            case "talkCh_Chiaki": talkToCharacter("Chiaki"); break;
            case "talkCh_Gaku": talkToCharacter("Gaku"); break;
            case "talkCh_Shu": talkToCharacter("Shu"); break;
            case "talkCh_Yato": talkToCharacter("Yato"); break;
            case "talkCh_Itsuki": talkToCharacter("Itsuki"); break;
            case "talkCh_Tsumugi": talkToCharacter("Tsumugi"); break;

            case "checkCh_Chiaki":  checkCharacter("Chiaki");             break;
            case "checkCh_Gaku":  checkCharacter("Gaku");               break;
            case "checkCh_Shu":  checkCharacter("Shu");                break;
            case "checkCh_Yato":  checkCharacter("Yato");               break;
            case "checkCh_Itsuki":  checkCharacter("Itsuki");             break;
            case "checkCh_Tsumugi":  checkCharacter("Tsumugi");            break;
            
            case "giftCh_Chiaki": gm.ui.messageText.setText("Chiaki: Aw, that's so nice of you! Appreciate it."); break;
            case "giftCh_Gaku": gm.ui.messageText.setText("Gaku: Cool, man! Are you trying to get points with me?"); break;
            case "giftCh_Shu": gm.ui.messageText.setText("Shu: How wonderful! I'm grateful for your kindness."); break;

            case "giftCh_Yato": gm.ui.messageText.setText("Yato: Tch... I don't wanna owe you anything. But if you are just handing it around..."); break;
            case "giftCh_Itsuki": gm.ui.messageText.setText("Itsuki: Hm? Is that for me? Ah, thank you. I wasn't expecting a gift today."); break;
            case "giftCh_Tsumugi": gm.ui.messageText.setText("Tsumugi: Oh, um. Do I really deserve this? Ah... you kinda put me on the spot. I feel awkward."); break;


            case "checkLocal": gm.ui.messageText.setText("You found an easter egg!"); break;

                //muda lugar

            case "goCafe": gm.mudaLugar.changeLocation("Cafe"); break;
            case "goLibrary": gm.mudaLugar.changeLocation("Library"); break;
            case "goGym": gm.mudaLugar.changeLocation("Gym"); break;
            case "goMall": gm.mudaLugar.changeLocation("Mall"); break;
            case "goOffice": gm.mudaLugar.changeLocation("Office"); break;
            case "goPark": gm.mudaLugar.changeLocation("Park"); break;

            // Doggo interactions

            case "talkDoggo": currentCharacter = "Doggo"; startDialogue("Doggo: Woof woof! *It runs away.*"); break;
            case "checkDoggo": currentCharacter = "Doggo"; startDialogue("Doggo: *wags tail and runs away.*"); break;
            case "giftDoggo": currentCharacter = "Doggo"; startDialogue("Doggo: *eats food and runs away.*"); break;
        }

        //wait for click on screen, until it does then run the following
    }

	public void keyTyped(KeyEvent ke) {
		System.out.println("Key typed: " + ke.getKeyChar());

        if(ke.getKeyChar() == 27) { // 27 is the key code for Esc
            if (gm.mudaLugar.currentLocation == "Cafe" || gm.mudaLugar.currentLocation == "Library" || gm.mudaLugar.currentLocation == "Gym" || gm.mudaLugar.currentLocation == "Mall" || gm.mudaLugar.currentLocation == "Office" || gm.mudaLugar.currentLocation == "Park" || gm.mudaLugar.currentLocation == "characterScreen" || gm.mudaLugar.currentLocation == "Doggo" || gm.mudaLugar.currentLocation == "Map") { 
                        System.out.println("Tried to go to savemenu");
                        gm.mudaLugar.changeLocation("SaveMenu");
            }
        }

	}
    
}
