package com.abdullaevaziz.quizspringbootfx.controllers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.Quiz;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.model.UserType;
import com.abdullaevaziz.quizspringbootfx.retrofit.QuizRepository;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MainController {

    @FXML
    public ListView<Quiz> quizListView;
    @FXML
    public Label label;
    @FXML
    public Label textLabel;
    @FXML
    public Button backToUsersButton;
    @FXML
    private Button buttonNewQuiz;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

    private User user;




    public void initialize() {
        try {
            long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
            User user = new UserRepository(token).getUserId(id);

            User owner = new UserRepository(token).get();

            System.out.println("Token === " + token);
            System.out.println("User type " + owner.getUserType());
            System.out.println("ID " + id);
            if (UserType.ADMIN.equals(user.getUserType())) {
                buttonNewQuiz.setVisible(false);
            } else {
                backToUsersButton.setVisible(false);
            }
            this.quizListView.setItems(FXCollections.observableList(new QuizRepository(token).getListQuiz(user.getId())));
            this.quizListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Quiz quiz = quizListView.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("gameForm.fxml", "Game form info", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                                App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backToUsersButton(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
            User user = new UserRepository(token).getUserId(id);

            if (user.getUserType().equals(UserType.ADMIN)) {
                App.openWindow("choseUser.fxml", "Chose users info", null);
            } else {
                App.showAlert("Error!", "Только у админа есть доступ", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonNewQuiz(ActionEvent actionEvent) {
        Quiz quiz = quizListView.getSelectionModel().getSelectedItem();
        try {
            App.openWindow("loadingForm.fxml", "Loading Form", quiz);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
