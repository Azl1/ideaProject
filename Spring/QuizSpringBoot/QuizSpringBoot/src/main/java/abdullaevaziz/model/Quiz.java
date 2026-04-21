
package abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "quiz")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "response_code",
        "results"
})
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @JsonProperty("response_code")
    private int responseCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     */
    public Quiz() {
    }

    /**
     * @param results
     * @param responseCode
     */
    public Quiz(int responseCode, List<Result> results) {
        super();
        this.responseCode = responseCode;
        this.results = results;
    }

    @JsonProperty("response_code")
    public int getResponseCode() {
        return responseCode;
    }

    @JsonProperty("response_code")
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz that = (Quiz) o;
        return responseCode == that.responseCode && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, results);
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "responseCode=" + responseCode +
                ", results=" + results +
                '}';
    }
}
