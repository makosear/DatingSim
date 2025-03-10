
//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.GameStructure;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.Personagem.Romanceable.Romanceable;
import makosear.datingsim.User.Admin;
import makosear.datingsim.User.Default;
import makosear.datingsim.User.Guest;
import makosear.datingsim.User.User;

/**
 *
 * @author ice
 */
public class ui {
    public boolean tryingSomething;
    final int DEBUG_PANEL_BG_NUM = 12;
    final int PLAYER_CREATION_BG_NUM = 8;
    final int TRYING_STUFF = 425;
    final int MSGBOX_Y = 410;
    

    JFrame window;
    public JButton btnSave;
    public JButton btnProfiles;
    public JButton backButton; //characterprofiles screen
    public JButton exitButton; // characterprofiles guest screen
    public JButton btnDebug;
    DatingSim gm;

    private JTextPane debugInfo;
    public JTextArea messageText;
    public String previousText;
    public JTextArea dayAndPeriodCounter;
    public JPanel characterManager;
    private List<JTextArea> optionTextAreas = new ArrayList<>();
    Map<String, JTextArea> characterTextMap;
    public JPanel bgPanel[] = new JPanel[15];
    public JLabel bgLabel[] = new JLabel[15];
    
    

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
        ImageIcon windowIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/iconWindow.png"));
        window.setIconImage(windowIcon.getImage());
        window.setTitle("Dating Sim");
        
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
        btnSave.addActionListener(e -> {System.out.println("lugar antigo eh " + gm.mudaLugar.previousLocation); gm.mudaLugar.menuButton("SaveMenu");});
        btnSave.setBounds(300, 25, 150, 30);
        btnSave.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
        window.add(btnSave);

        btnProfiles = new JButton("Character Profiles");
        btnProfiles.addActionListener(e -> 
                                gm.mudaLugar.menuButton("CharacterProfiles")
                                );
        btnProfiles.setBounds(500, 25, 200, 30); 
        btnProfiles.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
        window.add(btnProfiles);
        
        btnDebug = new JButton("D");
        btnDebug.addActionListener(e -> 
                                    gm.mudaLugar.menuButton("DebugMenu")
            );
        btnDebug.setBounds(725, 25, 25, 30); 
        btnDebug.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
        window.add(btnDebug);

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
                        if (gm.userService.currentUser instanceof Admin) {
                            String optionSelection = DatingSim.romanceableCharacters.get(gm.aHandler.currentCharacter).getCena().getResults(optionIndex);
                            if(optionSelection.startsWith("$g")) {
                                optionText.setForeground(Color.GREEN);
                            } else if(optionSelection.startsWith("$b")) {
                                optionText.setForeground(Color.RED);
                            }
                        }
                        else {
                            optionText.setForeground(Color.blue);
                        }

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
        gm.mudaLugar.addNewLocation("PlayerCreationMenu", PLAYER_CREATION_BG_NUM, ""); 
        
        bgPanel[PLAYER_CREATION_BG_NUM] = new JPanel();
        bgPanel[PLAYER_CREATION_BG_NUM].setBounds(0, 0, 800, 600);
        bgPanel[PLAYER_CREATION_BG_NUM].setBackground(Color.black);
        bgPanel[PLAYER_CREATION_BG_NUM].setLayout(null);

        // Title
        JLabel title = new JLabel("User Management");
        title.setBounds(0, 50, 800, 100);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Book Antiqua", Font.BOLD, 32));
        title.setForeground(Color.white);
        bgPanel[PLAYER_CREATION_BG_NUM].add(title);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(200, 150, 100, 30);
        styleLabel(userLabel);
        bgPanel[PLAYER_CREATION_BG_NUM].add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(320, 150, 280, 30);
        bgPanel[PLAYER_CREATION_BG_NUM].add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 200, 100, 30);
        styleLabel(passLabel);
        bgPanel[PLAYER_CREATION_BG_NUM].add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(320, 200, 280, 30);
        bgPanel[PLAYER_CREATION_BG_NUM].add(passwordField);

        // Error Message Area
        JTextArea errorArea = new JTextArea();
        errorArea.setBounds(200, 300, 400, 50);
        errorArea.setForeground(Color.RED);
        errorArea.setBackground(Color.BLACK);
        errorArea.setEditable(false);
        bgPanel[PLAYER_CREATION_BG_NUM].add(errorArea);

        // Buttons
        JButton btnLogin = createActionButton("Login", 250, 375, e -> 
            handleAuthAction(usernameField.getText(), 
                            new String(passwordField.getPassword()), 
                            "Login", 
                            errorArea));
        
        JButton btnRegister = createActionButton("Register", 350, 375, e -> {
            handleAuthAction(usernameField.getText(), 
                            new String(passwordField.getPassword()), 
                            "Register", 
                            errorArea);
        });

        JButton btnGuest = createActionButton("Guest", 450, 375, e -> 
            handleAuthAction("Guest", "", "Guest", errorArea));

        bgPanel[PLAYER_CREATION_BG_NUM].add(btnLogin);
        bgPanel[PLAYER_CREATION_BG_NUM].add(btnRegister);
        bgPanel[PLAYER_CREATION_BG_NUM].add(btnGuest);

        window.add(bgPanel[PLAYER_CREATION_BG_NUM]);
    }

    private void handleAuthAction(String username, String password, 
                                String actionType, JTextArea errorArea) {
        try {
            User user = null;

            switch(actionType) {
                case "Login":
                    user = gm.userService.authenticateUser(username, password);
                    break;

                case "Register":
                    user = new Default(username, password);
                    gm.userService.criarUsuario(user);
                    break;

                case "Guest":
                    user = new Guest("Guest_" + System.currentTimeMillis(), "");
                    break;
            }

            if(user != null) {
                gm.userService.currentUser = user;
                showUserDashboard(user);
                if (user.getProfileType().equalsIgnoreCase("ADMIN")) gm.mudaLugar.changeLocation("Map", "Admin powers enabled.");
                else if (!user.getUsername().startsWith("Guest_")) gm.mudaLugar.changeLocation("Map", "Welcome " + user.getUsername() + ". Choose a place to visit.");
                else { gm.mudaLugar.changeLocation("CharacterProfiles"); backButton.setVisible(false); exitButton.setVisible(true);};

            }
            
            else {
                errorArea.setText("Authentication failed.");
            }
        } 
        catch (InvalidInputException e) {
            errorArea.setText(e.getMessage());
        }
        catch (Exception e) {
            errorArea.setText("Authentication failed: " + e.getMessage());
        }
    }

    private JButton createActionButton(String text, int x, int y, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
        button.addActionListener(listener);
        return button;
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
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

        ImageIcon locationIcon = null;

        java.net.URL imgURL = getClass().getClassLoader().getResource(objFileName);
        if (imgURL != null) {
            locationIcon = new ImageIcon(imgURL);
        } else {
            System.err.println("Missing icon: " + objFileName);
            locationIcon = new ImageIcon(getClass().getClassLoader().getResource(""));

        }

        JButton locationButton = new JButton();

        locationButton.setBounds(objx, objy, objw, objh);
        locationButton.setBackground(null);
        locationButton.setContentAreaFilled(false);
        locationButton.setFocusPainted(false);
        locationButton.setIcon(locationIcon);
        locationButton.addActionListener(gm.aHandler);
        locationButton.setActionCommand(objCommand);
        locationButton.setBorderPainted(false);

        locationButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}   

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                    previousText = messageText.getText();
                    messageText.setText("Click to go to the" + objFileName.replaceFirst("icons/icon", "").replaceFirst("(?=[A-Z])", " ").replaceFirst(".png", ""));
                    messageText.append(".");
                    tryingSomething = true;

            }

            public void mouseExited(MouseEvent e) {
                if (tryingSomething)messageText.setText(previousText);
            }

        });

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
            if (location != "Map" && location != "characterScreen" && location != "MainMenu" && location != "SaveMenu" && !location.equals("CharacterProfiles")) {
                createBackground(gm.mudaLugar.bgToLocations.get(location), gm.mudaLugar.bgToFilePath.get(location));
                
                bgPanel[gm.mudaLugar.bgToLocations.get(location)].add(bgLabel[gm.mudaLugar.bgToLocations.get(location)]);
            }
        }
        createCharacterProfilesScreen();
        createDebugPanel();
        createSaveMenu();
        //SCREEN 8 - PLAYER CREATIO
        createPlayerCreationMenu();

    }


    public Component getPanel() {
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
        btnBack.addActionListener(e -> {System.out.println("lugar antigo eh " + gm.mudaLugar.previousLocation); gm.mudaLugar.changeLocation(gm.mudaLugar.previousLocation);});

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
                String slotLabel = location;
                if (location.equals("characterScreen")) {
                    slotLabel = rootNode.path("aHandler").path("currentCharacter").asText();
                }
                String text = String.format("<html>Slot %d Day: %s Location: %s</html>", 
                    slotNumber, diaAtual, slotLabel);
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

            String slotLabel = gm.mudaLugar.previousLocation;
            if (gm.mudaLugar.previousLocation.equals("characterScreen")) {
                slotLabel = gm.aHandler.currentCharacter;
            }

            // Update button text
            String text = "<html>Slot " + slotNumber + " "
                        + "Date: " + gm.diaAtual + " "
                        + "Location: " + slotLabel + "</html>";
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
            gm.addScenesToCharactersAfterLoad(); //reattach scenes

            // Refresh UI
            gm.ui.updateDayAndPeriodCounter();
            gm.mudaLugar.changeLocation(gm.mudaLugar.currentLocation);
            
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

    public void updateDescriptions() {
        for (Map.Entry<String, JTextArea> entry : characterTextMap.entrySet()) {
            entry.getValue().setText(DatingSim.romanceableCharacters.get(entry.getKey()).getDescription());
        }
    }

    public void createCharacterProfilesScreen() {
        final int PROFILES_BG_NUM = 11;
        gm.mudaLugar.addNewLocation("CharacterProfiles", PROFILES_BG_NUM, "");

        bgPanel[PROFILES_BG_NUM] = new JPanel();
        bgPanel[PROFILES_BG_NUM].setBounds(50, 50, 700, 450);
        bgPanel[PROFILES_BG_NUM].setBackground(Color.BLACK);
        bgPanel[PROFILES_BG_NUM].setLayout(new BoxLayout(bgPanel[PROFILES_BG_NUM], BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Character Profiles");
        titleLabel.setFont(new Font("Book Antiqua", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bgPanel[PROFILES_BG_NUM].add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10)); // One column for rows
        contentPanel.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(650, 350));
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(null);

        characterTextMap = new HashMap<>();

        for (Romanceable character : DatingSim.romanceableCharacters.values()) {
            JPanel entryPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            entryPanel.setBackground(Color.DARK_GRAY);
            entryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel imageLabel = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(character.getSpriteFilePath  ()));
            if (icon != null) {
                Image scaled = icon.getImage().getScaledInstance(-1, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            }
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            
            JTextArea descArea = new JTextArea(character.getDescription());
            descArea.setWrapStyleWord(true);
            descArea.setLineWrap(true);
            descArea.setEditable(false);
            descArea.setBackground(Color.DARK_GRAY);
            descArea.setForeground(Color.WHITE);
            descArea.setFont(new Font("Book Antiqua", Font.PLAIN, 16));

            entryPanel.add(imageLabel);
            entryPanel.add(descArea);
            contentPanel.add(entryPanel);
            characterTextMap.put(character.getNome(), descArea);    
        }
        

        backButton = new JButton("Back");
        styleButton(backButton);
        
        backButton.addActionListener(e -> {System.out.println("lugar antigo eh " + gm.mudaLugar.previousLocation); gm.mudaLugar.changeLocation(gm.mudaLugar.previousLocation);});
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        exitButton = new JButton("Exit to Login");
        styleButton(exitButton);
        exitButton.addActionListener(e -> {gm.userService.logoutUsuario(); gm.mudaLugar.changeLocation("PlayerCreationMenu");});
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setVisible(false);
        

        bgPanel[PROFILES_BG_NUM].add(scrollPane);
        bgPanel[PROFILES_BG_NUM].add(Box.createVerticalStrut(10));
        bgPanel[PROFILES_BG_NUM].add(backButton);
        bgPanel[PROFILES_BG_NUM].add(exitButton);

        window.add(bgPanel[PROFILES_BG_NUM]);
    }

    public void showUserDashboard(User user) {
        updateUIForUserType(user.getProfileType());
    } 

    private void updateUIForUserType(String profileType) {

        switch (profileType) {
            case "Admin":
            case "Default":
                break;
            case "Guest":
                backButton.setVisible(false); // basically a sanity check tbh
                break;
        }
        
    }

    private void createDebugPanel() {
        gm.mudaLugar.addNewLocation("DebugMenu", DEBUG_PANEL_BG_NUM, "");
    
        bgPanel[DEBUG_PANEL_BG_NUM] = new JPanel();
        bgPanel[DEBUG_PANEL_BG_NUM].setBounds(0, 0, 800, 600);
        bgPanel[DEBUG_PANEL_BG_NUM].setBackground(Color.black);
        bgPanel[DEBUG_PANEL_BG_NUM].setLayout(new BorderLayout());
    
        // Control Buttons
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.BLACK);
    
        // dividir em abas para debuginfo e charactermanager
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.BLACK);
        
        JButton btnDebugInfo = new JButton("Debug Info");
        styleButton(btnDebugInfo);
        btnDebugInfo.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "DEBUG_INFO");
        });

        JButton btnBack = new JButton("Back");
        styleButton(btnBack);
        btnBack.addActionListener(e -> {
            gm.mudaLugar.changeLocation(gm.mudaLugar.previousLocation);
        });
    
        JButton btnCharacterManager = new JButton("Character Manager");
        styleButton(btnCharacterManager);
        btnCharacterManager.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "CHARACTER_MANAGER");
        });
        
        controlPanel.add(btnDebugInfo);
        controlPanel.add(btnCharacterManager);
        controlPanel.add(btnBack);
        
        // DebugInfo
        debugInfo = new JTextPane();
        debugInfo.setContentType("text/html");
        debugInfo.setEditable(false);
        JScrollPane debugScrollPane = new JScrollPane(debugInfo);
        contentPanel.add(debugScrollPane, "DEBUG_INFO"); 
    
        // CharacterManager
        characterManager = new JPanel();
        characterManager.setLayout(new BorderLayout());

        // Left panel of the character manager
        JPanel spriteComboBox = new JPanel();
        spriteComboBox.setLayout(new BoxLayout(spriteComboBox, BoxLayout.Y_AXIS));
        spriteComboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        spriteComboBox.setPreferredSize(new Dimension(250, 500));        
        JLabel characterSpriteViewer = new JLabel();
        characterSpriteViewer.setAlignmentX(Component.CENTER_ALIGNMENT);
        characterSpriteViewer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel characterLabel = new JLabel("Select Character:");
        characterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] characterNames = new String[DatingSim.romanceableCharacters.size()];
        String[] characterFilepaths = new String[DatingSim.romanceableCharacters.size()];
        int i = 0;
        for (Romanceable character : DatingSim.romanceableCharacters.values()) {
            characterNames[i] = character.getNome();
            characterFilepaths[i] = character.getSpriteFilePath();
            i++;
        }

        
    
        JComboBox<String> characterNameComboBox = new JComboBox<>(characterNames);
        characterNameComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        characterNameComboBox.setPreferredSize(new Dimension(150, 25));
        characterNameComboBox.setMaximumSize(new Dimension(200, 25));

        

        spriteComboBox.add(characterLabel);
        spriteComboBox.add(characterNameComboBox);
        spriteComboBox.add(characterSpriteViewer);
        
        // Right panel of the character manager
        JPanel editableFields = new JPanel();
        editableFields.setLayout(new BoxLayout(editableFields, BoxLayout.Y_AXIS));
        editableFields.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Create a map to store all text fields for later reference
        Map<String, JComponent> fieldComponents = new HashMap<>();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Character");
        buttonPanel.add(saveButton);
        editableFields.add(saveButton);
    
        // Name field
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        namePanel.add(nameField);
        editableFields.add(namePanel);
        fieldComponents.put("nome", nameField);
    
        // Description field
        JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descPanel.add(new JLabel("Description:"));
        JTextArea descField = new JTextArea(5, 20);
        descField.setLineWrap(true);
        descField.setWrapStyleWord(true);
        editableFields.add(descPanel);
        fieldComponents.put("description", descField);
    
        // Sprite filepath
        JPanel spritePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> characterFilepathsComboBox = new JComboBox<>(characterFilepaths);
        characterFilepathsComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        characterFilepathsComboBox.setPreferredSize(new Dimension(150, 25));
        characterFilepathsComboBox.setMaximumSize(new Dimension(200, 25));
        spritePanel.add(new JLabel("Sprite File Path:"));
        spritePanel.add(characterFilepathsComboBox);
        editableFields.add(spritePanel);
        fieldComponents.put("spriteFilePath", characterFilepathsComboBox);
    
        // Encounter locations panel
        JPanel locationsPanel = new JPanel();
        locationsPanel.setLayout(new BoxLayout(locationsPanel, BoxLayout.Y_AXIS));
        locationsPanel.setBorder(BorderFactory.createTitledBorder("Encounter Locations (Sum must be 1.0)")); // esse createtitledborder eh mt util bixo eu qria ter usado isso para setar os graficos de personagens custom
    
        String[] locations = {"Cafe", "Gym", "Library", "Mall", "Office", "Park"};
        Map<String, JTextField> locationFields = new HashMap<>();
    
        for (String location : locations) {
            JPanel locationRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            locationRow.add(new JLabel(location + ":"));
            JTextField valueField = new JTextField("0.0", 5);
            locationRow.add(valueField);
            locationsPanel.add(locationRow);
            locationFields.put(location, valueField);
        }
    
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.add(new JLabel("Total:"));
        JLabel totalValueLabel = new JLabel("0.0");
        totalPanel.add(totalValueLabel);
    
        JButton refreshTotalButton = new JButton("Refresh Total");
        refreshTotalButton.addActionListener(e -> {
            double total = 0.0;
            for (JTextField field : locationFields.values()) {
                try {
                    total += Double.parseDouble(field.getText());
                } catch (NumberFormatException ex) {
                    // hmmmm um custom exception aq seria bom..
                }
            }
            totalValueLabel.setText(String.format("%.1f", total));
            
            // highlighta em vermelho se tiver errado
            if (Math.abs(total - 1.0) > 0.001) {
                totalValueLabel.setForeground(Color.RED);
            } else {
                totalValueLabel.setForeground(Color.BLACK);
            }
        });
        totalPanel.add(refreshTotalButton);
        locationsPanel.add(totalPanel);
        editableFields.add(locationsPanel);
        fieldComponents.put("lugaresEncontro", locationsPanel);
    
        JPanel morningPanel = createArrayTextFieldPanel("Morning Dialogs", 3);
        editableFields.add(morningPanel);
        fieldComponents.put("falasManha", morningPanel);
    
        JPanel afternoonPanel = createArrayTextFieldPanel("Afternoon Dialogs", 3);
        editableFields.add(afternoonPanel);
        fieldComponents.put("falasTarde", afternoonPanel);
    
        JPanel nightPanel = createArrayTextFieldPanel("Night Dialogs", 3);
        editableFields.add(nightPanel);
        fieldComponents.put("falasNoite", nightPanel);
    
        JPanel winCondPanel = new JPanel();
        winCondPanel.setLayout(new BoxLayout(winCondPanel, BoxLayout.Y_AXIS));
        winCondPanel.setBorder(BorderFactory.createTitledBorder("Win Conditions"));

        JPanel necessaryAffectionLevelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        necessaryAffectionLevelPanel.add(new JLabel("Necessary Affection Level:"));
        JTextField necessaryAffectionLevelValueField = new JTextField("", 30);
        necessaryAffectionLevelPanel.add(necessaryAffectionLevelValueField);
        winCondPanel.add(necessaryAffectionLevelPanel);
        fieldComponents.put("necessaryAffectionLevel", necessaryAffectionLevelPanel);

        // Panel for Necessary Cutscenes
        JPanel necessaryCutscenesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        necessaryCutscenesPanel.add(new JLabel("Necessary Cutscenes:"));
        JTextField necessaryCutscenesValueField = new JTextField("", 30);
        necessaryCutscenesPanel.add(necessaryCutscenesValueField);
        winCondPanel.add(necessaryCutscenesPanel);
        fieldComponents.put("necessaryCutscenes", necessaryCutscenesPanel);

        // Panel for Necessary Gifts
        JPanel necessaryGiftsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        necessaryGiftsPanel.add(new JLabel("Necessary Gifts:"));
        JTextField necessaryGiftsValueField = new JTextField("", 30);
        necessaryGiftsPanel.add(necessaryGiftsValueField);
        winCondPanel.add(necessaryGiftsPanel);
        fieldComponents.put("necessaryGifts", necessaryGiftsPanel);

        // Panel for Necessary Location Visits
        JPanel necessaryLocationVisitsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        necessaryLocationVisitsPanel.add(new JLabel("Necessary Location Visits:"));
        JTextField necessaryLocationVisitsValueField = new JTextField("", 30);
        necessaryLocationVisitsPanel.add(necessaryLocationVisitsValueField);
        winCondPanel.add(necessaryLocationVisitsPanel);
        fieldComponents.put("necessaryLocationVisits", necessaryLocationVisitsPanel);

        // Panel for Necessary Good Answers
        JPanel necessaryGoodAnswersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        necessaryGoodAnswersPanel.add(new JLabel("Necessary Good Answers:"));
        JTextField necessaryGoodAnswersValueField = new JTextField("", 30);
        necessaryGoodAnswersPanel.add(necessaryGoodAnswersValueField);
        winCondPanel.add(necessaryGoodAnswersPanel);
        fieldComponents.put("necessaryGoodAnswers", necessaryGoodAnswersPanel);

        saveButton.addActionListener(e -> {
            // Get the selected character
            String selectedCharacter = (String) characterNameComboBox.getSelectedItem();
            Romanceable character = DatingSim.romanceableCharacters.get(selectedCharacter);
            
            // Update character properties
            character.setNome(nameField.getText());
            character.setDescription(descField.getText());
            character.setSpriteFilePath(characterFilepathsComboBox.getSelectedItem().toString());
            //update the viewer
            String newFilepath = character.getSpriteFilePath();
            ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(newFilepath));
            characterSpriteViewer.setIcon(new ImageIcon(scaleifCharacter(objectIcon, TRYING_STUFF)));

            
            // Update locations
            Map<String, Double> newLocations = new HashMap<>();
            for (String location : locations) {
                try {
                    double value = Double.parseDouble(locationFields.get(location).getText());
                    newLocations.put(location, value);
                } catch (NumberFormatException ex) {
                    newLocations.put(location, 0.0);
                }
            }
            character.setLugaresEncontro(newLocations);
            
            // Update dialog lines (implementation depends on your data structure)
            // This is a placeholder - you'll need to implement according to your Romanceable class
            
            JOptionPane.showMessageDialog(null, "Character saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        editableFields.add(winCondPanel);

        editableFields.add(Box.createVerticalGlue());
        editableFields.setMinimumSize(editableFields.getPreferredSize());
    
        characterNameComboBox.addActionListener(e -> {
            String selectedCharacter = characterNameComboBox.getSelectedItem().toString();
            Romanceable character = DatingSim.romanceableCharacters.get(selectedCharacter);
        
            String filepath = character.getSpriteFilePath();
            ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(filepath));
            characterSpriteViewer.setIcon(new ImageIcon(scaleifCharacter(objectIcon, TRYING_STUFF)));
            

            nameField.setText(character.getNome());
            descField.setText(character.getDescription());
            characterFilepathsComboBox.setSelectedItem(character.getSpriteFilePath());
            
            Map<String, Double> characterLocations = character.getLugaresEncontro();
            for (String location : locationFields.keySet()) {
                Double value = characterLocations.get(location);
                locationFields.get(location).setText(value == null ? "0.0" : value.toString());
            }
        
            refreshTotalButton.doClick();
            
        });
        
        JScrollPane editableFieldsScrollPane = new JScrollPane(editableFields);
        editableFieldsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        characterManager.add(spriteComboBox, BorderLayout.WEST);
        characterManager.add(editableFieldsScrollPane, BorderLayout.CENTER);
        
        //JScrollPane characterScrollPane = new JScrollPane(characterManager);
        //characterScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPanel.add(characterManager, "CHARACTER_MANAGER"); 

        debugScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "DEBUG_INFO");  // Start with debug info visible
    
        bgPanel[DEBUG_PANEL_BG_NUM].add(controlPanel, BorderLayout.NORTH);
        bgPanel[DEBUG_PANEL_BG_NUM].add(contentPanel, BorderLayout.CENTER);
    
        window.add(bgPanel[DEBUG_PANEL_BG_NUM]);
    }
    
    // Helper method to create panels for array text fields
    private JPanel createArrayTextFieldPanel(String title, int numFields) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        
        for (int i = 0; i < numFields; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel("Line " + (i + 1) + ":"));
            JTextField textField = new JTextField(30);
            row.add(textField);
            panel.add(row);
        }
        
        return panel;
    }

    public void toggleDebugPanel() {
        if(bgPanel[DEBUG_PANEL_BG_NUM].isVisible()) {
            debugInfo.setText(getDebugInfo());
        }
        
        bgPanel[DEBUG_PANEL_BG_NUM].revalidate();
    }

    private String getDebugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>=== Character Status ===<br>");
        
        for (Romanceable character : DatingSim.romanceableCharacters.values()) {
            sb.append("<b>").append(character.getNome()).append("</b>:<br>")
              .append("<b>Affection: </b>").append(character.nivelDeAfeicao).append("<br>")
              .append("<b>Scenes Viewed: </b>").append(character.cenasVistas).append("<br>")
              .append("<b>Win Conditions Met: </b>").append(character.isWinConditionsMet(gm.player)).append("<br><br>")
              .append("<b>Current Location: </b>").append(
                  gm.dayToLocationCharacters.get(gm.diaAtual).stream()
                      .filter(locationToCharacters -> locationToCharacters.characters.contains(character.getNome()))
                      .findFirst()
                      .map(locationToCharacters -> locationToCharacters.location)
                      .orElse("Unknown"))
              .append("<br><br>");
        }
        
        sb.append("</html>");
        return sb.toString();
    }
}
