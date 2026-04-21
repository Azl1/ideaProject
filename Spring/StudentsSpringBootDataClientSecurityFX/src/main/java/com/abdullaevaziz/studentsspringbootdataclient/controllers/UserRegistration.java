package com.abdullaevaziz.studentsspringbootdataclient.controllers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.model.User;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserRegistration {


    @FXML
    public Label loginLabel;
    @FXML
    public TextField loginTextField;
    @FXML
    public Label passwordLabel;
    @FXML
    public TextField passwordTextField;


    @FXML
    public void addUser(ActionEvent actionEvent) {
        String username = loginTextField.getText();
        String password = passwordTextField.getText();

        User userNew = new User(username, password);
        UserRepository userRepository = new UserRepository();

        if (loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            App.showAlert("Error!", "Дынные не введены!", Alert.AlertType.ERROR);
            return;
        }

        try {
            User user = userRepository.post(userNew);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
