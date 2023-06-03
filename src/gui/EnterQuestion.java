package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.Editor;

import java.awt.event.*;

public class EnterQuestion {
    private Editor editor;
    private JFrame frame;
    private JTextField question;
    private JTextField answer1;
    private JTextField answer2;
    private JTextField answer3;
    private JTextField answer4;
    private ArrayList<JRadioButton> buttons;

    public EnterQuestion(Editor editor) {
        this.editor = editor;
    }

    public void launch() {
        frame = new JFrame();
        //Platz für Schönheit
        frame.setBounds(100, 100, 605, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

        JLabel enterQuestion = new JLabel("Enter your Question");
        //Platz für Schönheit
        enterQuestion.setBounds(53, 39, 198, 51);
        frame.getContentPane().add(enterQuestion);

        question = new JTextField();
        //Platz für Schönheit
        question.setBounds(261, 44, 285, 44);
        frame.getContentPane().add(question);

        JLabel explanation = new JLabel("Give 4 answers and select the correct one");
        //Platz für Schönheit
        explanation.setBounds(52, 114, 469, 34);
        frame.getContentPane().add(explanation);

        answer1 = new JTextField();
        //Platz für Schönheit
        answer1.setBounds(66, 197, 165, 20);
        frame.getContentPane().add(answer1);

        answer2 = new JTextField();
        //Platz für Schönheit
        answer2.setBounds(316, 197, 165, 20);
        frame.getContentPane().add(answer2);

        answer3 = new JTextField();
        //Platz für Schönheit
        answer3.setBounds(66, 265, 165, 20);
        frame.getContentPane().add(answer3);

        answer4 = new JTextField();
        //Platz für Schönheit
        answer4.setBounds(316, 265, 165, 20);
        frame.getContentPane().add(answer4);

        JRadioButton button1 = new JRadioButton("0");
        //Platz für Schönheit
        button1.setBounds(237, 196, 23, 23);
        frame.getContentPane().add(button1);

        JRadioButton button2 = new JRadioButton("1");
        //Platz für Schönheit
        button2.setBounds(487, 196, 23, 23);
        frame.getContentPane().add(button2);

        JRadioButton button3 = new JRadioButton("2");
        //Platz für Schönheit
        button3.setBounds(237, 264, 23, 23);
        frame.getContentPane().add(button3);

        JRadioButton button4 = new JRadioButton("3");
        //Platz für Schönheit
        button4.setBounds(487, 264, 23, 23);
        frame.getContentPane().add(button4);

        //Kram einbauen für MC oder SC?

        buttons = new ArrayList<JRadioButton>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        JButton more = new JButton("Create more");
        //Platz für Schönheit
        more.setBounds(53, 315, 200, 66);
        more.addActionListener(new MoreQuestions());
        frame.getContentPane().add(more);

        JButton save = new JButton("Save all");
        //Platz für Schönheit
        save.setBounds(321, 315, 200, 66);
        save.addActionListener(new SaveAll());
        frame.getContentPane().add(save);

        frame.setVisible(true);
    }

    public boolean validateQuiz() {
        if (question.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter something");
        } else if (!completed()) {
            JOptionPane.showMessageDialog(null, "Please submit 4 answers");
        }
        return true;
    }

    public boolean completed() {
        JTextField[] answers = {answer1, answer2, answer3, answer4};
        for (int i = 0; i < answers.length; i++ ) {
            if (answers[i].getText().length() == 0) {
                return false;
            }
        }
        return true;
    }

    public void save() {
        String questionString = question.getText();
        String[] answers = {answer1.getText(), answer2.getText(), answer3.getText(), answer4.getText()};
        int correct = getCorrectAnswer();
        editor.addQuestion(questionString, answers, correct);
    }

    public int getCorrectAnswer() {
        for (JRadioButton button: buttons) {
            if (button.isSelected()) {
                return Integer.parseInt(button.getText());
            }
        }
        return 100; 
    }

    class MoreQuestions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (validateQuiz()) {
                save();
                frame.setVisible(false);
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Success");
                editor.getQuestions();
            }
        }
    }

    class SaveAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (validateQuiz()) {
                save();
                frame.setVisible(false);
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Success");
                editor.addToServer();
                JOptionPane.showMessageDialog(null, "Pushed to server");
            }
        }
    }
}
