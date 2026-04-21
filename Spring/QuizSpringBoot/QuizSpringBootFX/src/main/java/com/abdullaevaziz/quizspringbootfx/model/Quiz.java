
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
public class Quiz {

    private long id;

    private int responseCode;

    private User user;

    @ToString.Exclude
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
