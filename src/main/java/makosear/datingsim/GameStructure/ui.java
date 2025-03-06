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
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

import makosear.datingsim.DatingSim;
import makosear.datingsim.Excecao.GameLoadException;
import makosear.datingsim.Excecao.GameSaveException;
import makosear.datingsim.Excecao.InvalidInputException;
import makosear.datingsim.GameStructure.GamePersistence.*;
import makosear.datingsim.Personagem.NonRomanceable.PlayerCharacter;
import makosear.datingsim.User.Admin;
import makosear.datingsim.User.Default;
import makosear.datingsim.User.Guest;
import makosear.datingsim.User.User;

/**
 *
 * @author ice
 */
public class ui {
    final int PLAYER_CREATION_BG_NUM = 8;
    final int TRYING_STUFF = 425;
    final int MSGBOX_Y = 410;
    

    JFrame window;

    DatingSim gm;

    public JTextArea messageText;
    public JButton btnSave;
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
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                gm.aHandler.keyTyped(e);
            }
        });
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
        if (bgPanel[bgNum] == null) {
            bgPanel[bgNum] = new JPanel();
            bgPanel[bgNum].setBounds(50, 50, 700, 350);
            bgPanel[bgNum].setBackground(Color.BLACK);
            bgPanel[bgNum].setLayout(null);
            window.add(bgPanel[bgNum]);
        }
    
        if (bgLabel[bgNum] == null) {
            bgLabel[bgNum] = new JLabel();
            bgLabel[bgNum].setBounds(0, 0, 700, 350);
            bgPanel[bgNum].add(bgLabel[bgNum]);
        }
    
        if (bgFileName != null && !bgFileName.isEmpty()) {
            try {
                ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
                bgLabel[bgNum].setIcon(bgIcon);
            } catch (Exception e) {
                System.err.println("Error loading background: " + bgFileName);
            }
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
                //showUserDashboard(user);
                gm.mudaLugar.changeLocation("Map", "Welcome " + user.getUsername());
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
    /*
    public void showUserDashboard(User user) {
        if(user instanceof Admin) {
            // Show admin controls
            new AdminPanel(gm.userService).setVisible(true);
        }
        else if(user instanceof Default) {
            // Load player data
            gm.player = ((Default) user).getPlayerCharacter();
        }
        else {
            // Guest experience
            gm.player = new PlayerCharacter("Guest", new ArrayList<>(), new ArrayList<>(), new HashMap<>    ());
        }

        updateUIForUserType(user.getProfileType());
    } 

    private void updateUIForUserType(String profileType) {
        // Enable/disable features based on user type
        boolean isAdmin = profileType.equals("ADMIN");
        // ... update UI components accordingly
    } */

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
        if (gm.mudaLugar.bgToLocations.get(location) == null) {
            System.err.println("Invalid location ID for: " + location);
            return;
        }
        int locationId = gm.mudaLugar.bgToLocations.get(location);

        
        
        // First, ensure the background is set up
        if (bgPanel[locationId] == null) {
            createBackground(locationId, gm.mudaLugar.bgToFilePath.get(location));
            System.err.println("Failed to initialize panel for: " + location);
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

        if (bgLabel[locationId] != null) {
            bgPanel[locationId].add(bgLabel[locationId]);
        }
        
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

        //SCREEN 10 - SAVE MENU
        createSaveMenu();

        //SCREEN 8 - PLAYER CREATION
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
        if(saveFile.exists()) {
            try {
                JsonNode rootNode = gm.jsonPersistence.mapper.readTree(saveFile);
                String text = "<html>Slot " + slotNumber + "<br>"
                            + "Date: " + rootNode.path("diaAtual").asText() + "<br>"
                            + "Location: " + rootNode.path("mudaLugar").path("currentLocation") + "</       html>";
                button.setText(text);
            } catch (IOException e) {
                button.setText("Corrupted Save");
            }
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
            gm.jsonPersistence.saveGameState(gm, "saves/slot" + slotNumber + ".json");

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
            DatingSim loadedGame = gm.jsonPersistence.loadGameState("saves/slot" + slotNumber + ".json");

            // Update current game state
            gm.diaAtual = loadedGame.diaAtual;
            gm.periodoAtual = loadedGame.periodoAtual;
            gm.mudaLugar = loadedGame.mudaLugar;
            gm.player = loadedGame.player;

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

    public void postLoadInit() {
        createMainField();
        generateScreen();
        window.setVisible(true);
        gm.setLoadingFromSave(false);
    }
}
