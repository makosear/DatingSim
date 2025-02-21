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
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    
    

    public ui(DatingSim game) {
        this.gm = game;
        createMainField();
        generateScreen();
        window.setVisible(true);
    }

    public void createMainField() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        messageText = new JTextArea("SAMPLE TEXT AAAAA");

        messageText.setBounds(50,400,700,150);

        messageText.setBackground(Color.black);

        messageText.setForeground(Color.white);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEnabled(false);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
 
        window.add(messageText);
    }

    public void createBackground(int bgNum, String bgFileName) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50,700,350);
        

        bgPanel[bgNum].setBackground(Color.blue);
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

    public void createObject(int bgNum, int objx, int objy, int objw, int objh, String objFileName, String[] optionNames, String[] optionCommands) {
        JPopupMenu popMenu = new JPopupMenu();

        JMenuItem menuItem[] = new JMenuItem[4]; // use 1 2 3
        /* 
        int i = 1;
        for (String option : optionNames) {
            menuItem[i] = new JMenuItem(i-1);
            menuItem[i].addActionListener(gm.aHandler);
            menuItem[i].setActionCommand(optionCommands[i-1]);
            popMenu.add(menuItem[i]);
            i++;
        } */
        menuItem[1] = new JMenuItem(optionNames[0]);
        menuItem[1].addActionListener(gm.aHandler);
        menuItem[1].setActionCommand(optionCommands[0]);
        popMenu.add(menuItem[1]);


        menuItem[2] = new JMenuItem(optionNames[1]);
        menuItem[2].addActionListener(gm.aHandler);
        menuItem[2].setActionCommand(optionCommands[1]);
        popMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(optionNames[2]);
        menuItem[3].addActionListener(gm.aHandler);
        menuItem[3].setActionCommand(  optionCommands[2]);
        popMenu.add(menuItem[3]);



        

       
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objw, objh);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));

        double x = 200 / (double) objectIcon.getIconHeight();

        Image scaledImage = objectIcon.getImage().getScaledInstance((int) (objectIcon.getIconWidth() * x), 200, java.awt.Image.SCALE_SMOOTH);
        
        objectIcon = new ImageIcon(scaledImage);
        
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

    public void generateScreen() {
        //SCREEN 1
        createBackground(1, "Cafe_Interior_750x300.jpg");
        createObject(1, 400,150,200,200,"ch_1.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh1", "checkCh1", "giftCh1"});
        createObject(1, 300, 150, 200, 200, "ch_2.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh2", "checkCh2", "giftCh2"});
        createObject(1, 200, 150, 200, 200, "ch_3.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh3", "checkCh3", "giftCh3"});

        createLocationButton(1, 100, 75, 64, 64, "locationCafe.png", "goCafe2");

        bgPanel[1].add(bgLabel[1]);

        //SCREEN 2

        createBackground(2, "Cafe_Interior_Evening_750x300.jpg");
        createObject(2, 400,150,200,200,"ch_4.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh1", "checkCh1", "giftCh1"});
        createObject(2, 300, 150, 200, 200, "ch_5.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh2", "checkCh2", "giftCh2"});
        createObject(2, 200, 150, 200, 200, "ch_6.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh3", "checkCh3", "giftCh3"});

        createLocationButton(2, 100, 75, 64, 64, "locationCafe.png", "goCafe1");
        //createObject(2, 600, 20, 64, 64, "ch_7.png", new String[]{"Talk", "Check", "Give gift"}, new String[]{"talkCh1", "checkCh1", "giftCh1"});

        bgPanel[2].add(bgLabel[2]);

    }
}
