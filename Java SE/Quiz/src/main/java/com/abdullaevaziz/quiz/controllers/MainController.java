package com.abdullaevaziz.quiz.controllers;

import com.abdullaevaziz.quiz.App;
import com.abdullaevaziz.quiz.model.QuizResult;
import com.abdullaevaziz.quiz.repository.Repository;
import com.abdullaevaziz.quiz.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;


public class MainController {

    @FXML
    public CheckBox checkBox;
    private final String LAST_USED_DIRECTORY_KEY = "LAST_USED_DIRECTORY";
    private final Preferences prefs = Preferences.userNodeForPackage(GameFormController.class);

    public void initialize() {
        boolean isResCheckBox = prefs.getBoolean(Constants.SHOW_CORRECT_ANSWERS, false);
        checkBox.setSelected(isResCheckBox);
    }

    public void buttonLoadingForm(ActionEvent actionEvent) throws IOException {
        App.openWindow("loadingForm.fxml", "Loading Form ", null);
    }

    public void buttonGameForm(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter2);

        fileChooser.setInitialDirectory(new File(prefs.get(LAST_USED_DIRECTORY_KEY, ".")));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Repository repository = new Repository(new File(String.valueOf(file)));
            QuizResult quizResult1 = repository.getQuiz();
            App.openWindow("gameForm.fxml", "GameFormController", quizResult1);
        }
    }


    public void buttonShowCorrectAnswers(ActionEvent actionEvent) throws IOException {
        boolean cbx = checkBox.isSelected();
        prefs.putBoolean(Constants.SHOW_CORRECT_ANSWERS, cbx);
    }
}
