//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008

package makosear.datingsim.GameStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import makosear.datingsim.Gift.Gift;

public class WinConditions {

    /*  affection requirement
        all cutscenes are viewed
        gifted specific item
        visited specific location
        correct answers counter 
    */
    
    public int necessaryAffectionLevel = 0;
    public int necessaryCutscenes = 0;
    public List<Gift> necessaryGifts = new ArrayList<>();
    public Map<String, Integer> necessaryLocationVisits = new HashMap<>();
    public int necessaryGoodAnswers = 0;

    public WinConditions(int necessaryAffectionLevel, int necessaryCutscenes, List<Gift> necessaryGifts, Map<String, Integer> necessaryLocationVisits, int necessaryGoodAnswers) {
        this.necessaryAffectionLevel = necessaryAffectionLevel;
        this.necessaryCutscenes = necessaryCutscenes;
        this.necessaryGifts = necessaryGifts;
        this.necessaryLocationVisits = necessaryLocationVisits;
        this.necessaryGoodAnswers = necessaryGoodAnswers;
    }

    public WinConditions() {}
}

//int int List<Gift> Map<String, Integer> int