
package com.abdullaevaziz.quizspringbootfx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Result {

    private long id;

    private String type;

    private String difficulty;

    private String category;

    private String question;

    private String correctAnswer;

    private List<String> incorrectAnswers = new ArrayList<String>();

    @ToString.Exclude
    private Quiz quiz;


    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param difficulty
     * @param incorrectAnswers
     * @param question
     * @param type
     * @param category
     * @param correctAnswer
     */
    public Result(String type, String difficulty, String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        super();
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("difficulty")
    public String getDifficulty() {
        return difficulty;
    }

    @JsonProperty("difficulty")
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("correct_answer")
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @JsonProperty("correct_answer")
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @JsonProperty("incorrect_answers")
    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    @JsonProperty("incorrect_answers")
    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(type, result.type) && Objects.equals(difficulty, result.difficulty) && Objects.equals(category, result.category) && Objects.equals(question, result.question) && Objects.equals(correctAnswer, result.correctAnswer) && Objects.equals(incorrectAnswers, result.incorrectAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, difficulty, category, question, correctAnswer, incorrectAnswers);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                ", quiz=" + quiz +
                '}';
    }
}
