/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package makosear.datingsim.UI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

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
        createBackground();
        createObject();
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

    public void createBackground() {
        bgPanel[1] = new JPanel();
        bgPanel[1].setBounds(50,50,700,350);

        bgPanel[1].setBackground(Color.blue);
        bgPanel[1].setLayout(null);
        window.add(bgPanel[1]);

        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(0,0,700,350);

        java.net.URL imgURL = getClass().getClassLoader().getResource("Cafe_Interior_750x300.jpg");
        if (imgURL != null) {
            ImageIcon bgIcon = new ImageIcon(imgURL);
            bgLabel[1].setIcon(bgIcon);
        } else {
            System.err.println("Couldn't find file: res/Cafe_Interior.jpg");
        }

        
    }

    public void createObject() {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(400,150,200,200);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource("ch_1.png"));

        double x = 200 / (double) objectIcon.getIconHeight();

        Image scaledImage = objectIcon.getImage().getScaledInstance((int) (objectIcon.getIconWidth() * x), 200, java.awt.Image.SCALE_SMOOTH);
        
        objectIcon = new ImageIcon(scaledImage);
        
        objectLabel.setIcon(objectIcon);

        bgPanel[1].add(objectLabel);
        bgPanel[1].add(bgLabel[1]);

    }
}
