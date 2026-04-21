package com.abdullaevaziz.quiz.repository;

import com.abdullaevaziz.quiz.model.QuizResult;
import com.abdullaevaziz.quiz.model.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;


public class Repository {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    private QuizResult quizResult;


    public Repository(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedReader = new BufferedInputStream(httpURLConnection.getInputStream())) {
            this.quizResult = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });

            for (Result result : this.quizResult.getResults()){
                result.setQuestion(StringEscapeUtils.unescapeHtml4(result.getQuestion()));
                result.setCorrectAnswer(StringEscapeUtils.unescapeHtml4(result.getCorrectAnswer()));
                List<String> incorrectAnswers = result.getIncorrectAnswers();
                for (int i = 0; i < incorrectAnswers.size(); i++) {
                    incorrectAnswers.set(i, StringEscapeUtils.unescapeHtml4(incorrectAnswers.get(i)));
                }
                result.setIncorrectAnswers(incorrectAnswers);
            }
        }
    }




    public Repository(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            this.quizResult = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        }
    }

    public void save(File file) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            objectMapper.writeValue(bufferedWriter, this.quizResult);
        }
    }

    public QuizResult getQuiz() {
        return this.quizResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(objectMapper, that.objectMapper) && Objects.equals(quizResult, that.quizResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectMapper, quizResult);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "objectMapper=" + objectMapper +
                ", quiz=" + this.quizResult +
                '}';
    }
}
