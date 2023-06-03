package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;
import controller.Player;

public class Intro {
    private JFrame frame;
    private JButton createQuizButton;
    private JButton playQuizButton;
    private JPanel panel;
    private JLabel label;
    


    public void launch() {
        //Test für Schönheit
        Font text = new Font("serif", Font.BOLD, 20);
        frame = new JFrame("Menü");
        panel = new JPanel();

        label = new JLabel("Lernquiz für Studis");
        label.setFont(text);
        //Platz für Schönheit
        label.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        createQuizButton = new JButton("Create a new Quiz");
        createQuizButton.addActionListener(new CreateQuizButtonListener());
        createQuizButton.setFont(text);
        //Platz für Schönheit

        playQuizButton = new JButton("PLAY!");
        playQuizButton.addActionListener(new PlayQuizButtonListener());
        playQuizButton.setFont(text);
        
        panel.add(createQuizButton);
        panel.add(playQuizButton);
        //Platz für Schönheit
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        frame.getContentPane().add(BorderLayout.SOUTH,label);
		frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);

}   
    //erstellt einen Spieler, wenn der Play-Button gedrückt wird
    class PlayQuizButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new Player().launch();
        }
    }

    class CreateQuizButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new Editor().launch();
        }
    }


}