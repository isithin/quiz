package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.Player;

public class Highscores {
    private JFrame frame;
    private Player player;
    private String[] scores;
    private JList<String> list;

    public Highscores(Player player, String[] scores) {
        this.player = player;
        this.scores = scores;
    }

    public void launch() {
        frame = new JFrame();
        frame.setBounds(100, 100, 605, 472);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Platz für Schönheit
        frame.getContentPane().setLayout(null);
        

        JLabel title = new JLabel("High Scores");
        //Platz für Schönheit
        title.setBounds(41, 32, 401, 56);
        frame.getContentPane().add(title);

        JScrollPane scrollPane = new JScrollPane();
        //Platz für Schönheit
        scrollPane.setBounds(29, 75, 298, 317);
        frame.getContentPane().add(scrollPane);

        list = new JList<String>(scores);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);

        frame.setVisible(true);;
    }


}
