/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.GameStructure;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.GameStructure.GamePersistence.*;

/**
 *
 * @author ice
 */
public class ui {
    final int PLAYER_CREATION_BG_NUM = 8;
    final int TRYING_STUFF = 425;
    final int MSGBOX_Y = 410;
    

    JFrame window;
    public JButton btnSave;
    DatingSim gm;

    public JTextArea messageText;
    public JTextArea dayAndPeriodCounter;
    private List<JTextArea> optionTextAreas = new ArrayList<>();
    public JPanel bgPanel[] = new JPanel[12];
    public JLabel bgLabel[] = new JLabel[12];
    
    

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

        btnSave = new JButton("Save/Load");
        btnSave.addActionListener(e -> gm.mudaLugar.changeLocation("SaveMenu"));
        btnSave.setBounds(600, 25, 100, 50);
        btnSave.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
        window.add(btnSave);

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

    public void createPlayerCreationMenu() {

        gm.mudaLugar.addNewLocation("PlayerCreationMenu", 
        PLAYER_CREATION_BG_NUM, 
        ""); 
    
        System.out.println("Creating player creation menu");
        bgPanel[PLAYER_CREATION_BG_NUM] = new JPanel();
        bgPanel[PLAYER_CREATION_BG_NUM].setBounds(0, 0, 800, 600);
        bgPanel[PLAYER_CREATION_BG_NUM].setBackground(Color.black);
        bgPanel[PLAYER_CREATION_BG_NUM].setLayout(null); // Null layout
        
        JLabel playerCreationTitle = new JLabel("Account information");
        playerCreationTitle.setBounds(0, 50, 800, 100);
        //set the text to the center
        playerCreationTitle.setHorizontalAlignment(JLabel.CENTER);
        playerCreationTitle.setFont(new Font("Book Antiqua", Font.PLAIN, 30));
        playerCreationTitle.setForeground(Color.white);
        bgPanel[PLAYER_CREATION_BG_NUM].add(playerCreationTitle);

        JTextArea playerNameLabel = new JTextArea("Name:");
        playerNameLabel.setBounds(250, 200, 100, 50);
        playerNameLabel.setBackground(Color.black);
        playerNameLabel.setForeground(Color.white);
        playerNameLabel.setEditable(false);
        playerNameLabel.setLineWrap(true);
        playerNameLabel.setWrapStyleWord(true);
        playerNameLabel.setEnabled(false);
        playerNameLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        bgPanel[PLAYER_CREATION_BG_NUM].add(playerNameLabel);

        //box to type the name and save it to gm.Player.setName("name");
        JTextField playerNameBox = new JTextField();
        playerNameBox.setBounds(350, 200, 200, 30);
        playerNameBox.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        bgPanel[PLAYER_CREATION_BG_NUM].add(playerNameBox);

        JTextArea playerCodeLabel = new JTextArea("Code:");
        playerCodeLabel.setBounds(250, 275, 100, 50);
        playerCodeLabel.setBackground(Color.black);
        playerCodeLabel.setForeground(Color.white);
        playerCodeLabel.setEditable(false);
        playerCodeLabel.setLineWrap(true);
        playerCodeLabel.setWrapStyleWord(true);
        playerCodeLabel.setEnabled(false);
        playerCodeLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        bgPanel[PLAYER_CREATION_BG_NUM].add(playerCodeLabel);

        JTextField playerCodeBox = new JTextField();
        playerCodeBox.setBounds(350, 275, 200, 30);
        playerCodeBox.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        bgPanel[PLAYER_CREATION_BG_NUM].add(playerCodeBox);


        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(350, 375, 100, 50);
        btnLogin.addActionListener(e -> buttonPlayerCreation(playerNameBox.getText(), playerCodeBox.getText(), "Login"));
        bgPanel[PLAYER_CREATION_BG_NUM].add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(500, 375, 100, 50);
        btnRegister.addActionListener(e -> buttonPlayerCreation(playerNameBox.getText(), playerCodeBox.getText(), "Register"));
        bgPanel[PLAYER_CREATION_BG_NUM].add(btnRegister);

        JButton btnGuest = new JButton("Guest");
        btnGuest.setBounds(350 - (500 - 350), 375, 100, 50);
        btnGuest.addActionListener(e -> buttonPlayerCreation(playerNameBox.getText(), playerCodeBox.getText(), "Guest"));
        bgPanel[PLAYER_CREATION_BG_NUM].add(btnGuest);


        bgPanel[PLAYER_CREATION_BG_NUM].revalidate();
        bgPanel[PLAYER_CREATION_BG_NUM].repaint();

        window.add(bgPanel[PLAYER_CREATION_BG_NUM]);

        
        

    }

    public void buttonPlayerCreation(String name, String code, String type) {
        switch(type) {
            case "Login":
                gm.player.setName(name);
                break;
            case "Register":
                gm.player.setName(name);
                break;
            case "Guest":
                gm.player.setName(name);
                break;
        }
        gm.mudaLugar.changeLocation("Map", "Click a place to visit.");
    }

    public void createMainMenu() {
        System.out.println("Creating main menu");
        final int START_MENU_BGNUM = 7;
    
        bgPanel[START_MENU_BGNUM] = new JPanel();
        bgPanel[START_MENU_BGNUM].setBounds(50, 100, 800, 600);
        bgPanel[START_MENU_BGNUM].setBackground(Color.black);
        bgPanel[START_MENU_BGNUM].setLayout(null); // Null layout
        window.add(bgPanel[START_MENU_BGNUM]);

        JLabel gameTitle = new JLabel("Dating Sim");
        gameTitle.setBounds(220, 20, 300, 100);
        gameTitle.setFont(new Font("Book Antiqua", Font.PLAIN, 50));
        gameTitle.setForeground(Color.white);
        bgPanel[START_MENU_BGNUM].add(gameTitle);
    
        
        //JButton btnStart = new JButton("New Game");
        //JButton btnLoad = new JButton("Load Game");
        JButton btnExit = new JButton("Exit");
        JButton btnLogin = new JButton("Login");

        final int BUTTON_X = 250;
        final int BUTTON_Y = 180;
        final int BUTTON_WIDTH = 200;
        final int BUTTON_HEIGHT = 50;
        final int BUTTON_SPACING_VERTICAL = 75;
    
        
        //btnStart.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        //btnLoad.setBounds(BUTTON_X, BUTTON_Y + BUTTON_SPACING_VERTICAL, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnLogin.setBounds(BUTTON_X, BUTTON_Y + BUTTON_SPACING_VERTICAL, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnExit.setBounds(BUTTON_X, BUTTON_Y + BUTTON_SPACING_VERTICAL * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
    
        
        //btnStart.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        //btnLoad.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        btnLogin.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        btnExit.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
    
        //btnStart.setFocusable(false);
        //btnLoad.setFocusable(false);
        btnLogin.setFocusable(false);
        btnExit.setFocusable(false);
    
        
        /* btnStart.addActionListener(e -> gm.mudaLugar.changeLocation("PlayerCreationMenu", ""));
        btnLoad.addActionListener(e -> {
            try {
                gm.jsonPersistence.loadGameState();
            } catch (GameLoadException e1) {
                e1.printStackTrace();
            }
        }); */
        btnLogin.addActionListener(e -> gm.mudaLugar.changeLocation("PlayerCreationMenu", ""));
        btnExit.addActionListener(e -> closeWindow());
    
        
        //bgPanel[START_MENU_BGNUM].add(btnStart);
        //bgPanel[START_MENU_BGNUM].add(btnLoad);
        bgPanel[START_MENU_BGNUM].add(btnLogin);
        bgPanel[START_MENU_BGNUM].add(btnExit);

        bgPanel[START_MENU_BGNUM].setVisible(false);
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
            if (location != "Map" && location != "characterScreen" && location != "MainMenu" && location != "SaveMenu") {
                createBackground(gm.mudaLugar.bgToLocations.get(location), gm.mudaLugar.bgToFilePath.get(location));
                
                bgPanel[gm.mudaLugar.bgToLocations.get(location)].add(bgLabel[gm.mudaLugar.bgToLocations.get(location)]);
            }
        }

        createSaveMenu();
        //SCREEN 8 - PLAYER CREATIO
        createPlayerCreationMenu();

    }


    public Component getPanel() {
        // TODO Auto-generated method stub
        return window;
    }

     public void createSaveMenu() {
        final int SAVE_MENU_BG_NUM = 10;
        gm.mudaLugar.addNewLocation("SaveMenu", SAVE_MENU_BG_NUM, "");

        bgPanel[SAVE_MENU_BG_NUM] = new JPanel();
        bgPanel[SAVE_MENU_BG_NUM].setBounds(0, 0, 800, 600);
        bgPanel[SAVE_MENU_BG_NUM].setBackground(Color.black);
        bgPanel[SAVE_MENU_BG_NUM].setLayout(new GridLayout(4, 1));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel title = new JLabel("Save/Load Game");
        title.setFont(new Font("Book Antiqua", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // Save Slots
        JPanel slotsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        slotsPanel.setBackground(Color.BLACK);

        for(int i = 1; i <= 3; i++) {
            JButton slotButton = createSaveSlotButton(i);
            slotsPanel.add(slotButton);
        }

        // Control Buttons
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.BLACK);

        JButton btnBack = new JButton("Back");
        styleButton(btnBack);
        btnBack.addActionListener(e -> gm.mudaLugar.changeLocation("Map"));

        controlPanel.add(btnBack);

        // Error Message Area
        JTextArea errorArea = new JTextArea();
        errorArea.setEditable(false);
        errorArea.setForeground(Color.RED);
        errorArea.setBackground(Color.BLACK);
        errorArea.setFont(new Font("Book Antiqua", Font.PLAIN, 16));

        bgPanel[SAVE_MENU_BG_NUM].add(headerPanel);
        bgPanel[SAVE_MENU_BG_NUM].add(slotsPanel);
        bgPanel[SAVE_MENU_BG_NUM].add(controlPanel);
        bgPanel[SAVE_MENU_BG_NUM].add(errorArea);

        window.add(bgPanel[SAVE_MENU_BG_NUM]);
    }

    private JButton createSaveSlotButton(int slotNumber) {
        JButton button = new JButton();
        button.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Load save metadata if exists
        File saveFile = new File("saves/slot" + slotNumber + ".json");
        if (saveFile.exists()) {
            try {
                JsonNode rootNode = gm.jsonPersistence.mapper.readTree(saveFile);
                String diaAtual = rootNode.path("diaAtual").asText();
                String location = rootNode.path("mudaLugar").path("currentLocation").asText();
                String text = String.format("<html>Slot %d<br>Day: %s<br>Location: %s</html>", 
                    slotNumber, diaAtual, location);
                button.setText(text);
            } catch (IOException e) {
                button.setText("Corrupted Save");
            }
        } else {
            button.setText("Empty Slot");
        }
        // Right-click menu
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        saveItem.addActionListener(e -> handleSave(slotNumber, button));
        loadItem.addActionListener(e -> handleLoad(slotNumber, button));

        contextMenu.add(saveItem);
        contextMenu.add(loadItem);

        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    contextMenu.show(button, e.getX(), e.getY());
                }
            }
        });

        return button;
    }

    private void handleSave(int slotNumber, JButton button) {
        try {
            // Create saves directory if needed
            new File("saves").mkdirs();

            // Save game state
            gm.stuffToSave.updateInformation();
            gm.jsonPersistence.saveGameState(gm.stuffToSave, "saves/slot" + slotNumber + ".json");

            // Update button text
            String text = "<html>Slot " + slotNumber + "<br>"
                        + "Date: " + gm.diaAtual + "<br>"
                        + "Location: " + gm.mudaLugar.currentLocation + "</html>";
            button.setText(text);

            // Show confirmation
            JOptionPane.showMessageDialog(window, "Game saved successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (GameSaveException e) {
            JOptionPane.showMessageDialog(window, "Failed to save game: " + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLoad(int slotNumber, JButton button) {
        try {
            // Load into the existing game instance
            gm.jsonPersistence.loadGameState("saves/slot" + slotNumber + ".json");
            
            // Refresh UI
            gm.ui.updateDayAndPeriodCounter();
            gm.mudaLugar.changeLocation(gm.mudaLugar.currentLocation, "Game loaded");
            
        } catch (GameLoadException e) {
            JOptionPane.showMessageDialog(window, "Failed to load game: " + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        button.setBackground(new Color(30, 30, 30));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
