package com.abdullaevaziz.telegrambotspringbootfx.controllers;

import com.abdullaevaziz.telegrambotspringbootfx.App;
import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.model.Admin;
import com.abdullaevaziz.telegrambotspringbootfx.retrofit.AdminRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class UserRegistration {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String loginGet = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String passwordGet = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    //private AdminRepository adminRepository = new AdminRepository(loginGet, passwordGet);

    @FXML
    public void addUser(ActionEvent actionEvent) {
        try {
        String username = loginTextField.getText();
        String password = passwordTextField.getText();


        Admin adminNew = new Admin(username, password);


        AdminRepository adminRepository = new AdminRepository();

        if (loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            App.showAlert("Error!", "Дынные не введены!", Alert.AlertType.ERROR);
            return;
        }


            adminRepository.post(adminNew);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
