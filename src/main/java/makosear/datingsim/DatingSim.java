/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package makosear.datingsim;

import makosear.datingsim.UI.ActionHandler;
import makosear.datingsim.UI.MudaLugar;
import makosear.datingsim.UI.ui;

/**
 *
 * @author ice
 */
public class DatingSim {
    private int diaAtual;
    private int periodoAtual;
    private int diasTotais;

    public void comecaJogo() {

    }

    public void terminaJogo() {

    }

    public ActionHandler aHandler = new ActionHandler(this);

    public ui ui = new ui(this);

    public MudaLugar mudaLugar = new MudaLugar(this);
    
    public static void main (String[] args) {
        new DatingSim();
    }

    public DatingSim() {
        mudaLugar.setCafe1();
    }
}