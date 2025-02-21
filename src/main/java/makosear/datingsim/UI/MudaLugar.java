package makosear.datingsim.UI;

import makosear.datingsim.DatingSim;

public class MudaLugar {
    DatingSim gm;

    public MudaLugar(DatingSim gm) {
        this.gm = gm;
    }

    public void setCafe1() {
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.messageText.setText("You walk into the Cafe. Shu, Gaku, and Chiaki are present.");
    }

    public void setCafe2() {
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.messageText.setText("You walk into the Cafe. Tsumugi, Itsuki, and Yato are present.");
    }
    
}
