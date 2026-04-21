package com.abdullaevaziz.fileuploaderspringbootfx.controllers;

import com.abdullaevaziz.fileuploaderspringbootfx.App;
import com.abdullaevaziz.fileuploaderspringbootfx.model.User;
import com.abdullaevaziz.fileuploaderspringbootfx.model.UserFile;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserFileRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

public class ChoseUserController {

    @FXML
    private ListView<User> listView;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

    @FXML
    public void initialize() {
        try {
            this.listView.setItems(FXCollections.observableList(new UserRepository(token).getListUsers()));
            this.listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            User user = listView.getSelectionModel().getSelectedItem();
                            System.out.println("ChoseUser: User Type - " + user.getUserType() + "-> " + user.getId());
                            try {
                                App.openWindow("main.fxml", "Main info", user);
                                Stage currentStage = (Stage) listView.getScene().getWindow();
                                currentStage.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            });
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void exitButton(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
