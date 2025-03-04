/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.UI;

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
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import makosear.datingsim.DatingSim;

/**
 *
 * @author ice
 */
public class ui {
    JFrame window;

    DatingSim gm;

    public JTextArea messageText;
    public JTextArea dayAndPeriodCounter;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    
    

    public ui(DatingSim game) {
        this.gm = game;
        createMainField();
        generateScreen();
        window.setVisible(true);
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

        messageText.setBounds(50,410,700,150);

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

    Image scaleifCharacter(ImageIcon objectIcon) {
        double x = 200 / (double) objectIcon.getIconHeight();

        Image scaledImage = objectIcon.getImage().getScaledInstance((int) (objectIcon.getIconWidth() * x), 200, java.awt.Image.SCALE_SMOOTH);
        
        return scaledImage;
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

        if (objFileName.startsWith("characters/")) objectIcon = new ImageIcon(scaleifCharacter(objectIcon));
        
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

    public void bringCharacterScreen(String character) {
        createBackground(9, null);
        createObject(9, 400,150,800,800,"characters/ch_1.png", new String[]{"", "", ""}, new String[]{"", "", ""});

    }

    public void generateScreen() {

        //SCREEN 0 - TOWN MAP
        createBackground(0, "");
        createLocationButton(0, 100, 75, 64, 64, "icons/iconCoffeeShop.png", "goCafe1");
        createLocationButton(0, 300, 75, 64, 64, "icons/iconLibrary.png", "goCafe2");
        bgPanel[0].add(bgLabel[0]);


        //SCREEN 1 - CAFE 1
        createBackground(1, "backgrounds/Cafe_Interior_750x300.jpg");
        createObject(1, 400,150,200,200,"characters/ch_1.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh1", "checkCh1", "giftCh1"});
        createObject(1, 300, 150, 200, 200, "characters/ch_2.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh2", "checkCh2", "giftCh2"});
        createObject(1, 200, 150, 200, 200, "characters/ch_3.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh3", "checkCh3", "giftCh3"});

        bgPanel[1].add(bgLabel[1]);

        //SCREEN 2 - CAFE 2 

        createBackground(2, "backgrounds/Cafe_Interior_Evening_750x300.jpg");
        createObject(2, 400,150,200,200,"characters/ch_4.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh4", "checkCh4", "giftCh4"});
        createObject(2, 300, 150, 200, 200, "characters/ch_5.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh5", "checkCh5", "giftCh5"});
        createObject(2, 200, 150, 200, 200, "characters/ch_6.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh6", "checkCh6", "giftCh6"});

        createObject(2, 600, 20, 64, 64, "", new String[]{"Check"}, new String[]{"checkLocal"});

        bgPanel[2].add(bgLabel[2]);

    }
}
