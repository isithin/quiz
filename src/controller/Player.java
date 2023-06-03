package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import java.rmi.NotBoundException;
import gui.*;
import server.*;
import quiz.*;

public class Player {
    private RemoteInterface remoteInterface;
    private Attempt attempt;
    private Quiz activeQuiz;
    private int score;
    private int count;

    public void launch() {
        connect();
        new Selection(this, getQuizzes()).launch();
    }

    public void connect() {
        try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			remoteInterface = (RemoteInterface) reg.lookup("Quizzer");
			System.out.println("successfully connected to server");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Connection failed.");
		} catch (NotBoundException e) {
			e.printStackTrace();
			System.out.println("Connection failed.");
		}
    }

    public String[] getQuizzes() {
        String[] quizzes = null;
        try {
            quizzes = remoteInterface.getQuizzesString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public boolean deleteQuiz(String name) {
        boolean success = false;
        try {
            success = remoteInterface.deleteQuiz(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void newAttempt(String username, String name) {
        attempt = new Attempt(username);
        try {
            activeQuiz = remoteInterface.getQuiz(name);
            //remoteInterface.addActiveQuiz(id);
            nextQuestion();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void nextQuestion() {
        if (count < activeQuiz.getQuestionsInt()) {
            Question question = activeQuiz.getQuestions().get(count);
            new AnswerQuestion(this, question, count+1).launch();
        } else {
            finalMessage();
            storeAttempt();
            //cleanList();
        }
    }

    /**
        public void cleanList() {
            try{
                remoteInterface.clean(activeQuiz.getId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    */

    public void storeAttempt() {
        attempt.setScore(score);
        try{
            remoteInterface.addHighscore(attempt, activeQuiz.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void finalMessage() {
        JOptionPane.showMessageDialog(null, "DONE! You scored " + score + " out of " + activeQuiz.getQuestionsInt());
    }

    public void increaseScore(){
        score++;
    }

    public boolean validateQuiz(String name) {
        try {
            if (remoteInterface.getQuiz(name) == null) {
                return false;
            }
        } catch (RemoteException e) {
                e.printStackTrace();
            }
        return true;
    }

    public void highcore(String name) {
        Quiz quizScores;
        try {
            quizScores = remoteInterface.getQuiz(name);
            String[] allScores = new String[quizScores.getAttempts().size()];
            int position = 0;
            for (Attempt attempts: quizScores.getAttempts()) {
                allScores[position] = "User: " + attempts.getUsername() + " Score: " + attempts.getScore() + " / " + quizScores.getQuestionsInt();
                position++;
            }
            new Highscores(this, allScores).launch();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    

}
