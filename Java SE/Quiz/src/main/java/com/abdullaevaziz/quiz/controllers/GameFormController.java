package com.abdullaevaziz.quiz.controllers;

import com.abdullaevaziz.quiz.App;
import com.abdullaevaziz.quiz.model.QuizResult;
import com.abdullaevaziz.quiz.model.Result;
import com.abdullaevaziz.quiz.util.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

public class GameFormController implements ControllerData<QuizResult> {

    @FXML
    public TabPane tabPane;
    private QuizResult quizResult;

    private Map<Integer, String> map = new HashMap<>();

    private boolean showCorrectAnswers;

    /**
     * • Проверяется наличие ответов на все вопросы. Если не на все вопросы были даны ответы,
     * то должно отображаться AlertMessage с соответствующим сообщением.
     * <p>
     * • В TabPane “Results” выводится статистика (Label Statistics) по каждому вопросу в формате Question i : (+/-),
     * Correct/Incorrect (например, 3/2) и Correct Answer Rate (60 %).
     * Если в Форме 1 был выбран “Show Correct Answers”, то также показываются правильные ответы на каждый вопрос.
     */

    @Override
    public void initData(QuizResult value) {
        this.quizResult = value;

        List<Result> results = this.quizResult.getResults();
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
                int finalQuestionNumber = questionNumber;
                radioButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String text = radioButton.getText();
                        map.put(finalQuestionNumber - 1, text);
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
            public void handle(ActionEvent actionEvent) {
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
                        //TODO тут считать с преференсес данные того булеан
                        // и если он тру то тогда добавлть в рес еще дополнительно правильный ответ
                        Preferences prefs = Preferences.userNodeForPackage(getClass());
                        boolean showCorrectAnswers = prefs.getBoolean(Constants.SHOW_CORRECT_ANSWERS, false);
                        if (showCorrectAnswers) {
                            incorrect++;
                            res += "(" + correctAnswer + ")";
                        }
                    }
                    Label statistics = new Label("Question i : " + res);
                    vBox.getChildren().add(statistics);
                }
                    Label emptiness = new Label("");
                    vBox.getChildren().add(emptiness);

                    Label correctIncorrect1 = new Label("Correct/Incorrect: " + correct + " / " + incorrect);
                    vBox.getChildren().add(correctIncorrect1);

                    Label сorrectAnswer = new Label("Correct Answer Rate: " + ((double) correct / results.size()) * 100 + "% ");
                    vBox.getChildren().add(сorrectAnswer);


                }
        });
        tabPane.getTabs().add(tab);
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
                "quizResult=" + this.quizResult +
                '}';
    }
}
