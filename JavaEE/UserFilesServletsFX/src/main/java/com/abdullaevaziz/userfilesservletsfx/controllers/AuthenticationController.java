package com.abdullaevaziz.userfilesservletsfx.controllers;

import com.abdullaevaziz.userfilesservletsfx.App;
import com.abdullaevaziz.userfilesservletsfx.model.User;
import com.abdullaevaziz.userfilesservletsfx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthenticationController  {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private User user;
    private UserRepository userRepository = new UserRepository();


    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            this.user = userRepository.getUser(loginTextField.getText(), passwordTextField.getText());
            if(user == null){
                App.showAlert("Error!", "Пользователь отсутствует!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("listUsers.fxml", "List file info", user);

        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", "Не верный логин или пароль!", Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addUser.fxml", "Add user info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
