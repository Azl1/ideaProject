package com.abdullaevaziz.fencingschoolfx.controllers.usercontrollers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.model.User;
import com.abdullaevaziz.fencingschoolfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {

    @FXML
    public TextField loginTextField;

    @FXML
    public TextField passwordTextField;

    @FXML
    public TextField nameTextField;

    private UserRepository userRepository = new UserRepository();

    @FXML
    public void addUser(ActionEvent actionEvent) {
        User userNew = new User(loginTextField.getText(), passwordTextField.getText(), nameTextField.getText());
        try {
            User user = this.userRepository.post(userNew);
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
