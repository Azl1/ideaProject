package com.abdullaevaziz.quizspringbootfx.controllers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.History;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.model.Result;
import com.abdullaevaziz.quizspringbootfx.retrofit.HistoryRepository;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

public class GameFormController implements ControllerData<Quiz> {

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private UserRepository userRepository = new UserRepository(token);
    private HistoryRepository historyRepository = new HistoryRepository(token);
    @FXML
    public TabPane tabPane;
    private Quiz quiz;
    private Map<Integer, String> map = new HashMap<>();
    private boolean showCorrectAnswers;
    private List<History> histories = new ArrayList<>();

    @Override
    public void initData(Quiz value) {
        try {
            this.quiz = value;
            // List<History> histories;
            //TODO для выбранного квиза сделать получение списка объектов хистори

            histories = FXCollections.observableList(new HistoryRepository(token).
                    getHistory(value.getId()));

            System.out.println(histories);

            List<Result> results = this.quiz.getResults();
            int questionNumber = 0;
            for (Result result : results) {
                questionNumber++;
                Tab tab = new Tab("Q" + questionNumber);
                tabPane.getTabs().add(tab);

                VBox vBox = new VBox();
                Label label = new Label(result.getQuestion());
                vBox.getChildren().add(label);
                ArrayList<String> answers = new ArrayList<>(result.getIncorrectAnswers());
                answers.add(result.getCorrectAnswer());
                ToggleGroup toggleGroup = new ToggleGroup();
                for (String answer : answers) {
                    RadioButton radioButton = new RadioButton(answer);
                    vBox.getChildren().add(radioButton);
                    radioButton.setToggleGroup(toggleGroup);

                    for (History history : histories) {
                        if (history.getResult().getId() == result.getId()) {

                            if (history.getAnswer().trim().equalsIgnoreCase(answer.trim())) {
                                radioButton.setSelected(true);
                            }

                            if (history.isCorrect()) {

                                if (answer.equals(result.getCorrectAnswer())) {
                                    radioButton.setStyle("-fx-text-fill: green;");
                                }
                            } else {

                                if (answer.equals(history.getAnswer())) {
                                    radioButton.setStyle("-fx-text-fill: red;");
                                }

                                if (answer.equals(result.getCorrectAnswer())) {
                                    radioButton.setStyle("-fx-text-fill: green;");
                                }
                            }
                        }
                    }

                    int finalQuestionNumber = questionNumber;
                    radioButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String text = radioButton.getText();
                            map.put(finalQuestionNumber - 1, text);

                            boolean isCorrect = text.equals(result.getCorrectAnswer());

                            try {
                                Result r = new Result();
                                r.setId(result.getId());
                                History history = new History(r, text, isCorrect);
                                new HistoryRepository(token).post(history);
                                histories.add(history);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                tab.setContent(vBox);
            }
            Tab tab = new Tab("Results");
            VBox vBox = new VBox();
            tab.setContent(vBox);
            Button summarizeButton = new Button("Check");
            vBox.getChildren().add(summarizeButton);
            summarizeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (map.size() != results.size()) {
                        App.showAlert("Error!", "Не все вопросы были даны ответы!", Alert.AlertType.ERROR);
                        return;
                    }

                    String res = "";
                    Label statisticRes = new Label("Statistics:");
                    vBox.getChildren().add(statisticRes);
                    int correct = 0;
                    int incorrect = 0;
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        int number = entry.getKey();
                        String answer = entry.getValue();
                        String correctAnswer = results.get(number).getCorrectAnswer();
                        if (correctAnswer.equals(answer)) {
                            res = "+";
                            correct++;
                        } else {
                            res = "-";
                            Preferences prefs = Preferences.userNodeForPackage(getClass());
                            boolean showCorrectAnswers = prefs.getBoolean(Constants.SHOW_CORRECT_ANSWERS, false);
                            if (showCorrectAnswers) {
                                incorrect++;
                                res += "(" + correctAnswer + ")";
                            }
                        }
                        Label statistics = new Label("Question i : " + res);
                        vBox.getChildren().add(statistics);

                        Label emptiness = new Label("");
                        vBox.getChildren().add(emptiness);

                        Label correctIncorrect1 = new Label("Correct/Incorrect: " + correct + " / " + incorrect);
                        vBox.getChildren().add(correctIncorrect1);

                        Label сorrectAnswer = new Label("Correct Answer Rate: " + ((double) correct / results.size()) * 100 + "% ");
                        vBox.getChildren().add(сorrectAnswer);
                    }
                }
            });
            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isShowCorrectAnswers() {
        return showCorrectAnswers;
    }

    public void setShowCorrectAnswers(boolean showCorrectAnswers) {
        this.showCorrectAnswers = showCorrectAnswers;
    }

    @Override
    public String toString() {
        return "GameFormController{" +
                "quizResult=" + this.quiz +
                '}';
    }
}
