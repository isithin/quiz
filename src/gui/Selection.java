package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controller.Player;

import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Selection {
    private JFrame frame;
    private Player player;
    private String[] quizzes;
    private JList<String> list;

    public Selection(Player player, String[] quizzes) {
        this.player = player;
        if (quizzes.length == 0) {
            this.quizzes = new String[1];
            this.quizzes[0] = "No Quizzes available";
        } else {
            this.quizzes = quizzes;
        }
    }

    public void launch() {
        frame = new JFrame();
        //Platz für Schönheit
        frame.setBounds(100, 100, 605, 472);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

        JButton play = new JButton("PLAY");
        //Platz für Schönheit
        play.setBounds(358, 76, 175, 72);
        play.addActionListener(new PlayQuiz());
        frame.getContentPane().add(play);

        //vllt noch was um High Scores anzuzeigen?
        //ab zum editor
        JButton delete = new JButton("DELETE");
        //Platz für Schönheit
        delete.setBounds(358, 320, 175, 72);
        delete.addActionListener(new DeleteQuiz());
        frame.getContentPane().add(delete);

        JLabel explanation = new JLabel("SELECT A QUIZ");
        //Platz für Schönheit
        explanation.setBounds(44, 27, 268, 25);
        explanation.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(explanation);

        JScrollPane scrollPane = new JScrollPane();
		//Platz für Schönheit
        scrollPane.setBounds(29, 75, 298, 317);
		frame.getContentPane().add(scrollPane);

        list = new JList<String>(quizzes);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);

        frame.setVisible(true);

    }

    public boolean validateSelection() {
        if (list.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "SELECT!!!");
            return false;
        } else if (quizzes[0].equals("No quizzes available")) {
            JOptionPane.showMessageDialog(null, "You cant select no quizzes");
        }
        return true;
    }

    public int getId() {
        String digit = "";
        String info = list.getSelectedValue();
        //int pos = info.indexOf("[Quiz id]: ");
        //System.out.println(pos);
        Pattern pattern = Pattern.compile("(\\d+$)");
        Matcher matcher = pattern.matcher(info);
        if (matcher.find()) {
            digit = matcher.group(1);
        }

        int res = Integer.parseInt(digit);

        return res;
    }

    class DeleteQuiz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (validateSelection()) {
                if(player.validateQuiz(getId())) {
                   int confirmation = JOptionPane.showConfirmDialog(null, "Sure?");
                   if (confirmation == JOptionPane.YES_OPTION) {
                       if (player.deleteQuiz(getId())) {
                           JOptionPane.showMessageDialog(null, "Success");
                           frame.setVisible(false);
                           frame.dispose();
                       } else {
                           JOptionPane.showMessageDialog(null, "Someone is still playing...");

                       }
                   } 
                } else {
                    JOptionPane.showMessageDialog(null, "The Quiz is already deleted");
                }
            }
        }
    }

    class PlayQuiz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(validateSelection()) {
                if(player.validateQuiz(getId())) {
                    String username = JOptionPane.showInputDialog("Enter username: ");
                    if (username.length() == 0) {
                        JOptionPane.showMessageDialog(null, "You have to enter a username.");
                    } else {
                        frame.setVisible(false);
                        frame.dispose();
                        player.newAttempt(username, getId());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quiz doesnt exist");
            }
        }
    }

}
