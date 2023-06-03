package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.*;

import controller.Player;
import quiz.Question;

public class AnswerQuestion {
    private JFrame frame;
    private Player player;
    private Question question;
    private int questionNr;
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;

    public AnswerQuestion(Player player, Question question, int questionNr) {
        this.player = player;
        this.question = question;
        this.questionNr = questionNr;
    }

    public void launch() {
        frame = new JFrame();
        //Platz für Schönheit
        frame.setBounds(100, 100, 607, 464);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

        JLabel questionLabel = new JLabel(question.getQuestion());
        //Platz für Schönheit
        questionLabel.setBounds(40, 71, 517, 65);
        frame.getContentPane().add(questionLabel);

        JLabel questionNrLabel = new JLabel("Question Number: " + questionNr);
        //Platz für Schönheit
        questionNrLabel.setBounds(40, 34, 247, 14);
        frame.getContentPane().add(questionNrLabel);

        answer1 = new JButton(question.getAnswers()[0]);
        //Platz für Schönheit
        answer1.setBounds(82, 200, 159, 78);
        frame.getContentPane().add(answer1);

        answer2 = new JButton(question.getAnswers()[1]);
        //Platz für Schönheit
        answer2.setBounds(357, 200, 159, 78);
        frame.getContentPane().add(answer2);

        answer3 = new JButton(question.getAnswers()[2]);
        //Platz für Schönheit
        answer3.setBounds(82, 311, 159, 78);
        frame.getContentPane().add(answer3);

        answer4 = new JButton(question.getAnswers()[3]);
        //Platz für Schönheit
        answer4.setBounds(357, 311, 159, 78);
        frame.getContentPane().add(answer4);

        JLabel selectAnswerLabel = new JLabel("Select an answer:");
		selectAnswerLabel.setBounds(50, 147, 429, 40);
		frame.getContentPane().add(selectAnswerLabel);

        setActionListeners(question.getCorrectAnswer());

        frame.setVisible(true);

        
    }
    public void setActionListeners(String correctAnswer) {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);   
        for (JButton button: buttons) {
            if (button.getText().equals(correctAnswer)) {
                button.addActionListener(new CorrectAnswer());
            } else {
                button.addActionListener(new WrongAnswer());
            }
        }                 
    }

    class WrongAnswer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, "Incorrect");
            frame.setVisible(false);
            frame.dispose();
            player.nextQuestion();
        }
    }

    class CorrectAnswer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, "Correct");
            frame.setVisible(false);
            frame.dispose();
            player.increaseScore();
            player.nextQuestion();
        }
    }
}
