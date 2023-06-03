package server;

import quiz.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{
    
    public boolean addQuiz(Quiz quiz) throws RemoteException;

    public boolean deleteQuiz(String name) throws RemoteException;

    public Quiz getQuiz(String name) throws RemoteException;

    //public int setQuizId() throws RemoteException;

    public String[] getQuizzesString() throws RemoteException;

    public void addHighscore(Attempt attempt, int id) throws RemoteException;

    //public void addActiveQuiz(int id) throws RemoteException;

    //public void removeActiveQuiz(int id) throws RemoteException;
    
}
