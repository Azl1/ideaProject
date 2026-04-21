package com.abdullaevaziz.quizspringbootfx.controllers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.retrofit.QuizRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class LoadingFormController implements ControllerData<Quiz>{
    @FXML
    TextField textFieldNumberOfQuestions;
    @FXML
    ComboBox<String> comboBoxLoadingCategory;
    @FXML
    ComboBox<String> comboBoxLoadingDifficulty;
    private HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

    private Quiz quiz;
    private final String LAST_USED_DIRECTORY_KEY = "LAST_USED_DIRECTORY";
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private QuizRepository quizRepository = new QuizRepository(token);
    private long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);

    @Override
    public void initData(Quiz value)  {
        this.quiz = value;
    }
    @FXML
    public void initialize() {
        ArrayList<String> stringArrayList1 = new ArrayList<>();
        stringArrayList1.add("Art");
        stringArrayList1.add("Animals");
        stringArrayList1.add("History");
        stringArrayList1.add("Sports");

        stringIntegerHashMap.put("Art", 25);
        stringIntegerHashMap.put("Animals", 27);
        stringIntegerHashMap.put("History", 10);
        stringIntegerHashMap.put("Sports", 21);

        this.comboBoxLoadingCategory.setItems(FXCollections.observableList(stringArrayList1));

        ArrayList<String> stringArrayList2 = new ArrayList<>();
        stringArrayList2.add("easy");
        stringArrayList2.add("medium");
        stringArrayList2.add("hard");
        this.comboBoxLoadingDifficulty.setItems(FXCollections.observableList(stringArrayList2));

    }

    public void buttonStart(ActionEvent actionEvent) {
        try {
            int count = Integer.parseInt(this.textFieldNumberOfQuestions.getText());
            if (count < 1 || count > 10) {
                App.showAlert("Error", "Select number", Alert.AlertType.ERROR);
                return;
            }

            String category = this.comboBoxLoadingCategory.getSelectionModel().getSelectedItem();
            if (category == null) {
                App.showAlert("Error", "Select category", Alert.AlertType.ERROR);
                return;
            }

            String difficulty = this.comboBoxLoadingDifficulty.getSelectionModel().getSelectedItem();
            if (difficulty == null) {
                App.showAlert("Error", "Select difficulty", Alert.AlertType.ERROR);
                return;
            }

            /*String link = "https://opentdb.com/api.php?amount=" + count + "&category=" + this.stringIntegerHashMap.get(category)
                    + "&difficulty=" + difficulty;
            Repository repository = new Repository(link);
            QuizResult quizResult = repository.getQuiz();*/
            System.out.println("Game Form/Token - " + token);
            Quiz quizGet = new QuizRepository(token).getQuiz(count, this.stringIntegerHashMap.get(category), difficulty);
            System.out.println("Quiz get" + quizGet);
            Quiz quizSave = quizRepository.post(quizGet);

            App.openWindow("gameForm.fxml", "Game Form", quizSave);

        } catch (NumberFormatException | IOException e) {
            App.showAlert("Error!", "Некорректный введены данные!", Alert.AlertType.ERROR);
        }
    }

    /*public void buttonSave(ActionEvent actionEvent) throws IOException {
        try {
            int count = Integer.parseInt(this.textFieldNumberOfQuestions.getText());
            if (count < 1 || count > 10) {
                App.showAlert("Error", "Select number", Alert.AlertType.ERROR);
                return;
            }

            String category = this.comboBoxLoadingCategory.getSelectionModel().getSelectedItem();
            if (category == null) {
                App.showAlert("Error", "Select category", Alert.AlertType.ERROR);
                return;
            }

            String difficulty = this.comboBoxLoadingDifficulty.getSelectionModel().getSelectedItem();
            if (difficulty == null) {
                App.showAlert("Error", "Select difficulty", Alert.AlertType.ERROR);
                return;
            }

            String link = "https://opentdb.com/api.php?amount=" + count + "&category=" + this.stringIntegerHashMap.get(category)
                    + "&difficulty=" + difficulty;
            System.out.println(link);

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter2);

            fileChooser.setInitialDirectory(new File(preferences.get(LAST_USED_DIRECTORY_KEY, ".")));
            File file = fileChooser.showSaveDialog(null);

            *//*if (file != null) {
                prefs.put(LAST_USED_DIRECTORY_KEY, file.getParent());
                Repository repository = new Repository(link);
                repository.save(file);
            }*//*
        } catch (NumberFormatException e) {
            //TODO вывести шоу алертом что некотрректный формат введенного числа
            App.showAlert("Error!", "Некорректный формат введенного числа", Alert.AlertType.ERROR);
        }
    }*/

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("main.fxml", "Main info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
