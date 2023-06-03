package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.*;

import controller.Editor;

public class NamingQuiz {
    private Editor editor;
    private JFrame frame;
    private JTextField text;

    public NamingQuiz(Editor editor) {
        this.editor = editor;
    }

    public void launch() {
        frame = new JFrame();
        //Platz für Schönheit
        frame.setBounds(100, 100, 606, 455);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

        JLabel enterNameLabel = new JLabel("Your Quizname:");
        //Platz für Schönheit
        enterNameLabel.setBounds(32, 72, 418, 116);
        frame.getContentPane().add(enterNameLabel);

        text = new JTextField();
        //Platz für Schönheit
        text.setBounds(55, 191, 478, 20);
        frame.getContentPane().add(text);

        JButton next = new JButton("NEXT");
        //PLatz für Schönheit
        next.setBounds(336, 303, 200, 50);
        next.addActionListener(new Next());
        frame.getContentPane().add(next);

        
        frame.setVisible(true); 
    }
    class Next implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if ((text.getText()).length() == 0) {
                JOptionPane.showMessageDialog(null, "You have to enter a quizname");
            } else {
                editor.create(text.getText());
                frame.setVisible(false);
                frame.dispose();
                editor.getQuestions();
            }
        }
    }
}


