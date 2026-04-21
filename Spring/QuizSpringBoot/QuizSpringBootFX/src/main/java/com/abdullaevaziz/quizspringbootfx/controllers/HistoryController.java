package com.abdullaevaziz.quizspringbootfx.controllers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.History;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.retrofit.HistoryRepository;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.prefs.Preferences;

public class HistoryController implements ControllerData<Quiz> {
    @FXML
    public ListView<History> getHistoryListView;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
    private UserRepository userRepository = new UserRepository(token);
    private Quiz quiz;

    @Override
    public void initData(Quiz value) throws IOException {
        quiz = value;
        this.getHistoryListView.setItems(FXCollections.observableList(new HistoryRepository(token).
                        getHistory(value.getId())));
    }

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
