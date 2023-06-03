package controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.lang.model.element.QualifiedNameable;

import gui.EnterQuestion;
import gui.NamingQuiz;
import quiz.Question;
import quiz.Quiz;
import server.RemoteInterface;

public class Editor {
    private Quiz newQuiz;
    private RemoteInterface remoteInterface;

    public void launch() {
        connect();
        new NamingQuiz(this).launch();
    }

    public void create(String name) {
        newQuiz = new Quiz(name);
        System.out.println("Success!");
    }

    public void addToServer() {
        try {
            newQuiz.setId(remoteInterface.setQuizId());
            remoteInterface.addQuiz(newQuiz, newQuiz.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getQuestions() {
        new EnterQuestion(this).launch();
    }

    public void addQuestion(String question, String[] answers, int correctAnswer ) {
        this.newQuiz.setQuestion(new Question(question, answers, correctAnswer));
        System.out.println("Question added");
    }

    public void connect() {
        try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			remoteInterface = (RemoteInterface) reg.lookup("Quizzer");
			System.out.println("Connected.");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Connection failed.");
		} catch (NotBoundException e) {
			e.printStackTrace();
			System.out.println("Connection failed.");
		} 
    }

}
