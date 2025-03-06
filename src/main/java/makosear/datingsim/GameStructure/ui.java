/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.GameStructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import makosear.datingsim.DatingSim;

/**
 *
 * @author ice
 */
public class ui {
    final int TRYING_STUFF = 425;
    final int MSGBOX_Y = 410;
    

    JFrame window;

    DatingSim gm;

    public JTextArea messageText;
    public JTextArea dayAndPeriodCounter;
    private List<JTextArea> optionTextAreas = new ArrayList<>();
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    
    

    public ui(DatingSim game) {
        this.gm = game;
        createMainField();
        generateScreen();
        window.setVisible(true);
    }

    public void closeWindow(){
        window.dispose();
    }

    public void updateDayAndPeriodCounter() {
        dayAndPeriodCounter.setText("Day " + gm.diaAtual + ", " + gm.periodoAtual);
    }

    public void createMainField() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setResizable(false);

        messageText = new JTextArea("Sample text");

        messageText.setBounds(50, MSGBOX_Y,700,150);

        messageText.setBackground(Color.black);

        messageText.setForeground(Color.white);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEnabled(false);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));

        dayAndPeriodCounter = new JTextArea("Day " + gm.diaAtual + ", " + (String) gm.periodoAtual);
        dayAndPeriodCounter.setBounds(50, 25, 700, 50);
        dayAndPeriodCounter.setBackground(Color.black);
        dayAndPeriodCounter.setForeground(Color.white);
        dayAndPeriodCounter.setEditable(false);
        dayAndPeriodCounter.setLineWrap(true);
        dayAndPeriodCounter.setWrapStyleWord(true);
        dayAndPeriodCounter.setEnabled(false);
        dayAndPeriodCounter.setFont(new Font("Book Antiqua", Font.PLAIN, 26));

        messageText.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}   

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gm.aHandler.passDialogue();
                }
            }

            public void mouseReleased(MouseEvent e) {
                
            }

            public void mouseEntered(MouseEvent e) {

                    //objectLabel.setVisible(false);

            }

            public void mouseExited(MouseEvent e) {}

        });
 
        window.add(messageText);
        window.add(dayAndPeriodCounter);
    }

    public void displayLoseScreen() {
        gm.aHandler.loseScene();
    }

    public void displayWinScreen(String winner) {
        gm.aHandler.winScene(winner);
    }

    public void unpopulateOptions() {
        for (JTextArea optionText : optionTextAreas) {
            window.remove(optionText);
        }
        optionTextAreas.clear();
        messageText.setVisible(true);
    }

    public void populateOptions(String[] options) {
        messageText.setVisible(false);
        final int ONE_LINE_HEIGHT = 30;

        for (int i = 0; i < options.length; i++) {
            final int optionIndex = i - 1;
            String option = options[i];
            JTextArea optionText = new JTextArea(option);
            optionTextAreas.add(optionText);
            optionText.setBounds(50, 410 + i*ONE_LINE_HEIGHT, 700, ONE_LINE_HEIGHT);
            optionText.setBackground(Color.black);
            optionText.setForeground(Color.white);
            optionText.setEditable(false);
            optionText.setLineWrap(true);
            optionText.setWrapStyleWord(true);
            
            //optionText.setFocusable(true);
            optionText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
            if (i != 0){
                optionText.setEnabled(true);
                optionText.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {}   
                
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            gm.aHandler.chooseOption(optionIndex);
                        }
                    }
                
                    public void mouseReleased(MouseEvent e) {

                    }
                
                    public void mouseEntered(MouseEvent e) {
                        optionText.setForeground(Color.blue);

                    }
                
                    public void mouseExited(MouseEvent e) {
                        optionText.setForeground(Color.white );
                    }
                    
                });
            }
            window.add(optionText);
        }


    }

    public void createBackground(int bgNum, String bgFileName) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50,700,350);
        

        bgPanel[bgNum].setBackground(Color.WHITE);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,700,350);

        java.net.URL imgURL = getClass().getClassLoader().getResource(bgFileName);
        if (imgURL != null) {
            ImageIcon bgIcon = new ImageIcon(imgURL);
            bgLabel[bgNum].setIcon(bgIcon);
        } else {
            System.err.println("Couldn't find file: " + bgFileName);
        }

        
    }

    public void createMainMenu(){

        System.out.println("Creating main menu");
        final int START_MENU_BGNUM = 7;

        bgPanel[START_MENU_BGNUM] = new JPanel();
        bgPanel[START_MENU_BGNUM].setBounds(50,50,700,350);
        

        bgPanel[START_MENU_BGNUM].setBackground(Color.WHITE);
        bgPanel[START_MENU_BGNUM].setLayout(null);
        window.add(bgPanel[START_MENU_BGNUM]);

        bgLabel[START_MENU_BGNUM] = new JLabel();
        bgLabel[START_MENU_BGNUM].setBounds(0,0,700,350);

        // I want three labels, two of them will be buttons, one starts the game, the other loads a save

        JLabel startButton = new JLabel();
        startButton.setBounds(0,0,700,350);
        startButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gm.mudaLugar.changeLocation("Map");
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        bgPanel[START_MENU_BGNUM].add(startButton);

    }

    Image scaleifCharacter(ImageIcon objectIcon, int newHeight) {
        double newWidth = newHeight / (double) objectIcon.getIconHeight();

        Image scaledImage = objectIcon.getImage().getScaledInstance((int) (objectIcon.getIconWidth() * newWidth), newHeight, java.awt.Image.SCALE_SMOOTH);
        
        return scaledImage;
    }

    public void createCharacterScreenObject(int bgNum, int objx, int objy, int objw, int objh, String objFileName) {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objw, objh);
        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectIcon = new ImageIcon(scaleifCharacter(objectIcon, TRYING_STUFF));
        objectLabel.setIcon(objectIcon);
        bgPanel[bgNum].add(objectLabel);
    }

    public void createObject(int bgNum, int objx, int objy, int objw, int objh, String objFileName, String[] optionNames, String[] optionCommands) {
        JPopupMenu popMenu = new JPopupMenu();

        JMenuItem menuItem[] = new JMenuItem[4]; // use 1 2 3
        
        int i = 1;
        for (String option : optionNames) {
            menuItem[i] = new JMenuItem(option);
            menuItem[i].addActionListener(gm.aHandler);
            menuItem[i].setActionCommand(optionCommands[i-1]);
            popMenu.add(menuItem[i]);
            i++;
        } 

       
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objw, objh);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));

        //check if objFileName starts with "characters/" to determine if it's a character or not

        if (objFileName.startsWith("characters/")) objectIcon = new ImageIcon(scaleifCharacter(objectIcon, 200));
        
        objectLabel.setIcon(objectIcon);
        objectLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}   

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(objectLabel, e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                
            }

            public void mouseEntered(MouseEvent e) {

                    //objectLabel.setVisible(false);

            }

            public void mouseExited(MouseEvent e) {}

        });

        bgPanel[bgNum].add(objectLabel);
        

    }

    public void createLocationButton(int bgNum, int objx, int objy, int objw, int objh, String objFileName, String objCommand) {
        ImageIcon locationIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));

        JButton locationButton = new JButton();

        locationButton.setBounds(objx, objy, objw, objh);
        locationButton.setBackground(null);
        locationButton.setContentAreaFilled(false);
        locationButton.setFocusPainted(false);
        locationButton.setIcon(locationIcon);
        locationButton.addActionListener(gm.aHandler);
        locationButton.setActionCommand(objCommand);
        locationButton.setBorderPainted(false);

        bgPanel[bgNum].add(locationButton);


    }

    public void removeCharactersFromLocation(String location) {
        int locationId = gm.mudaLugar.bgToLocations.get(location);
        bgPanel[locationId].removeAll();
    }

    public void addCharacterToLocation(String location, String characterName, CharacterPosition characterPosition) {
        int locationId = gm.mudaLugar.bgToLocations.get(location);
        
        // First, ensure the background is set up
        if (bgPanel[locationId] == null) {
            createBackground(locationId, gm.mudaLugar.bgToFilePath.get(location));
        }

        //check if 
        if (characterName == "Doggo") {
            //System.out.println("Doggo added");
            createObject(locationId, characterPosition.getX(), characterPosition.getY(), characterPosition.getWidth(), characterPosition.getHeight(), 
            "characters/Dog.png", 
            new String[]{"Talk", "Check", "Give gift"}, 
            new String[]{"talkDoggo", "checkDoggo", "giftDoggo"});
        }
        
        // Create the object on top of the existing background
        else {
            createObject(locationId, characterPosition.getX(), characterPosition.getY(), characterPosition.getWidth(), characterPosition.getHeight(), 
            DatingSim.romanceableCharacters.get(characterName).getSpriteFilePath(), 
            new String[]{"Talk", "Check", "Give gift"}, 
            new String[]{"talkCh_" + characterName, "checkCh_" + characterName, "giftCh_" + characterName});
        }
        
        // Ensure the background label is on top of the background panel
        bgPanel[locationId].add(bgLabel[locationId]);
        
        // Repaint to ensure the new object is visible
        bgPanel[locationId].revalidate();
        bgPanel[locationId].repaint();
    }

    public void bringCharacterScreen(String character) {
        createBackground(9, gm.mudaLugar.bgToFilePath.get(gm.mudaLugar.currentLocation));

        createCharacterScreenObject(9, 250, 80, TRYING_STUFF, TRYING_STUFF, DatingSim.romanceableCharacters.get(character).getSpriteFilePath());
        //System.out.println(DatingSim.romanceableCharacters.get(character).getSpriteFilePath());

        //300, 150, 200, 200

        bgPanel[9].add(bgLabel[9]);

        gm.mudaLugar.addNewLocation("characterScreen", 9, gm.mudaLugar.bgToFilePath.get(gm.mudaLugar.currentLocation));

        gm.mudaLugar.changeLocation("characterScreen", "");

    }

    public void deleteCharacterScreen() {
        gm.mudaLugar.removeLocation("characterScreen");
        
        bgPanel[9].setVisible(false);
        bgPanel[9].removeAll();
        bgPanel[9].repaint();
        bgPanel[9] = null;
        bgLabel[9] = null;

        window.remove(bgPanel[9]);
    }

    public void generateScreen() {

        final int ICON_FIRST_COLUMN = 100;
        final int ICON_SECOND_COLUMN = ICON_FIRST_COLUMN + 200;
        final int ICON_THIRD_COLUMN = ICON_SECOND_COLUMN + 200;
        final int ICON_FIRST_ROW = 75; 
        final int ICON_SECOND_ROW = ICON_FIRST_ROW * 3;

        //SCREEN 7 - START MENU

        createMainMenu();

        //SCREEN 0 - TOWN MAP
        createBackground(gm.mudaLugar.bgToLocations.get("Map"), gm.mudaLugar.bgToFilePath.get("Map"));

        createLocationButton(0, ICON_FIRST_COLUMN, ICON_FIRST_ROW, 64, 64, "icons/iconCoffeeShop.png", "goCafe");
        createLocationButton(0, ICON_SECOND_COLUMN, ICON_FIRST_ROW, 64, 64, "icons/iconLibrary.png", "goLibrary");
        createLocationButton(0, ICON_THIRD_COLUMN, ICON_FIRST_ROW, 64, 64, "icons/iconGym.png", "goGym");
        createLocationButton(0, ICON_FIRST_COLUMN, ICON_SECOND_ROW, 64, 64, "icons/iconMall.png", "goMall");
        createLocationButton(0, ICON_SECOND_COLUMN, ICON_SECOND_ROW, 64, 64, "icons/iconOffice.png", "goOffice");
        createLocationButton(0, ICON_THIRD_COLUMN, ICON_SECOND_ROW, 64, 64, "icons/iconPark.png", "goPark");
        //bgPanel[0].add(bgLabel[0]);

        List<String> locations = new ArrayList<>(gm.mudaLugar.bgToLocations.keySet());

        for (String location : locations) {
            if (location != "Map" && location != "characterScreen" && location != "MainMenu") { 
                createBackground(gm.mudaLugar.bgToLocations.get(location), gm.mudaLugar.bgToFilePath.get(location));
                
                bgPanel[gm.mudaLugar.bgToLocations.get(location)].add(bgLabel[gm.mudaLugar.bgToLocations.get(location)]);
            }
        }

    }


    public Component getPanel() {
        // TODO Auto-generated method stub
        return window;
    }
}
