/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.Scene;

/**
 *
 * @author ice
 */
public class Scene {
    public boolean isViewed = false;
    private String dialogue;
    private String options;
    private String[] results;

    public Scene(String dialogue, String options, String[] results) {
        this.dialogue = dialogue;
        this.options = options;
        this.results = results;
    }

    public String getDialogue() {
        return dialogue;
    }

    public String getOptions() {
        return options;
    }

    public String getResults(int option) {
        return results[option];
    }


}
