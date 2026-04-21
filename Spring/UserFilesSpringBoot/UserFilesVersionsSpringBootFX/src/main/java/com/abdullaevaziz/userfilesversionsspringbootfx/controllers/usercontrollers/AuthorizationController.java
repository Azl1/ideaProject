package com.abdullaevaziz.userfilesversionsspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.userfilesversionsspringbootfx.App;
import com.abdullaevaziz.userfilesversionsspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.userfilesversionsspringbootfx.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthorizationController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);


    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            String login = loginTextField.getText();
            String password = passwordTextField.getText();


            String token = new UserRepository().authenticate(login, password);
            this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, token);
            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, login);

            App.openWindow("main.fxml", "List files", null);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("userRegistration.fxml", "Registration user info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
