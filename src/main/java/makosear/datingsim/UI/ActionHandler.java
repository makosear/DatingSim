package makosear.datingsim.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import makosear.datingsim.DatingSim;

public class ActionHandler implements ActionListener {

    DatingSim gm;

    public ActionHandler(DatingSim gm) {
        this.gm = gm;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        switch(yourChoice) {
            case "talkCh1":  gm.ui.messageText.setText("Chiaki: Hi! How are you doing?"); break;
            case "talkCh2":  gm.ui.messageText.setText("Gaku: Hi! How are you doing?"); break;
            case "talkCh3":  gm.ui.messageText.setText("Shu: Hi! How are you doing?"); break;

            case "checkCh1":  gm.ui.messageText.setText("Chiaki is just standing there. You should talk to him."); break;
            case "checkCh2":  gm.ui.messageText.setText("Gaku is just standing there. You should talk to him."); break;
            case "checkCh3":  gm.ui.messageText.setText("Shu is just standing there. You should talk to him."); break;
            
            case "giftCh1": gm.ui.messageText.setText("Chiaki: Aw, that's so nice of you! Appreciate it."); break;
            case "giftCh2": gm.ui.messageText.setText("Gaku: Cool, man! Are you trying to get points with me?"); break;
            case "giftCh3": gm.ui.messageText.setText("Shu: How wonderful! I'm grateful for your kindness."); break;

                //muda lugar

            case "goCafe1": gm.mudaLugar.setCafe1(); break;
            case "goCafe2": gm.mudaLugar.setCafe2(); break;
        }
    }
    
}
