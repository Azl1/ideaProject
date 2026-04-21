package com.abdullaevaziz.telegrambotspringbootfx.controllers;

import com.abdullaevaziz.telegrambotspringbootfx.App;
import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.model.Admin;
import com.abdullaevaziz.telegrambotspringbootfx.retrofit.AdminRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Base64;
import java.util.prefs.Preferences;

public class AuthController {
    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private Admin admin;
    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            AdminRepository adminRepository = new AdminRepository(loginTextField.getText(), passwordTextField.getText());
            admin = adminRepository.get();
            if (admin == null) {
                App.showAlert("Error!", "Пользователь отсутствует!", Alert.AlertType.ERROR);
                return;
            }
            String userLogin = loginTextField.getText();
            String password = passwordTextField.getText();


            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, userLogin);
            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, admin.getId());


            App.openWindow("main.fxml", "Main info", null);
            App.closeWindow(actionEvent);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Не верный логин или пароль!", Alert.AlertType.ERROR);
            this.preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            this.preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
            this.preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            this.preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("userRegistration.fxml", "Registration user info", admin);
        } catch (IOException e) {
            e.printStackTrace();
            this.preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            this.preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        }
    }
}
