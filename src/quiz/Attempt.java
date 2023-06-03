package quiz;

import java.io.Serializable;

public class Attempt implements Comparable, Serializable{
    private String username;
    private int score;

    public Attempt(String username) {
        this.username = username;
    }

    public int compareTo(Object other) {
        Attempt otherAttempt = (Attempt) other;
        return otherAttempt.getScore() - this.getScore();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
}
