package com.abdullaevaziz.studentsspringbootdataclient.controllers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.model.User;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthorizationController{

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    @FXML
    public void buttonLogin(ActionEvent actionEvent) throws IOException {
        try {
            String username = loginTextField.getText();
            String password = passwordTextField.getText();

            User user = new UserRepository(username, password).get();

            System.out.println(user);

            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, username);
            this.preferences.putLong(Constants.PREFERENCE_KEY_PASSWORD, Long.parseLong(password));

            //App.openWindow("main.fxml", "User registration info", null);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", "Не верный логин или пароль!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("userRegistration.fxml", "User info", null);
        } catch (IOException e){

        }
    }

}

