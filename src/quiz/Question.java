package quiz;

import java.io.Serializable;

public class Question implements Serializable{
    private String question;
    private String[] answers;
    private int correctAnswer;

    public Question (String question, String[] answers, int correctAnswer) {
		this.question = question; 
		this.answers = answers;
		this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public String getCorrectAnswer() {
        return this.answers[correctAnswer];
    }

}
