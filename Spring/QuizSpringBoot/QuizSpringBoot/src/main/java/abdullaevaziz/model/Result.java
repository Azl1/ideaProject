
package abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "result")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "difficulty",
        "category",
        "question",
        "correct_answer",
        "incorrect_answers"
})
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @JsonProperty("type")
    private String type;

    @Column(nullable = false)
    @JsonProperty("difficulty")
    private String difficulty;

    @Column(nullable = false)
    @JsonProperty("category")
    private String category;

    @Column(nullable = false)
    @JsonProperty("question")
    private String question;

    @Column(nullable = false)
    @JsonProperty("correct_answer")
    private String correctAnswer;

    @ElementCollection
    @JoinTable(name = "incorrect_answers")
    @Column(name = "incorrect_answer")
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers = new ArrayList<String>();

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @ToString.Exclude
    @JsonIgnore
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
                "type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}
