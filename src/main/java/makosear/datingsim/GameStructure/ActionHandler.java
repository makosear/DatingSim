package makosear.datingsim.GameStructure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import makosear.datingsim.DatingSim;

import java.util.*;

public class ActionHandler implements ActionListener {

    DatingSim gm;

    public ActionHandler(DatingSim gm) {
        this.gm = gm;
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

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        switch(yourChoice) {
            case "talkCh1":     if (isNoOneTalking()) 
                                    startDialogue("Chiaki: Hi! How are you doing?\nTest");
                                else passDialogue(); break;
            case "talkCh2":  gm.ui.messageText.setText("Gaku: Hi! How are you doing?"); break;
            case "talkCh3":  gm.ui.messageText.setText("Shu: Hi! How are you doing?"); break;
            case "talkCh4":  gm.ui.messageText.setText("Yato: Hi! How are you doing?"); break;
            case "talkCh5":     currentCharacter = "Itsuki";
                                if (isNoOneTalking()) 
                                    startDialogue(DatingSim.romanceableCharacters.get(currentCharacter).interact(gm.periodoAtual));
                                else passDialogue(); break;
            case "talkCh6":  gm.ui.messageText.setText("Tsumugi: Hi! How are you doing?"); break;

            case "checkCh1":  checkCharacter("Chiaki");             break;
            case "checkCh2":  checkCharacter("Gaku");               break;
            case "checkCh3":  checkCharacter("Shu");                break;
            case "checkCh4":  checkCharacter("Yato");               break;
            case "checkCh5":  checkCharacter("Itsuki");             break;
            case "checkCh6":  checkCharacter("Tsumugi");            break;
            
            case "giftCh1": gm.ui.messageText.setText("Chiaki: Aw, that's so nice of you! Appreciate it."); break;
            case "giftCh2": gm.ui.messageText.setText("Gaku: Cool, man! Are you trying to get points with me?"); break;
            case "giftCh3": gm.ui.messageText.setText("Shu: How wonderful! I'm grateful for your kindness."); break;

            case "giftCh4": gm.ui.messageText.setText("Yato: Tch... I don't wanna owe you anything. But if you are just handing it around..."); break;
            case "giftCh5": gm.ui.messageText.setText("Itsuki: Hm? Is that for me? Ah, thank you. I wasn't expecting a gift today."); break;
            case "giftCh6": gm.ui.messageText.setText("Tsumugi: Oh, um. Do I really deserve this? Ah... you kinda put me on the spot. I feel awkward."); break;


            case "checkLocal": gm.ui.messageText.setText("You found an easter egg!"); break;

                //muda lugar

            case "goCafe1": gm.mudaLugar.setCafe1(); break;
            case "goCafe2": gm.mudaLugar.setCafe2(); break;
        }

        //wait for click on screen, until it does then run the following
    }
    
}
