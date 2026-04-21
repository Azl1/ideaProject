package com.abdullaevaziz.quizspringbootfx.controllers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class ChoseUserController {

    @FXML
    public ListView<User> listView;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

    @FXML
    public void initialize(){
        try {
            this.listView.setItems(FXCollections.observableList(new UserRepository(token).getListUsers()));
            this.listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if (mouseEvent.getClickCount() == 2){
                            User user = listView.getSelectionModel().getSelectedItem();
                            preferences.putLong(Constants.PREFERENCE_KEY_ID, user.getId());

                            try {
                                App.openWindow("main.fxml", "Main info", null);
                                App.closeWindow(mouseEvent);
                            } catch (IOException e){
                                e.printStackTrace();
                                App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            });
        } catch (IOException | IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    @FXML
    public void exitButton(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
