package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

import quiz.Attempt;
import quiz.Quiz;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    private HashMap<Integer, Quiz> quizzes = new HashMap<Integer, Quiz>();

    public Server() throws RemoteException {
        super();
        File data = new File("Data.txt");
        if (data.exists()) {
            try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(data))) {
                quizzes = (HashMap<Integer,Quiz>) input.readObject();
                System.out.println("Loading Quizzes");
            } catch(IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addQuiz(Quiz newquiz, int id) {
        quizzes.put(id, newquiz);
        flush();
        System.out.println("Quiz added");
        return true;
    }

    public Quiz getQuiz(int id) {
        return (Quiz) quizzes.get(id);
    }

    public boolean deleteQuiz(int id) {
        quizzes.remove(id);
        flush();
        System.out.println("Quiz deleted");
        return true;
        
    }

    public synchronized int setQuizId() {
        Set<Integer> keySet = quizzes.keySet();
        int id = 1;
        boolean found = false;
        while (!found) {
            if (keySet.contains(id)) {
                id++;
            } else {
                found = true;
            }
        }
        return id;
    }

    public String[] getQuizzesString() {
        int pos = 0;
        String[] quizlist = new String[quizzes.size()];
            for (Quiz quiz: quizzes.values()) {
                quizlist[pos] = new String("[Quiz id: ]" + quiz.getId());
                pos++;
            }
        return quizlist;
    }

    public void addHighscore(Attempt score, int id) {
        quizzes.get(id).setAttempt(score);
        flush();
    }

    private void flush() {
        File data = new File("Data.txt");
        if (data.exists()) {
            data.delete();
        }
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(data))) {
            output.writeObject(quizzes);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}
